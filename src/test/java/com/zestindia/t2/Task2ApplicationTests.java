package com.zestindia.t2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

//@ExtendWith(MockitoExtension.class)
class Task2ApplicationTests {


    Random random;

    @BeforeEach
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
