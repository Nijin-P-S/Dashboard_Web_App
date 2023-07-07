package com.nijin.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService underTest;
    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void selectAllCustomers() {
        //When
        underTest.selectAllCustomers();
        //Then
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    void selectCustomerById() {
        //Given
        int id = 1;
        //When
        underTest.selectCustomerById(id);
        //Then
        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    void insertCustomer() {
        //Given
        Customer customer = new Customer(
                1,
                "Alex",
                "alex@gmail.com",
                23

        );
        //When
        underTest.insertCustomer(customer);
        //Then
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    void existsPersonWithEmail() {

        String email = "alex@gmail.com";
        //When
        underTest.existsPersonWithEmail(email);
        //Then
        Mockito.verify(customerRepository).existsCustomerByEmail(email);
    }

    @Test
    void existsPersonWithId() {
        int id = 1;
        //When
        underTest.existsPersonWithId(id);
        //Then
        Mockito.verify(customerRepository).existsCustomerById(id);
    }

    @Test
    void deleteCustomerById() {
        int id = 1;
        //When
        underTest.deleteCustomerById(id);
        //Then
        Mockito.verify(customerRepository).deleteById(id);
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer(
                1,
                "Alex",
                "alex@gmail.com",
                23

        );
        //When
        underTest.updateCustomer(customer);
        //Then
        Mockito.verify(customerRepository).save(customer);
    }
}