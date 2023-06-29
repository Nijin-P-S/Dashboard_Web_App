package com.nijin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getAllCustomer(){
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomerById(Integer id) throws ResourceNotFoundException {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("The ID you are looking for is not present in the database")
                );
    }



}
