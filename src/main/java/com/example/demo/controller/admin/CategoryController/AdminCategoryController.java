package com.example.demo.controller.admin.CategoryController;

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


//@RestController
//@RequestMapping("/admin/category")
//public class AdminCategoryController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @PostMapping(
//            value = "/add",
//            consumes = "multipart/form-data"
//    )
//    public ResponseEntity<Categories> addCategory(
//            @Valid @ModelAttribute CategoryRequest request) {
//
//        Categories savedCategory = adminService.saveCategory(request);
//        return ResponseEntity.ok(savedCategory);
//    }
//}
//


@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private AdminService adminService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Categories> saveCategory(
            @RequestPart("category") @Valid CategoryRequest categoryRequest,
            @RequestPart("image") MultipartFile imageFile) throws IOException {

        Categories savedCategory = adminService.saveCategory(categoryRequest, imageFile);
        return ResponseEntity.ok(savedCategory);
    }
}

