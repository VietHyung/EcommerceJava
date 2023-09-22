package com.example.ecommerceJava2.Service;

import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<Result> getUsers();

    public User saveUser(User user, String url);

    public void removeSessionMessage();

    public void sendEmail(User user, String path);

    public boolean verifyAccount(String verificationCode);

}
