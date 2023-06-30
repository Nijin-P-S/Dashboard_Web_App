package com.nijin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDataAccessService implements CustomerDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> selectAllCustomers() {
        var sql = """
                SELECT id, name, email, age FROM customer
                """;
        List<Customer> customers = jdbcTemplate.query(sql,
                (rs, rowNum)->{
                    Customer customer = new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("age")
                    );
                    return customer;
                }
        );
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name, email, age)
                VALUES(?, ?, ?)
                """;

        int update = jdbcTemplate.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
                );
        System.out.println(update);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return false;
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {

    }

    @Override
    public void updateCustomer(Customer update) {

    }
}
