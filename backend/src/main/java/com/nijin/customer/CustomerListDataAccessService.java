package com.nijin.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository("list")
public class CustomerListDataAccessService{


//    private static List<Customer> customers;
//
//    static{
//        customers = new ArrayList<>();
//        customers.add(
//                new Customer(
//                        1,
//                        "Nijin P S",
//                        "abc@gmail.com",
//                        24
//                )
//        );
//        customers.add(
//                new Customer(
//                        2,
//                        "Jessica Nijin",
//                        "abc@gmail.com",
//                        23
//                )
//        );
//    }
//
//    @Override
//    public List<Customer> selectAllCustomers() {
//        return customers;
//    }
//
//    @Override
//    public Optional<Customer> selectCustomerById(Integer id) {
//        return customers.stream()
//                .filter(a -> a.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public void insertCustomer(Customer customer) {
//        customers.add(customer);
//    }
//
//    @Override
//    public boolean existsPersonWithEmail(String email) {
//        return customers.stream()
//                .anyMatch(c -> c.getEmail().equals(email));
//    }
}
