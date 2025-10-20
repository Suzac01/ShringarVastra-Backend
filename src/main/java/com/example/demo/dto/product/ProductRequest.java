package com.example.demo.dto.product;

import java.util.List;

public class ProductRequest {

    private Long id; // ✅ add this for updates
    private String name;
    private Double price;
    private String description;
    private Integer categoryId;
    private String collection;
    private List<String> tags;
    private String vendor;
    private String status;
    private Double weight;
    private Integer stockQuantity;
    private Double rating;
    private Integer reviewsCount;
    private String sku;
    private List<String> images;

    public ProductRequest() {}

    public ProductRequest(Long id, String name, Double price, String description, Integer categoryId, String collection,
                          List<String> tags, String vendor, String status, Double weight,
                          Integer stockQuantity, Double rating, Integer reviewsCount,
                          String sku, List<String> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.collection = collection;
        this.tags = tags;
        this.vendor = vendor;
        this.status = status;
        this.weight = weight;
        this.stockQuantity = stockQuantity;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
        this.sku = sku;
        this.images = images;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getReviewsCount() { return reviewsCount; }
    public void setReviewsCount(Integer reviewsCount) { this.reviewsCount = reviewsCount; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", collection='" + collection + '\'' +
                ", tags=" + tags +
                ", vendor='" + vendor + '\'' +
                ", status='" + status + '\'' +
                ", weight=" + weight +
                ", stockQuantity=" + stockQuantity +
                ", rating=" + rating +
                ", reviewsCount=" + reviewsCount +
                ", sku='" + sku + '\'' +
                ", images=" + images +
                '}';
    }
}
