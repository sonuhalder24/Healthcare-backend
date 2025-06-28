package com.example.health.controller;

import com.example.health.Model.ApplicationUser;
import com.example.health.Model.Patient;
import com.example.health.security.JwtUtil;
import com.example.health.service.ApplicationUserService;

//import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationUserController {
    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtService;

    @PostMapping("register")
    public String saveUser(@RequestBody ApplicationUser user){
        boolean isSaved = applicationUserService.saveAppUser(user);
        if(isSaved){
            return "Registration successful";
        }
        return "Password or username policy failed";
    }

    @PostMapping("signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody ApplicationUser user){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUser_name()
                        /*user.getUser_email()*/,user.getPassword())
        );
        if(authentication.isAuthenticated()) {
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtService.generateToken(user.getUser_name()));
            response.put("id", user.getUser_name());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        /*if(authentication.isAuthenticated())
            return "Authentication Successful!,\n\ntoken="+
                    jwtService.generateToken(user.getUser_email())+",\nid="+
                    user.getUser_name();
        else
            return "Username or Password is incorrect.";*/
    }

    @GetMapping("/viewprofile/{userId}")
    public ApplicationUser getUserByUsername(@PathVariable("userId") String id) {
        return applicationUserService.getUserById(id);
    }

    @PutMapping("/editprofile/{userId}")
    public ApplicationUser update(@PathVariable("userId") String id,@RequestBody ApplicationUser user) {
        return applicationUserService.editUserName(id, user);

    }

}
