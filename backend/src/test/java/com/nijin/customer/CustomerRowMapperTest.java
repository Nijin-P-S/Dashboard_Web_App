package com.nijin.customer;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class CustomerRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        //Given
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("email")).thenReturn("abc@gmail.com");
        when(resultSet.getInt("age")).thenReturn(29);
        when(resultSet.getString("name")).thenReturn("Abc");

        //When
        Customer actual = customerRowMapper.mapRow(resultSet,1);

        Customer expected = new Customer(
                1,
                "Abc",
                "abc@gmail.com",
                29
                );
        //Then
        assertThat(actual).isEqualTo(expected);
    }
}