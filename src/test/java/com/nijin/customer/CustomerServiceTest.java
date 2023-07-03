package com.nijin.customer;

import com.nijin.exception.DuplicateResourceException;
import com.nijin.exception.RequestValidationException;
import com.nijin.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void willThrowWhenGetCustomerByIdReturnEmptyOptional(){
        //Given
        int id = 10;

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty());

        //When
        //Then
        assertThatThrownBy(()->underTest.getCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The ID you are looking for is not present in the database");
    }

    @Test
    void addCustomer() throws DuplicateResourceException {
        String email = "alex@gmail.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(false);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Alex", email, 19
        );

        //When
        underTest.addCustomer(request);

        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(
                Customer.class
        );

        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(request.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(request.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(request.age());
    }

    @Test
    void willThrowWhenEmailExistsWhileAddingACustomer() throws DuplicateResourceException {
        String email = "alex@gmail.com";

        when(customerDao.existsPersonWithEmail(email)).thenReturn(true);

        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Alex", email, 19
        );

        assertThatThrownBy(() -> underTest.addCustomer(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("The email already in use");

        verify(customerDao, never()).insertCustomer(any());
    }

    @Test
    void deleteCustomerById() {
        //Given

        int id = 10;

        when(customerDao.existsPersonWithId(id)).thenReturn(true);
        //When

        underTest.deleteCustomerById(id);
        //Then
        verify(customerDao).deleteCustomerById(id);
    }


    @Test
    void willThrowDeleteCustomerByIdNotExists() {
        //Given

        int id = 10;

        when(customerDao.existsPersonWithId(id)).thenReturn(false);
        //When

        assertThatThrownBy(() -> underTest.deleteCustomerById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("customer with id [%s] not found".formatted(id));

        verify(customerDao, never()).deleteCustomerById(id);
    }


    @Test
    void updateCustomer() throws RequestValidationException, DuplicateResourceException {
        //Given
        int id = 1;

        Customer customer = new Customer(
                id, "Alex", "alex@gmail.com", 21
        );

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        String newEmail = "alexandro@gmail.com";
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest("Alexandro", newEmail, 29 );

        when(customerDao.existsPersonWithEmail(newEmail)).thenReturn(false);
        //When
        underTest.updateCustomer(id, customerUpdateRequest);

        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(
                Customer.class
        );
        verify(customerDao).updateCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getAge()).isEqualTo(customerUpdateRequest.age());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customerUpdateRequest.email());
        assertThat(capturedCustomer.getName()).isEqualTo(customerUpdateRequest.name());
    }
}