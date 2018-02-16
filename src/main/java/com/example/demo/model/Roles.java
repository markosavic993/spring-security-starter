package com.example.demo.model;

/**
 * Created by msav on 2/7/2018.
 */
public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
