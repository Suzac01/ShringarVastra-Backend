package com.example.demo.service.client.address;

import com.example.demo.dto.address.AddressDTO;
import com.example.demo.model.address.Address;
import com.example.demo.repository.addressRepository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AddressServiceImple implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImple(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Long userId, AddressDTO dto){
        Address address = new Address(
                userId,
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getCity(),
                dto.getState(),
                dto.getZipCode(),
                dto.getCountry()
        );

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getUserAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}
