package com.nijin.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository//This is redundant as it is added by default
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  boolean existsCustomerByEmail(String email);
  boolean existsCustomerById(Integer id);
}
