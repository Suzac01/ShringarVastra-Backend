////package com.example.demo.dto.CategoryRequest;
////
////import jakarta.validation.constraints.NotBlank;
////import jakarta.validation.constraints.Size;
////
////public class CategoryRequest {
////
////    @NotBlank(message = "Category name is required")
////    private String categoryName;
////
////    @Size(max = 500, message = "Description can be max 500 characters")
////    private String description;
////
////    // getters & setters
////    public String getCategoryName() {
////        return categoryName;
////    }
////
////    public void setCategoryName(String categoryName) {
////        this.categoryName = categoryName;
////    }
////
////    public String getDescription() {
////        return description;
////    }
////
////    public void setDescription(String description) {
////        this.description = description;
////    }
////}
//
//package com.example.demo.dto.CategoryRequest;
//
//import jakarta.validation.constraints.NotBlank;
//
//public class CategoryRequest {
//
//    @NotBlank(message = "Category name is required")
//    private String categoryName;
//
//    private String description;
//
//    // getters & setters
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}


package com.example.demo.dto.CategoryRequest;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class CategoryRequest {

    @NotBlank(message = "Category name is required")
    private String categoryName;

    private String description;

    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    // getters & setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
