//package com.example.ecommerceJava2.Auth;
//
//import com.example.ecommerceJava2.Model.Role;
//import com.example.ecommerceJava2.Model.User;
//import com.example.ecommerceJava2.Repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//
//    public ResponseEntity<AuthResponseDTO> register(RegisterRequestDTO request) {
//        try {
//            if (userRepository.existsUserByUsername(request.getUsername())) {
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body(AuthResponseDTO.builder().message("Username is already used").token(null).build());
//            }
//            User user = User.builder()
//                    .username(request.getUsername())
//                    .password(passwordEncoder.encode(request.getPassword()))
//                    .email(request.getEmail())
//                    .role(request.getRole().equals("ADMIN") ? Role.ADMIN : Role.CUSTOMER)
//                    .build();
//            userRepository.save(user);
//            String jwtToken = jwtService.generateToken(user);
//            return ResponseEntity.ok(AuthResponseDTO.builder().message("Register successfully").token(jwtToken).build());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    public ResponseEntity<AuthResponseDTO> authenticate(AuthRequestDTO request) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//            User user = userRepository.findByUsername(request.getUsername()).orElseThrow(null);
//            String jwtToken = jwtService.generateToken(user);
//            return ResponseEntity.ok(AuthResponseDTO.builder().message("Login success").token(jwtToken).build());
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponseDTO.builder().message("Invalid username or password").build());
//        } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//       }
//    }
//
//    public ResponseEntity<AuthResponseDTO> changePassword(ChangePasswordRequestDTO request) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//            User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
//            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//            userRepository.save(user);
//            //String jwtToken = jwtService.generateToken(user);
//            return ResponseEntity.ok(AuthResponseDTO.builder().message("Change Password successfully").build());
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponseDTO.builder().message("Invalid username or password").build());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
