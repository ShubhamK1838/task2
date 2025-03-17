package com.zestindia.t2.service;

import com.zestindia.t2.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

//    @Spy
//    private JwtTokenService tokenService;
//
//    @Test
//    @Disabled
//    void jwtTokenTest() {
//        String str = tokenService.generateToken(User.builder().username("user").password("pass1").build());
//        Assertions.assertNotNull(str);
//    }

}
