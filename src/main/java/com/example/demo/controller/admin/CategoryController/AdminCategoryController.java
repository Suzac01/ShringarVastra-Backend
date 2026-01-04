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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
}
