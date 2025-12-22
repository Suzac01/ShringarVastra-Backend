package com.example.demo.service.client.address;

import com.example.demo.dto.address.AddressDTO;
import com.example.demo.model.address.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Long userId, AddressDTO addressDTO);

    List<Address> getUserAddresses(Long userId);
}
