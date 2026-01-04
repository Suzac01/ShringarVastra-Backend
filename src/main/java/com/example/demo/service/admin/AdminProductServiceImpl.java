//package com.example.demo.service.admin;
//
//import com.example.demo.dto.CategoryRequest.CategoryRequest;
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.model.Products;
//import com.example.demo.repository.categoryRepository.CategoryRepository;
//import com.example.demo.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class AdminServiceimpl implements AdminService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//
//    @Override
//    public Products saveProduct(ProductRequest request) {
//        Products product = new Products();
//        mapProductDtoToEntity(request, product);
//        return productRepository.save(product);
//    }
//
//    @Override
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Products updateProduct(Products product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//
//
//    private void mapProductDtoToEntity(ProductRequest request, Products product) {
//        product.setProductName(request.getName());
//        product.setProductPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        product.setWeight(request.getWeight());
//        product.setStockQuantity(request.getStockQuantity());
//        product.setRating(request.getRating());
//        product.setReviewsCount(request.getReviewsCount());
//        product.setSku(request.getSku());
//        product.setStatus(request.getStatus());
//        product.setBrand(request.getBrand());
//        product.setDiscount(request.getDiscount());
//        product.setTags(request.getTags());
//
//        if (request.getCategoryId() != null) {
//            Categories category = categoryRepository.findById(request.getCategoryId().longValue())
//                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
//            product.setCategory(category);
//        }
//    }
//
//    // ===== Category Methods =====
//    public Categories saveCategory(CategoryRequest request, MultipartFile imageFile) throws IOException {
//        Categories category = new Categories();
//        category.setCategoryName(request.getCategoryName());
//        category.setDescription(request.getDescription());
//
//        // Handle image file
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String uploadDir = "uploads/categories/";
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            File dest = new File(uploadDir + fileName);
//            dest.getParentFile().mkdirs();
//            imageFile.transferTo(dest);
//            category.setImage(uploadDir + fileName); // save path or file name
//        }
//
//        return categoryRepository.save(category);
//    }
//
//    public List<Categories> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    public Categories updateCategory(Categories category, MultipartFile imageFile) throws IOException {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String uploadDir = "uploads/categories/";
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            File dest = new File(uploadDir + fileName);
//            dest.getParentFile().mkdirs();
//            imageFile.transferTo(dest);
//            category.setImage(uploadDir + fileName);
//        }
//        return categoryRepository.save(category);
//    }
//
//    public void deleteCategory(Long id) {
//        categoryRepository.deleteById(id);
//    }
//}
//
//
//package com.example.demo.service.admin;
//
//import com.example.demo.dto.CategoryRequest.CategoryRequest;
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.model.Products;
//import com.example.demo.repository.categoryRepository.CategoryRepository;
//import com.example.demo.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//@Service
//public class AdminServiceimpl implements AdminService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Value("${app.upload.dir}")
//    private String uploadDir; // configurable folder from application.properties
//
//
//    @Override
//    public Products saveProduct(ProductRequest request) {
//        Products product = new Products();
//        mapProductDtoToEntity(request, product);
//        return productRepository.save(product);
//    }
//
//    @Override
//    public List<Products> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public Products updateProduct(Products product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//
//    private void mapProductDtoToEntity(ProductRequest request, Products product) {
//        product.setProductName(request.getName());
//        product.setProductPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        product.setWeight(request.getWeight());
//        product.setStockQuantity(request.getStockQuantity());
//        product.setRating(request.getRating());
//        product.setReviewsCount(request.getReviewsCount());
//        product.setSku(request.getSku());
//        product.setStatus(request.getStatus());
//        product.setBrand(request.getBrand());
//        product.setDiscount(request.getDiscount());
//        product.setTags(request.getTags());
//
//        if (request.getCategoryId() != null) {
//            Categories category = categoryRepository.findById(request.getCategoryId().longValue())
//                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
//            product.setCategory(category);
//        }
//    }
//
//    // ===== Category Methods =====
//    @Override
//    public Categories saveCategory(CategoryRequest request, MultipartFile imageFile) throws IOException {
//        Categories category = new Categories();
//        category.setCategoryName(request.getCategoryName());
//        category.setDescription(request.getDescription());
//
//        if (imageFile != null && !imageFile.isEmpty()) {
//            Path uploadPath = Paths.get(uploadDir);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath); // create folder if missing
//            }
//
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            Path filePath = uploadPath.resolve(fileName);
//
//            imageFile.transferTo(filePath.toFile()); // save file
//            category.setImage(fileName);
//        }
//
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public List<Categories> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//    @Override
//    public Categories updateCategory(Categories category, MultipartFile imageFile) throws IOException {
//        if (imageFile != null && !imageFile.isEmpty()) {
//            Path uploadPath = Paths.get(uploadDir);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//            Path filePath = uploadPath.resolve(fileName);
//
//            imageFile.transferTo(filePath.toFile());
//            category.setImage(fileName);
//        }
//
//        return categoryRepository.save(category);
//    }
//
//    @Override
//    public void deleteCategory(Long id) {
//        categoryRepository.deleteById(id);
//    }
//}
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
import java.util.ArrayList;
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

        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir + "/products");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save each image and store URLs in tags
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            if (!file.isEmpty() && file.getOriginalFilename() != null) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                file.transferTo(filePath.toFile());
                String imageUrl = "/uploads/products/" + fileName;
                imageUrls.add(imageUrl);
            }
        }

        // Add image URLs to tags (or you could create a separate entity for images)
        if (product.getTags() == null) {
            product.setTags(new ArrayList<>());
        }
        product.getTags().addAll(imageUrls);

        // Also set the first image as main product image
        if (!imageUrls.isEmpty()) {
            product.setProductImage(imageUrls.get(0));
        }

        Products updatedProduct = productRepository.save(product);
        return mapEntityToResponse(updatedProduct);
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
        product.setProductName(request.getName());
        product.setSku(request.getSku());
        product.setProductPrice(request.getPrice());
        product.setBrand(request.getBrand());
        product.setStatus(request.getStatus());
        product.setDescription(request.getDescription());

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
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = saveCategoryImage(imageFile);
            category.setImage(imageUrl);
        }

        return categoryRepository.save(category);
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
