package com.example.ecommerceJava2.Model.DTO;

import com.example.ecommerceJava2.Model.Role;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}

