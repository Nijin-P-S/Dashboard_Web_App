package com.nijin.customer;

import com.nijin.AbstractTestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest : If we use this annotation, then it will be connecting to the primary database configured in docker-compose.yml
@DataJpaTest  //This makes it connect to the datasource
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //No embedded database
class CustomerRepositoryTest extends AbstractTestcontainers{

    @Autowired
    private CustomerRepository underTest;



    @BeforeEach
    void setUp() {
        underTest.deleteAll(); //Cause we have commandLineRunner inserting data to database from our Main file everytime we run the application
    }

    @Test
    void existsCustomerByEmail() {
        String email = FAKER.internet().safeEmailAddress()+"-"+ UUID.randomUUID();
        //Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.save(customer);

        //When
        boolean actual = underTest.existsCustomerByEmail(email);

        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void existsCustomerByEmailFails() {
        String email = FAKER.internet().safeEmailAddress()+"-"+ UUID.randomUUID();
        //Given
        //When
        boolean actual = underTest.existsCustomerByEmail(email);

        //Then
        assertThat(actual).isFalse();
    }

    @Test
    void existsCustomerById() {
        String email = FAKER.internet().safeEmailAddress()+"-"+ UUID.randomUUID();
        //Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.save(customer);

        int id = underTest.findAll()
                .stream()
                .filter((a) -> a.getEmail().equals(email))
                .map((a) -> a.getId())
                .findFirst()
                .orElseThrow();
        //When
        boolean actual = underTest.existsCustomerById(id);

        //Then
        assertThat(actual).isTrue();

    }

    @Test
    void existsCustomerByIdFails() {

        int id = -1;
        //When
        boolean actual = underTest.existsCustomerById(id);

        //Then
        assertThat(actual).isFalse();

    }
}