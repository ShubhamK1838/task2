package com.zestindia.t2.dto.convertor;


import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.entity.CustomUser;

public class DTOConvertor {
    public static CustomUserDTO toCustomUserDTO(CustomUser entity) {
       return CustomUserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRoles())
                .build();
    }

    public static CustomUser toCustomUser(CustomUserDTO dto  )
    {
        return CustomUser.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(dto.getRoles())
                .build();
    }
}
