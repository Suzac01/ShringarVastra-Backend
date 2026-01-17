package com.example.demo.service.admin.order;

import com.example.demo.dto.customer.CustomerDTO;

import java.util.List;

public interface AdminCustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO dto);

    void hardDeleteCustomer(Long id);

    CustomerDTO getCustomerById(Long id);


}
