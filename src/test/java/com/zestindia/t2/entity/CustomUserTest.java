package com.zestindia.t2.entity;

import com.zestindia.t2.enums.Roles;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomUserTest {


    @Test
    void getAuthoritiesTest()
    {
        var user=CustomUser.builder()
                .build();
        assertNotNull(user.getAuthorities());

    }
}
