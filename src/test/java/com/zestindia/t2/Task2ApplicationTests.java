package com.zestindia.t2;
import com.zestindia.t2.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;

import java.util.Random;

import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class Task2ApplicationTests {


    Random random;

//    @BeforeEach
    void initRandom()
    {
        random=new Random();
    }
    @Test
    void init()
    {
        Assertions.assertNotNull(random);
    }



}
