package com.nijin.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
