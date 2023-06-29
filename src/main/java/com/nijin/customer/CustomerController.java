package com.nijin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping("api/v1/customer/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) throws ResourceNotFoundException {
       return customerService.getCustomerById(customerId);
    }

    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomer();
    }
}
