package com.example.ecommerceJava2.Model;

public enum Role {
    CUSTOMER, ADMIN;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}
