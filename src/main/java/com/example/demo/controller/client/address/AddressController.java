package com.example.demo.controller.client.address;


import com.example.demo.dto.address.AddressDTO;
import com.example.demo.model.address.Address;
import com.example.demo.service.client.address.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // POST → Save new shipping address
    @PostMapping("/{userId}")
    public Address saveAddress(
            @PathVariable Long userId,
            @RequestBody AddressDTO addressDTO) {

        return addressService.saveAddress(userId, addressDTO);
    }

    // GET → Get all addresses for a user
    @GetMapping("/{userId}")
    public List<Address> getUserAddresses(@PathVariable Long userId) {
        return addressService.getUserAddresses(userId);
    }

}

