package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.DTO.UserDTO;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.UserRepository;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Result> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User each : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(each.getUserId());
            userDTO.setUsername(each.getUsername());
            userDTO.setEmail(each.getEmail());
            userDTO.setRole(each.getRole());
            userDTOS.add(userDTO);
        }
        return ResponseEntity.ok(new Result("SUCCESS", "OK", userDTOS));
    }
}
