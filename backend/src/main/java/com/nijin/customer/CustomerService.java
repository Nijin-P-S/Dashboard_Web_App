package com.nijin.customer;

import com.nijin.exception.DuplicateResourceException;
import com.nijin.exception.RequestValidationException;
import com.nijin.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

//    @Autowired
//    @Qualifier(value = "jdbc")
//    CustomerDao customerDao;

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomer(){
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomerById(Integer id) throws ResourceNotFoundException {
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("The ID you are looking for is not present in the database")
                );
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) throws DuplicateResourceException {
        //Check if email exists
        if(customerDao.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException("The email already in use");
        }
        //Else add the customer
        customerDao.insertCustomer(
                new Customer(
                        customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                )
        );
    }


    public void deleteCustomerById(Integer customerId) {
        if(!customerDao.existsPersonWithId(customerId)){
            throw new ResourceNotFoundException("customer with id [%s] not found".formatted(customerId));
        }

        customerDao.deleteCustomerById(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) throws DuplicateResourceException, RequestValidationException {
        Customer customer = getCustomerById(customerId);
        boolean changes = false;

        if(updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            changes = true;
        }

        if(updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if(updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
            if(customerDao.existsPersonWithEmail(updateRequest.email())){
                throw new DuplicateResourceException("Email already taken");
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if(!changes){
            throw new RequestValidationException("No data change found");
        }
        customerDao.updateCustomer(customer);
    }
}
