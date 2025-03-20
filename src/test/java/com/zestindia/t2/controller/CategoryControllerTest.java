package com.zestindia.t2.controller;


import com.zestindia.t2.entity.Category;
import com.zestindia.t2.entity.CustomUser;
import com.zestindia.t2.security.jwt.JwtTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CategoryControllerTest {

    @Autowired
    private JwtTokenService jwtTokenService;

    private final String BASE_URL = "http://localhost:8081/categories";

    private static RestClient restClient ;
    private static String token;

    @BeforeEach
    void setUp()
    {
        token=jwtTokenService.generateToken(CustomUser.builder().password("admin").username("admin").build());

        restClient=RestClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.set("Authorization", "Bearer "+token);
                }).build();
    }

    @Test
    void saveCategoryTest()
    {
        var data= Category.builder().name("Home").build();
        ResponseEntity<Category> categoryResponseEntity=restClient.post()
                .uri(BASE_URL)
                .body(data)
                .retrieve()
                .toEntity(Category.class);

        Assertions.assertEquals(HttpStatus.CREATED, categoryResponseEntity.getStatusCode());
        System.out.println(" Save category body : " + categoryResponseEntity.getBody());
    }

    @Test
    void deleteCategoryTest()
    {
        ResponseEntity<Void> responseEntity=restClient.delete()
                .uri(BASE_URL+"/1")
                .retrieve()
                .toBodilessEntity();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println("Delete Category Status code : "+responseEntity.getStatusCode());
    }





}
