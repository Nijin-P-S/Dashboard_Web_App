package com.nijin.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {
}
