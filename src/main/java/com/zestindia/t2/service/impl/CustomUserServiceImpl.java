package com.zestindia.t2.service.impl;

import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.dto.convertor.DTOConvertor;
import com.zestindia.t2.entity.CustomUser;
import com.zestindia.t2.exception.custom.UserAlreadyExistsException;
import com.zestindia.t2.repository.CustomUserRepository;
import com.zestindia.t2.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImpl implements CustomUserService, UserDetailsService {

    private final CustomUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomUserServiceImpl(CustomUserRepository repository) {
        userRepository = repository;
    }

    @Override
    public CustomUserDTO saveUser(CustomUserDTO userDTO) {
        return DTOConvertor.toCustomUserDTO(save(DTOConvertor.toCustomUser(userDTO)));
    }

    @Override
    public boolean delete(String id) {
        return deleteUser(id);
    }

    @Override
    public boolean update(String id, CustomUserDTO userDTO) {
        return update(id, DTOConvertor.toCustomUser(userDTO));
    }

    @Override
    public Optional<CustomUserDTO> getUser(String id) {
        Optional<CustomUser> customUser = getEntity(id);

        if(customUser.isPresent())
        {
            return Optional.of(DTOConvertor.toCustomUserDTO(customUser.get()) );
        }
        return Optional.empty();
    }

    @Override
    public List<CustomUserDTO> getAll() {
        return
                getAllEntities().stream()
                        .map(ele -> DTOConvertor.toCustomUserDTO(ele))
                        .collect(Collectors.toList());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("The Username Not Found "));
    }


    private CustomUser save(CustomUser customUser) {
        customUser.setId(UUID.randomUUID().toString());
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        if(userRepository.findByUsername(customUser.getUsername()).isPresent())
            throw  new UserAlreadyExistsException("User Already Exists With Given Username: "+ customUser.getUsername());

        return userRepository.save(customUser);
    }

    private Optional<CustomUser> getEntity(String id) {
        return userRepository.findById(id);
    }

    private List<CustomUser> getAllEntities() {
        return userRepository.findAll();
    }

    private boolean deleteUser(String id) {
        Optional<CustomUser> optionalCustomUser = getEntity(id);

        if (optionalCustomUser.isPresent()) {
            userRepository.delete(optionalCustomUser.get());
            return true;
        } else return false;
    }

    private boolean update(String id, CustomUser user) {
        Optional<CustomUser> optionalCustomUser = getEntity(id);

        if (optionalCustomUser.isPresent()) {

            CustomUser entity = optionalCustomUser.get();
            entity.setRoles(user.getRoles() != null ? user.getRoles() : entity.getRoles());
            String updatedPassword = user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : entity.getPassword();
            entity.setPassword(updatedPassword);
            entity.setUsername(user.getUsername() != null ? user.getUsername() : entity.getUsername());
            userRepository.save(entity);
            return true;
        } else return false;
    }


}
