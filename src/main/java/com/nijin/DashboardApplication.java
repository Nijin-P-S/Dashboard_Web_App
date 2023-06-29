package com.nijin;

import com.nijin.customer.Customer;
import com.nijin.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}


//	@Bean
//	CommandLineRunner runner(CustomerRepository customerRepository){
//		return args -> {
//
//			Customer nijin = new Customer(
//					"Nijin P S",
//					"abc@gmail.com",
//					24
//			);
//			Customer jessu =
//					new Customer(
//							"Jessica Nijin",
//							"abc@gmail.com",
//							23
//					);
//
//			List<Customer> customerList = List.of(nijin, jessu);
//			customerRepository.saveAll(customerList);
//		};
//	}


}
