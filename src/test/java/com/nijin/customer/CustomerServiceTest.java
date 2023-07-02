package com.nijin.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService underTest;
    @Mock
    private CustomerDao customerDao;

//    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
//        autoCloseable = MockitoAnnotations.openMocks(this); Instead of writing these boilerplate code, we can just annotate the class with @ExtendWith(MockitoExtension.class)
    }


//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void getAllCustomer() {
        underTest.getAllCustomer();

        verify(customerDao).selectAllCustomers();
    }

    @Test
    void getCustomerById() {
        //Given
        int id = 1;

        Customer customer = new Customer(
                id, "Alex", "alex@gmail.com", 21
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        //When
        Customer actual = underTest.getCustomerById(id);

        //Then
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void addCustomer() {
        //Given

        //When

        //Then
    }

    @Test
    void deleteCustomerById() {
        //Given

        //When

        //Then
    }

    @Test
    void updateCustomer() {
        //Given

        //When

        //Then
    }
}