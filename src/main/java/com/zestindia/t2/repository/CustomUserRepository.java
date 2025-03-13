package com.zestindia.t2.repository;

import com.zestindia.t2.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, String> {

    Optional<CustomUser> findByUsername(String username);
}
