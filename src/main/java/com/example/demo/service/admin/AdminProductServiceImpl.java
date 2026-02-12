package com.example.demo.service.admin;

import com.example.demo.dto.CategoryRequest.CategoryRequest;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.model.Categories;
import com.example.demo.model.Products;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminProductServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    // ===== PRODUCT METHODS =====

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        // Check if SKU already exists
        if (productRepository.existsBySku(request.getSku())) {
            throw new RuntimeException("SKU already exists: " + request.getSku());
        }

        Products product = new Products();
        mapProductDtoToEntity(request, product);

        // Handle product image (take first image if available)
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            // Store first image as main product image
            product.setProductImage(request.getImages().get(0));

            // Store all images in tags (including main image)
            List<String> allImages = new ArrayList<>(request.getImages());
            product.setTags(allImages);
        }

        Products savedProduct = productRepository.save(product);
        return mapEntityToResponse(savedProduct);
    }

//    @Override
//    public List<ProductResponse> getAllProducts() {
//        return productRepository.findAll()
//                .stream()
//                .map(this::mapEntityToResponse)
//                .collect(Collectors.toList());
//    }

    @Override
    public ProductResponse getProductById(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return mapEntityToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        // Check if SKU is being changed and if new SKU already exists
        if (request.getSku() != null && !product.getSku().equals(request.getSku()) &&
                productRepository.existsBySku(request.getSku())) {
            throw new RuntimeException("SKU already exists: " + request.getSku());
        }

        mapProductDtoToEntity(request, product);

        // Handle product image update
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            // Store first image as main product image
            product.setProductImage(request.getImages().get(0));

            // Store all images in tags
            List<String> allImages = new ArrayList<>(request.getImages());
            product.setTags(allImages);
        }

        Products updatedProduct = productRepository.save(product);
        return mapEntityToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse uploadProductImages(Long productId, List<MultipartFile> imageFiles) throws IOException {

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        String uploadDir = System.getProperty("user.dir")
                + File.separator + "uploads"
                + File.separator + "productsImg";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : imageFiles) {
            if (file != null && !file.isEmpty()) {

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File destination = new File(uploadDir + File.separator + fileName);

                file.transferTo(destination);

                imageUrls.add(fileName);
            }
        }

        product.setImages(imageUrls); // or tags
        productRepository.save(product);

        return mapToResponse(product);
    }

        private ProductResponse mapToResponse(Products product) {

            ProductResponse response = new ProductResponse();
            response.setId(product.getId());
            response.setProductName(product.getProductName());
            response.setProductPrice(product.getProductPrice());
            response.setProductImage(Collections.singletonList(product.getProductImage()));
            response.setDescription(product.getDescription());
            response.setStatus(product.getStatus());
            response.setIsFeatured(product.getIsFeatured());

            return response;
        }




    @Override
    public ProductResponse deleteProductImage(Long productId, String imageUrl) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        // Remove the image URL from tags
        if (product.getTags() != null) {
            product.getTags().remove(imageUrl);
        }

        // Optional: Delete the actual file from server
        try {
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            Path filePath = Paths.get(uploadDir + "/products/" + fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException | StringIndexOutOfBoundsException e) {
            System.err.println("Failed to delete image file: " + e.getMessage());
        }

        // If deleted image was the main product image, clear it
        if (imageUrl.equals(product.getProductImage())) {
            product.setProductImage(null);
        }

        Products updatedProduct = productRepository.save(product);
        return mapEntityToResponse(updatedProduct);
    }

    @Override
    public List<ProductResponse> searchProducts(String name, String sku, String brand, String category) {
        List<Products> products;

        if (name != null && !name.isEmpty()) {
            products = productRepository.findByProductNameContainingIgnoreCase(name);
        } else if (sku != null && !sku.isEmpty()) {
            products = productRepository.findBySkuContainingIgnoreCase(sku);
        } else if (brand != null && !brand.isEmpty()) {
            products = productRepository.findByBrandContainingIgnoreCase(brand);
        } else if (category != null && !category.isEmpty()) {
            products = productRepository.findByCategoryCategoryNameContainingIgnoreCase(category);
        } else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsByStatus(String status) {
        List<Products> products = productRepository.findByStatus(status);
        return products.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private void mapProductDtoToEntity(ProductRequest request, Products product) {
        // Use correct getter methods based on DTO field names
        product.setProductName(request.getName()); // Changed from getProductName() to getName()
        product.setSku(request.getSku());
        product.setProductPrice(request.getPrice()); // Changed from getProductPrice() to getPrice()
        product.setBrand(request.getBrand());
        product.setStatus(request.getStatus());
        product.setDescription(request.getDescription());

        // Handle images - assuming you store first image URL in product_image
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            product.setProductImage(request.getImages().get(0)); // Store first image
        }

        product.setStockQuantity(request.getStockQuantity() != null ? request.getStockQuantity() : 0);
        product.setWeight(request.getWeight());
        product.setRating(request.getRating());
        product.setReviewsCount(request.getReviewsCount());
        product.setDiscount(request.getDiscount());
        product.setDimensions(request.getDimensions());
        product.setIsFeatured(request.getIsFeatured() != null ? request.getIsFeatured() : false);

        // Handle category
        if (request.getCategoryId() != null) {
            Categories category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
            product.setCategory(category);
        }
    }

    private ProductResponse mapEntityToResponse(Products product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setProductName(product.getProductName());
        response.setSku(product.getSku());
        response.setProductPrice(product.getProductPrice());
        response.setBrand(product.getBrand());
        response.setStatus(product.getStatus());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());

        // Extract all images from tags (if stored there)
        List<String> allImages = new ArrayList<>();
        if (product.getTags() != null) {
            // Check if tags contain image URLs (could be mixed with actual tags)
            for (String item : product.getTags()) {
                if (item.startsWith("http") || item.startsWith("/uploads") || item.contains(".jpg") || item.contains(".png") || item.contains(".jpeg")) {
                    allImages.add(item);
                }
            }
        }

        // If no images in tags but main image exists, use that
        if (allImages.isEmpty() && product.getProductImage() != null) {
            allImages.add(product.getProductImage());
        }

        response.setProductImage(allImages);

        // Filter out images from tags for actual tags
        List<String> actualTags = new ArrayList<>();
        if (product.getTags() != null) {
            for (String tag : product.getTags()) {
                if (!tag.startsWith("http") && !tag.startsWith("/uploads") && !tag.contains(".jpg") && !tag.contains(".png") && !tag.contains(".jpeg")) {
                    actualTags.add(tag);
                }
            }
        }
        response.setTags(actualTags);

        response.setStockQuantity(product.getStockQuantity());
        response.setWeight(product.getWeight());
        response.setDimensions(product.getDimensions());
        response.setRating(product.getRating());
        response.setReviewsCount(product.getReviewsCount());
        response.setDiscount(product.getDiscount());
        response.setIsFeatured(product.getIsFeatured());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }



    @Override
    public Categories saveCategory(CategoryRequest request) throws IOException {
        Categories category = new Categories();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        category.setTitle(request.getTitle());  // ADD THIS LINE
        category.setCreatedDate(LocalDateTime.now());

        MultipartFile imageFile = request.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = saveCategoryImage(imageFile);
            category.setImage(imageUrl);
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }



    @Override
    public Categories updateCategory(Long id, CategoryRequest request, MultipartFile imageFile) throws IOException {
        // Find existing category
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        // Update fields if provided (only update non-null values)
        if (request.getCategoryName() != null && !request.getCategoryName().isEmpty()) {
            category.setCategoryName(request.getCategoryName());
        }

        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }

        if (request.getTitle() != null) {
            category.setTitle(request.getTitle());
        }

        // Handle image update if new image is provided
        if (imageFile != null && !imageFile.isEmpty()) {
            // Delete old image file if exists
            if (category.getImage() != null) {
                deleteCategoryImageFile(category.getImage());
            }

            // Save new image
            String fileName = saveCategoryImage(imageFile);
            category.setImage(fileName);
        }

        // Update timestamp
        category.setUpdatedDate(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    private void deleteCategoryImageFile(String fileName) {
        try {
            if (fileName != null && !fileName.isEmpty()) {
                String uploadDir = System.getProperty("user.dir")
                        + File.separator + "uploads"
                        + File.separator + "categories";

                File file = new File(uploadDir + File.separator + fileName);
                if (file.exists()) {
                    boolean deleted = file.delete();
                    if (deleted) {
                        System.out.println("Deleted old category image: " + fileName);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to delete category image file: " + fileName);
            e.printStackTrace();
            // Don't throw exception - continue with update even if image deletion fails
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private String saveCategoryImage(MultipartFile image) throws IOException {

        if (image == null || image.isEmpty()) {
            return null;
        }

        String uploadDir = System.getProperty("user.dir")
                + File.separator + "uploads"
                + File.separator + "categories";

        // ✅ CREATE DIRECTORY IF NOT EXISTS
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File destinationFile = new File(uploadDir + File.separator + fileName);

        image.transferTo(destinationFile);

        return fileName;
    }
}
