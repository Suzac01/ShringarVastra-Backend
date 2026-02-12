package com.example.demo.controller.admin.CategoryController;
//
//import com.example.demo.dto.CategoryRequest.CategoryRequest;
//import com.example.demo.model.Categories;
//import com.example.demo.service.admin.AdminService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//
//
////@RestController
////@RequestMapping("/admin/category")
////public class AdminCategoryController {
////
////    @Autowired
////    private AdminService adminService;
////
////    @PostMapping(
////            value = "/add",
////            consumes = "multipart/form-data"
////    )
////    public ResponseEntity<Categories> addCategory(
////            @Valid @ModelAttribute CategoryRequest request) {
////
////        Categories savedCategory = adminService.saveCategory(request);
////        return ResponseEntity.ok(savedCategory);
////    }
////}
////
//
//
//@RestController
//@RequestMapping("/admin/categories")
//public class AdminCategoryController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Categories> addCategory(
//            @Valid @ModelAttribute CategoryRequest request,
//            @RequestParam(required = false) MultipartFile image) throws IOException {
//
//        Categories saved = adminService.saveCategory(request, image);
//        return ResponseEntity.ok(saved);
//    }
//}
//
//

import com.example.demo.dto.CategoryRequest.CategoryRequest;
import com.example.demo.model.Categories;
import com.example.demo.service.admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private AdminService adminService;

    // ✅ ADD CATEGORY
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Categories> addCategory(
            @Valid @ModelAttribute CategoryRequest request) throws IOException {

        Categories saved = adminService.saveCategory(request);
        return ResponseEntity.ok(saved);
    }

    // ✅ GET ALL CATEGORIES
    @GetMapping("/all")
    public ResponseEntity<List<Categories>> getAllCategories() {
        System.out.println("NEW METHOD: getAllCategories");
        return ResponseEntity.ok(adminService.getAllCategories());

    }



    @PostMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id) {
        try {
            // Delete the category using service method
            adminService.deleteCategory(id);

            // Create success response
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Category deleted successfully");
            response.put("deletedId", String.valueOf(id));

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            // Handle category not found or other runtime exceptions
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // Handle other unexpected errors
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to delete category: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // ✅ UPDATE CATEGORY
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @ModelAttribute CategoryRequest request) throws IOException {

        try {
            Categories updated = adminService.updateCategory(id, request, request.getImage());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Category updated successfully");
            response.put("category", updated);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Failed to update category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
