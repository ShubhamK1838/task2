package com.zestindia.t2.dto;


import com.zestindia.t2.dto.convertor.DTOConvertor;
import com.zestindia.t2.entity.CustomUser;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DTOTest {

    @ParameterizedTest
    @CsvSource("a1b1,Rohan" +
            "a2b1,Tushar")
    void dtoToEntityAndEntityToDTO_Test(String id, String name )
    {
        CustomUserDTO userDTO=CustomUserDTO.builder().id(id).username(name).password(UUID.randomUUID().toString()).build();
        CustomUser entity=DTOConvertor.toCustomUser(userDTO);

        assertEquals(userDTO.getId(),entity.getId());
        assertEquals(userDTO.getUsername(),entity.getUsername());
        assertEquals(userDTO.getRoles(),entity.getRoles());
        assertEquals(userDTO.getUsername(),entity.getPassword());

    }
}
