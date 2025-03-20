package com.zestindia.t2.controller;


import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.security.jwt.JwtTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.client.HttpClientProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomUserControllerTest {

    @LocalServerPort
    private int port;

    private RestClient restClient;
    private String baseUrl;
    @Autowired
    private HttpClientProperties httpClientProperties;

    @Autowired
    JwtTokenService jwtTokenService;

    private String token;




    @BeforeEach
    void initRestTemplate() {
        token = jwtTokenService.generateToken(User.builder().username("admin").password("admin").build());
        restClient = RestClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    httpHeaders.set("Authorization", "Bearer " + token);
                }).build();

        baseUrl = "http://localhost:" + port;
    }


    @Test
    void userSaveTest()
    {
        var data=User.builder().username("shubham").password("this is my password").roles("USER").build();

        ResponseEntity<CustomUserDTO> customUserDTOResponseEntity=restClient.post()
                .uri(baseUrl + "/users/register")
                .body(data)
                .retrieve()
                .toEntity(CustomUserDTO.class);

        System.out.println(customUserDTOResponseEntity.getBody());
        assertEquals(HttpStatus.CREATED,customUserDTOResponseEntity.getStatusCode());

    }
    @Test
    void saveUserIfExistsTest()
    {
        var data=User.builder().username("admin").password("this is my password").roles("USER").build();

        ResponseEntity<String> response = restClient.post()
                .uri(baseUrl + "/users/register")
                .body(data)
                .retrieve()
                .onStatus((HttpStatus)->HttpStatus.is4xxClientError(), (request, res) -> {
                    System.err.println("User registration failed");
                })
                .toEntity(String.class);  // Capture error response in a String


        System.out.println(response);
        assertEquals(HttpStatus.NOT_ACCEPTABLE,response.getStatusCode());

    }
    @Test
    void getUserTestIfUserExists() {

        ResponseEntity<CustomUserDTO> responseEntity = restClient.get()
                .uri(baseUrl + "/users/1")
                .retrieve()
                .toEntity(CustomUserDTO.class);


        System.out.println("GET USER:ID" + responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void getAllUsersTest() {

        ResponseEntity<List<CustomUserDTO>> responseEntity = restClient.get()
                .uri(baseUrl + "/users")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<CustomUserDTO>>() {});


        System.out.println("GET ALL USERS " + responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }


    @Test
    void deleteUserTest()
    {
        ResponseEntity responseEntity=restClient.delete()
                .uri(baseUrl + "/users/2")
                .retrieve().toBodilessEntity();

        System.out.println("DELETE USER status code " + responseEntity.getStatusCode()   );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void updateUserTest()
    {
        CustomUserDTO customUserDTO = CustomUserDTO.builder().username("user3").password("password").build();

        ResponseEntity responseEntity=restClient.put()
                .uri(baseUrl + "/users/3")
                .body(customUserDTO)
                .retrieve().toBodilessEntity();

        System.out.println("Update USER status code " + responseEntity.getStatusCode()   );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}
