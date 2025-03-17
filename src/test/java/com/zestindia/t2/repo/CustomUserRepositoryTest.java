package com.zestindia.t2.repo;

import com.zestindia.t2.entity.CustomUser;
import com.zestindia.t2.repository.CustomUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomUserRepositoryTest {


    @Mock
    private CustomUserRepository repository;

    private CustomUser mockUser;

    @BeforeEach
    void  initUser()
    {
        mockUser=Mockito.mock(CustomUser.class);

    }

    @Test
    void saveUserTest()
    {

        Mockito.when(mockUser.getId()).thenReturn("ID");
        Mockito.when(repository.save(mockUser)).thenReturn(mockUser);

        assertNotNull(repository.save(mockUser));
        assertNotNull(mockUser.getId());
    }
    @Test
    void deleteUserTest() {
        Mockito.doNothing().when(repository).delete(mockUser);
        repository.delete(mockUser);
    }

    @Test
    void getUserTestIfUserExists()
    {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(mockUser));
        assertNotNull(repository.findById(Mockito.anyString()));
    }
    @Test
    void getUserTestIfUserNotExists()
    {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        assertFalse(repository.findById(Mockito.anyString()).isPresent());
    }

    @Test
    void updateUserWhenUserPresent()
    {
        Mockito.when(repository.findAll()).thenReturn(Mockito.mock(ArrayList.class));
        assertNotNull(repository.findAll());

    }




}
