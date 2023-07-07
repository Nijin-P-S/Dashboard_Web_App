package com.nijin.journey;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.nijin.customer.Customer;
import com.nijin.customer.CustomerRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;  //This will act as our Postman

    private static final Random RANDOM = new Random();
    private static final String CUSTOMER_URI = "/api/v1/customers";
    @Test
    void canRegisterACustomer() {
        //Create a registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String name = fakerName.fullName();
        String email = fakerName.lastName()+ UUID.randomUUID()+"@testxyz.com";
        int age = RANDOM.nextInt(1, 100);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                name, email, age
        );
        //Send a POST request
//        webTestClient.post()
//                .uri(CUSTOMER_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(request), CustomerRegistrationRequest.class)
//                .exchange()
//                .expectStatus()
//                .isOk();
//        //Get all customers
//        List<Customer> allCustomers = webTestClient.get()
//                .uri(CUSTOMER_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(new ParameterizedTypeReference<Customer>() {
//                })
//                .returnResult()
//                .getResponseBody();
//
//        assertThat(allCustomers).usingRecursiveFieldByFieldElementComparatorIgnoringFields()
        //make sure that customer is present
        //Get customer by Id
    }
}
