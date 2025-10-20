package com.example.demo.dto.auth.googleRegister;

public class Google {
    private String email;
    private String name;
    private String appwriteId; // optional, for tracking Appwrite user

    // getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAppwriteId() { return appwriteId; }
    public void setAppwriteId(String appwriteId) { this.appwriteId = appwriteId; }
}
