package com.nijin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) throws ResourceNotFoundException {
       return customerService.getCustomerById(customerId);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomer();
    }


    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) throws DuplicateResourceException {
        customerService.addCustomer(customerRegistrationRequest);
    }


    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId) throws ResourceNotFoundException{
        customerService.deleteCustomerById(customerId);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable(name = "customerId") Integer customerId,
                               @RequestBody CustomerUpdateRequest updateRequest){
        customerService.updateCustomer(customerId, updateRequest);
    }
}
