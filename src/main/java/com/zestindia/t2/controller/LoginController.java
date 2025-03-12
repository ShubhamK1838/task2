package com.zestindia.t2.controller;

import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.dto.convertor.DTOConvertor;
import com.zestindia.t2.security.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomUserDTO dto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated())
            return ResponseEntity.ok().body(Map.of("jwt", jwtTokenService.generateToken(DTOConvertor.toCustomUser(dto))));
        else
            return
                    ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please Enter Valid Username And Password!");

    }


}
