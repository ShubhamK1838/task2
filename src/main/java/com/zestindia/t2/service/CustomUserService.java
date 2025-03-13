package com.zestindia.t2.service;

import com.zestindia.t2.dto.CustomUserDTO;

import java.util.Optional;

public interface CustomUserService  {

    CustomUserDTO saveUser(CustomUserDTO userDTO);
    boolean  delete(String id );
    boolean update(String id ,CustomUserDTO userDTO);
    Optional<CustomUserDTO> getUser(String id );
    java.util.List<CustomUserDTO> getAll( );

}
