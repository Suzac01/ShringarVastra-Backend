//package com.example.demo.dto.auth.googleRegister;
//
//public class Google {
//    private String email;
//    private String name;
//    private String appwriteId; // optional, for tracking Appwrite user
//
//    // getters and setters
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getAppwriteId() { return appwriteId; }
//    public void setAppwriteId(String appwriteId) { this.appwriteId = appwriteId; }
//}


package com.example.demo.dto.auth.googleRegister;

public class Google {
    private String email;
    private String name;
    private String appwriteId; // optional, for tracking Appwrite user

    // Constructors
    public Google() {
    }

    public Google(String email, String name, String appwriteId) {
        this.email = email;
        this.name = name;
        this.appwriteId = appwriteId;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppwriteId() {
        return appwriteId;
    }

    public void setAppwriteId(String appwriteId) {
        this.appwriteId = appwriteId;
    }

    @Override
    public String toString() {
        return "Google{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", appwriteId='" + appwriteId + '\'' +
                '}';
    }
}
