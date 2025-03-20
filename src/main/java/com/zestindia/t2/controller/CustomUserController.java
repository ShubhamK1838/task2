package com.zestindia.t2.controller;


import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.service.CustomUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class CustomUserController {


    private final CustomUserService userService;

    public CustomUserController(CustomUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody CustomUserDTO customUser) {
        CustomUserDTO user = userService.saveUser(customUser);
        if (user != null) return ResponseEntity.status(HttpStatus.CREATED).body(user);
        else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable String id, Principal principal) {
        CustomUserDTO customUserDTO = userService.getUser(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        boolean rst = principal.getName().equals(customUserDTO.getUsername());

        if (rst || customUserDTO.getRoles().contains("ADMIN"))
            return userService.delete(id)
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        throw new RuntimeException("Invalid Credential ");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String id, @RequestBody CustomUserDTO userDto) {
        return userService.update(id, userDto) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<CustomUserDTO> optionalCustomUser = userService.getUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(optionalCustomUser.orElseThrow(() -> new UsernameNotFoundException("User Not Found")));

    }

}
