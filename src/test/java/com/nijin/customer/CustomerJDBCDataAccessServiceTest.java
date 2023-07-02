package com.nijin.customer;

import com.nijin.AbstractTestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {

    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper  customerRowMapper= new CustomerRowMapper();
    @BeforeEach
    void setup(){
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void selectAllCustomers() {
        //Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress()+"-"+ UUID.randomUUID(),
                20
        );
        underTest.insertCustomer(customer);
        //When
        List<Customer> customers = underTest.selectAllCustomers();

        //Then
        assertThat(customers).isNotEmpty();
    }

    @Test
    void selectCustomerById() {
        String email = FAKER.internet().safeEmailAddress()+"-"+ UUID.randomUUID();
        //Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomers()
                .stream()
                .filter((a) -> a.getEmail().equals(email))
                .map((a) -> a.getId())
                .findFirst()
                .orElseThrow();
        //When
        Optional<Customer> actual = underTest.selectCustomerById(id);

        //Then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
            assertThat(c.getName()).isEqualTo(customer.getName());
        });
    }

    @Test
    void willReturnEmptyWhenselectCustomerById(){
        int id = -1;

        var actual = underTest.selectCustomerById(id);

        assertThat(actual).isEmpty();
    }

    @Test
    void insertCustomer() {
        //Given

        //When

        //Then
    }

    @Test
    void existsPersonWithEmail() {
        //Given
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();
        String name = FAKER.name().fullName();
        Customer customer = new Customer(
                name,
                email,
                20
        );
        underTest.insertCustomer(customer);
        //When
        boolean actual = underTest.existsPersonWithEmail(email);
        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void existsPersonWithId() {
        //Given
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        //When

        var actual = underTest.existsPersonWithId(id);
        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void deleteCustomerById() {
        //Given
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        //When
        underTest.deleteCustomerById(id);

        //Then
        Optional<Customer> actual = underTest.selectCustomerById(id);
        assertThat(actual).isNotPresent();
    }

    @Test
    void updateCustomerName() {
        //Given
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );

        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        var newName = "foo";

        //When
        Customer update = new Customer();
        update.setId(id);
        update.setName(newName);

        underTest.updateCustomer(update);
        //Then
        Optional<Customer> actual = underTest.selectCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(newName);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }


    @Test
    void existsPersonWithEmailReturnsFalseWhenDoesNotExists(){
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();

        boolean actual = underTest.existsPersonWithEmail(email);

        assertThat(actual).isFalse();
    }
}