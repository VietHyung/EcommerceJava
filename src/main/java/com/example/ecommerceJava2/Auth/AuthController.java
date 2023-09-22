//package com.example.ecommerceJava2.Auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authenticationService;
//
//    @PostMapping("/register")
//    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request){
//        return authenticationService.register(request);
//    }
//
//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("title", "Login");
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("title", "register");
//        return "register";
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
////        return authenticationService.authenticate(request);
////    }
//
//    @PostMapping("/change-password")
//    public ResponseEntity<AuthResponseDTO> changePassword(@RequestBody ChangePasswordRequestDTO request){
//        return authenticationService.changePassword(request);
//    }
//}
