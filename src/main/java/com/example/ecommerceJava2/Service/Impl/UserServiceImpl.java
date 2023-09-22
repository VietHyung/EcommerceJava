package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.DTO.UserDTO;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Model.Role;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.UserRepository;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private JavaMailSender mailSender;

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

    @Override
    public User saveUser(User user, String url) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole(Role.CUSTOMER);

        user.setEnable(true);
        user.setVerificationCode(UUID.randomUUID().toString());

        User newuser = userRepo.save(user);
        return newuser;
    }

    @Override
    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

    @Override
    public void sendEmail(User user, String path) {

    }

    @Override
    public boolean verifyAccount(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {

            user.setEnable(true);
            user.setVerificationCode(null);

            userRepo.save(user);

            return true;
        }
    }
}
