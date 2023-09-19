package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Result;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<Result> getUsers();
}
