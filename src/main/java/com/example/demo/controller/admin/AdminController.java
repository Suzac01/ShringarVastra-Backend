//package com.example.demo.controller.admin;
//
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.model.Products;
//import com.example.demo.service.admin.AdminService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
////add edit update delete product from admin.
//@RestController
//@RequestMapping("/admin/product")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//
////    @PostMapping("/add")
////    public ResponseEntity<Products> addProduct(@Valid @RequestBody ProductRequest request) {
////        Products product = new Products();
////        product.setProductName(request.getName());
////        product.setProductPrice(request.getPrice());
////
////        Products savedProduct = adminService.saveProduct(product);
////        return ResponseEntity.ok(savedProduct);
////    }
//
//
//
//    @PostMapping("/add")
//    public ResponseEntity<Products> addProduct(@Valid @RequestBody ProductRequest request) {
//        Products savedProduct = adminService.saveProduct(request);
//        return ResponseEntity.ok(savedProduct);
//    }
//
//    // READ - Get all products
//    @GetMapping("/all")
//    public ResponseEntity<List<Products>> getAllProducts() {
//        List<Products> products = adminService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    // UPDATE - Update an existing product
//    @PutMapping("/update")
//    public ResponseEntity<Products> updateProduct(@RequestBody ProductRequest request) {
//        System.out.println("Received update payload: " + request);
//        Products product = new Products();
//        product.setId(request.getId()); // important!
//        product.setProductName(request.getName());
//        product.setProductPrice(request.getPrice());
//        Products updatedProduct = adminService.updateProduct(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    // DELETE - Delete a product by ID
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        adminService.deleteProduct(id);
//        return ResponseEntity.ok("Product deleted successfully");
//    }
//}


//package com.example.demo.controller.admin;
//
//import com.example.demo.dto.product.ProductRequest;
//import com.example.demo.dto.product.ProductResponse;
//import com.example.demo.service.admin.AdminService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/product")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    // CREATE - Add new product (matches frontend fields exactly)
//    @PostMapping("/add")
//    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
//        ProductResponse savedProduct = adminService.saveProduct(request);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    }
//
//    // READ - Get all products
//    @GetMapping("/all")
//    public ResponseEntity<List<ProductResponse>> getAllProducts() {
//        List<ProductResponse> products = adminService.getAllProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    // READ - Get product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
//        ProductResponse product = adminService.getProductById(id);
//        return ResponseEntity.ok(product);
//    }
//
//    // UPDATE - Update an existing product
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ProductResponse> updateProduct(
//            @PathVariable Long id,
//            @Valid @RequestBody ProductRequest request) {
//        ProductResponse updatedProduct = adminService.updateProduct(id, request);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    // DELETE - Delete a product by ID
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        adminService.deleteProduct(id);
//        return ResponseEntity.ok("Product deleted successfully");
//    }
//
//    // UPLOAD PRODUCT IMAGES
//    @PostMapping("/{id}/images")
//    public ResponseEntity<ProductResponse> uploadImages(
//            @PathVariable Long id,
//            @RequestParam("images") List<MultipartFile> imageFiles) throws IOException {
//        ProductResponse updatedProduct = adminService.uploadProductImages(id, imageFiles);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    // SEARCH PRODUCTS
//    @GetMapping("/search")
//    public ResponseEntity<List<ProductResponse>> searchProducts(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String sku,
//            @RequestParam(required = false) String brand,
//            @RequestParam(required = false) String category) {
//        List<ProductResponse> products = adminService.searchProducts(name, sku, brand, category);
//        return ResponseEntity.ok(products);
//    }
//
//    // GET PRODUCTS BY STATUS
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<ProductResponse>> getProductsByStatus(
//            @PathVariable String status) {
//        List<ProductResponse> products = adminService.getProductsByStatus(status);
//        return ResponseEntity.ok(products);
//    }
//}

package com.example.demo.controller.admin;

import com.example.demo.dto.customer.CustomerDTO;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.model.Products;
import com.example.demo.service.admin.AdminService;
import com.example.demo.service.admin.order.AdminCustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminCustomerService customerService;

    // CREATE - Add new product
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse savedProduct = adminService.saveProduct(request);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // READ - Get all products
    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = adminService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // READ - Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = adminService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // UPDATE - Update an existing product (FIXED PATH)
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse updatedProduct = adminService.updateProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE - Delete a product by ID

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        adminService.deleteProduct(id);
//        return ResponseEntity.ok("Product deleted successfully");
//    }

    // UPLOAD PRODUCT IMAGES
    @PostMapping("/{id}/images")
    public ResponseEntity<ProductResponse> uploadImages(
            @PathVariable Long id,
            @RequestParam("images") List<MultipartFile> imageFiles) throws IOException {
        ProductResponse updatedProduct = adminService.uploadProductImages(id, imageFiles);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE PRODUCT IMAGE
    @DeleteMapping("/{productId}/images/{imageIndex}")
    public ResponseEntity<ProductResponse> deleteImage(
            @PathVariable Long productId,
            @PathVariable Integer imageIndex) {
        // First get the product to find the image URL
        ProductResponse product = adminService.getProductById(productId);
        if (product.getProductImage() != null && imageIndex < product.getProductImage().size()) {
            String imageUrl = product.getProductImage().get(imageIndex);
            ProductResponse updatedProduct = adminService.deleteProductImage(productId, imageUrl);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // SEARCH PRODUCTS
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category) {
        List<ProductResponse> products = adminService.searchProducts(name, sku, brand, category);
        return ResponseEntity.ok(products);
    }

    // GET PRODUCTS BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductResponse>> getProductsByStatus(
            @PathVariable String status) {
        List<ProductResponse> products = adminService.getProductsByStatus(status);
        return ResponseEntity.ok(products);
    }







//    customer Part API

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/customersEdit/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO dto) {

        System.out.println("Updating customer ID: " + id);
        System.out.println("Payload: " + dto);


        CustomerDTO updatedCustomer = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(updatedCustomer);
    }


    @DeleteMapping("/customersDelete/{id}")
    public ResponseEntity<String> hardDeleteCustomer(@PathVariable Long id) {

        customerService.hardDeleteCustomer(id);
        return ResponseEntity.ok("Customer deleted permanently");
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {

        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }



}