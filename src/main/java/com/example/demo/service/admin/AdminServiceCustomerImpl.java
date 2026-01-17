package com.example.demo.service.admin;

import com.example.demo.dto.customer.CustomerDTO;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.model.Categories;
import com.example.demo.model.Products;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.address.Address;
import com.example.demo.repository.addressRepository.AddressRepository;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.categoryRepository.CategoryRepository;
import com.example.demo.service.admin.order.AdminCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceCustomerImpl implements AdminCustomerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        // fetch all users with role CLIENT
        List<User> users = userRepository.findByRole(Role.ROLE_CLIENT);

        return users.stream().map(user -> {
            // fetch address by email
            Address address = addressRepository.findByEmail(user.getEmail()).orElse(null);
            String phone = (address != null) ? address.getPhone() : "N/A"; // fallback if no phone

            return new CustomerDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    phone
            );
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        userRepository.save(user);

        Address address = addressRepository
                .findByUserId(id)
                .stream()
                .findFirst()
                .orElse(new Address());

        address.setUserId(id);
        address.setEmail(dto.getEmail());
        address.setPhone(dto.getPhone());

        addressRepository.save(address);

        return dto;
    }


    @Override
    public void hardDeleteCustomer(Long id) {

        addressRepository.findByUserId(id)
                .forEach(addressRepository::delete);

        userRepository.deleteById(id);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Ensure this user is a CLIENT
        if (user.getRole() != Role.ROLE_CLIENT) {
            throw new RuntimeException("User is not a customer");
        }

        // Fetch address using email (same logic as getAllCustomers)
        Address address = addressRepository
                .findByEmail(user.getEmail())
                .orElse(null);

        String phone = (address != null) ? address.getPhone() : "N/A";

        return new CustomerDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                phone
        );
    }

    private void mapDtoToEntity(ProductRequest request, Products product) {
        product.setProductName(request.getName());
        product.setProductPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setWeight(request.getWeight());
        product.setStockQuantity(request.getStockQuantity());
        product.setRating(request.getRating());
        product.setReviewsCount(request.getReviewsCount());
        product.setSku(request.getSku());
        product.setStatus(request.getStatus());
        product.setBrand(request.getBrand());
        product.setDiscount(request.getDiscount());
        product.setTags(request.getTags());

        if (request.getCategoryId() != null) {
            Categories category = categoryRepository.findById(request.getCategoryId().longValue())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
            product.setCategory(category);
        }
    }
}