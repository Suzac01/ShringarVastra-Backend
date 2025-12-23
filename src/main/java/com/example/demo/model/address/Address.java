package com.example.demo.model.address;
import jakarta.persistence.*;

    @Entity
    @Table(name = "addresses")
    public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // FK – linked to user
        private Long userId;

        private String fullName;
        private String email;
        private String phone;

        private String address;
        private String city;
        private String state;
        private String zipCode;
        private String country;

        // Constructors
        public Address() {}

        public Address(Long userId, String fullName, String email, String phone,
                       String address, String city, String state, String zipCode, String country) {
            this.userId = userId;
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }

        // Getters and setters (or use Lombok @Data)
        public Long getId() { return id; }

        public Long getUserId() { return userId; }

        public void setUserId(Long userId) { this.userId = userId; }

        public String getFullName() { return fullName; }

        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }

        public void setPhone(String phone) { this.phone = phone; }

        public String getAddress() { return address; }

        public void setAddress(String address) { this.address = address; }

        public String getCity() { return city; }

        public void setCity(String city) { this.city = city; }

        public String getState() { return state; }

        public void setState(String state) { this.state = state; }

        public String getZipCode() { return zipCode; }

        public void setZipCode(String zipCode) { this.zipCode = zipCode; }

        public String getCountry() { return country; }

        public void setCountry(String country) { this.country = country; }

}
