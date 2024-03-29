//package com.nijin.journey;
//
//import com.github.javafaker.Faker;
//import com.github.javafaker.Name;
//import com.nijin.customer.Customer;
//import com.nijin.customer.CustomerRegistrationRequest;
//import com.nijin.customer.CustomerUpdateRequest;
//import org.checkerframework.checker.units.qual.C;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//import org.assertj.core.api.Assertions;
//
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
//
//@SpringBootTest(webEnvironment = RANDOM_PORT)
//public class CustomerIntegrationTest {
//
//    @Autowired
//    private WebTestClient webTestClient;  //This will act as our Postman
//
//    private static final Random RANDOM = new Random();
//    private static final String CUSTOMER_URI = "/api/v1/customers";
//
//
//    /*
//    TODO:
//    1) create registration request
//    2) send a post request
//    3) get all customers
//    4) make sure that the customer is present
//    5) get customer by id
//     */
//    @Test
//    void canRegisterACustomer() {
//        //Create a registration request
//        Faker faker = new Faker();
//        Name fakerName = faker.name();
//        String name = fakerName.fullName();
//        String email = fakerName.lastName()+ UUID.randomUUID()+"@testxyz.com";
//        int age = RANDOM.nextInt(1, 100);
//        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
//                name, email, age
//        );
//        //Send a POST request
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
//        //make sure that customer is present
//        Customer expectedCustomer = new Customer(
//                name, email, age
//        );
//
//        Assertions.assertThat(allCustomers).usingElementComparatorIgnoringFields("id")
//                .contains(expectedCustomer);
//
//        int id = allCustomers.stream()
//                        .filter(customer -> customer.getEmail().equals(email))
//                                .map(Customer::getId)
//                                        .findFirst()
//                                                .orElseThrow();
//
//        expectedCustomer.setId(id);
//
//        //Get customer by Id
//        webTestClient.get()
//                .uri(CUSTOMER_URI+"/{id}", id)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody(new ParameterizedTypeReference<Customer>() {})
//                .isEqualTo(expectedCustomer);
//    }
//
//    @Test
//    void canDeleteCustomer() {
//        //Create a registration request
//        Faker faker = new Faker();
//        Name fakerName = faker.name();
//        String name = fakerName.fullName();
//        String email = fakerName.lastName()+ UUID.randomUUID()+"@testxyz.com";
//        int age = RANDOM.nextInt(1, 100);
//        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
//                name, email, age
//        );
//        //Send a POST request
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
//
//        int id = allCustomers.stream()
//                .filter(customer -> customer.getEmail().equals(email))
//                .map(Customer::getId)
//                .findFirst()
//                .orElseThrow();
//
//        //Delete customer
////        webTestClient.delete()
////                        .uri(CUSTOMER_URI+"/{id}", id)
////                                .accept(MediaType.APPLICATION_JSON);
//////                                        .exchange()
//////                                                .expectStatus()
//////                                                        .isOk();
////
////        //Get customer by Id
////        webTestClient.get()
////                .uri(CUSTOMER_URI+"/{id}", id)
////                .accept(MediaType.APPLICATION_JSON)
////                .exchange()
////                .expectStatus()
////                .isNotFound();
//    }
//
//    @Test
//    void canUpdateCustomer() {
//        //Create a registration request
//        Faker faker = new Faker();
//        Name fakerName = faker.name();
//        String name = fakerName.fullName();
//        String email = fakerName.lastName()+ UUID.randomUUID()+"@testxyz.com";
//        int age = RANDOM.nextInt(1, 100);
//        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
//                name, email, age
//        );
//        //Send a POST request
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
//
//        int id = allCustomers.stream()
//                .filter(customer -> customer.getEmail().equals(email))
//                .map(Customer::getId)
//                .findFirst()
//                .orElseThrow();
//
//        //Update customer
//        String newName = "newName";
//        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest(
//                newName, null, null
//        );
//
//        webTestClient.put()
//                .uri(CUSTOMER_URI+"/{id}", id)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(customerUpdateRequest), CustomerUpdateRequest.class)
//                .exchange()
//                .expectStatus()
//                .isOk();
//
//        //Get customer by Id
//        Customer updatedCustomer = webTestClient.get()
//                .uri(CUSTOMER_URI+"/{id}", id)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBody(Customer.class)
//                .returnResult()
//                .getResponseBody();
//
//        Customer expected = new Customer(
//                id, newName, email, age
//        );
//
//        assertThat(updatedCustomer).isEqualTo(expected);
//    }
//}
