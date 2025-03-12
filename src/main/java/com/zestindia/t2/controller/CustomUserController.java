package com.zestindia.t2.controller;


import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.enums.Roles;
import com.zestindia.t2.service.CustomUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CustomUserController {


    private final CustomUserService userService;

    public CustomUserController(CustomUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody CustomUserDTO customUser) {
        CustomUserDTO user = userService.saveUser(customUser);
        if (user != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable String id) {
        boolean updated = userService.delete(id);
        if (updated)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String id, @RequestBody CustomUserDTO userDto) {
        boolean updated = userService.update(id, userDto);

        if (updated)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<CustomUserDTO> optionalCustomUser = userService.getUser(id);

        if (optionalCustomUser.isPresent())
            return
                    ResponseEntity.status(HttpStatus.OK).body(optionalCustomUser.get());
        else
            return
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


}
