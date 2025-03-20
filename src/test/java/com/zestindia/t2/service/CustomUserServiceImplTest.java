package com.zestindia.t2.service;

import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.enums.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomUserServiceImplTest {


    @Autowired
    private com.zestindia.t2.service.impl.CustomUserServiceImpl userService;

    private CustomUserDTO mockUser;

    @BeforeEach
    void initMockUser() {
        MockitoAnnotations.openMocks(this);
        mockUser = CustomUserDTO.builder()
                .username("Tushar")
                .id(UUID.randomUUID().toString())
                .password("tushar'spass")
                .roles("USER,ADMIN")
                .build();
    }

    @Test
    void save_Update_Delete_Get_User_IfExists_Test() {

        var dto = userService.saveUser(mockUser);
        dto.setRoles("USER,ADMIN");

        assertNotNull(dto);
        assertNotNull(userService.getUser(dto.getId()));
        assertTrue(userService.update(dto.getId(), dto));
        assertDoesNotThrow(() -> {
            userService.loadUserByUsername(dto.getUsername());
        });
        assertNotNull(userService.delete(dto.getId()));

    }

    @Test
    void deleteUserIfNotExists() {
        assertFalse(userService.delete(anyString()));
    }

    @Test
    void updateUserIfNotExists() {
        assertFalse(userService.update(anyString(), mockUser));
    }

    @Test
    void getUserIfNotExists() {
        Optional<CustomUserDTO> optional = userService.getUser(anyString());

        assertFalse(optional.isPresent());
    }

    @Test
    void getAllCustomUserTest() {
        assertNotNull(userService.getAll());
    }

    @Test
    void getUserByUsernameIfUserNotExists() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(anyString());
        });
    }


}
