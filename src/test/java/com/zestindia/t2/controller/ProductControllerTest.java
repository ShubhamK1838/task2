package com.zestindia.t2.controller;


import com.zestindia.t2.dto.CustomUserDTO;
import com.zestindia.t2.entity.Product;
import com.zestindia.t2.security.jwt.JwtTokenService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductControllerTest {

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @Autowired
    private JwtTokenService jwtTokenService;

    private String token;
    private String BASE_URL = "http://localhost:";

    @BeforeEach
    void initRestClient() {
        BASE_URL = BASE_URL + port + "/products";
        token = jwtTokenService.generateToken(User.builder().username("admin").password("admin").build());
        restClient = RestClient.
                builder()
                .defaultHeaders((headers) -> {
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    headers.set("Authorization", "Bearer " + token);
                }).build();
    }


    @Test
    void saveProductTest() {
        var data = Product.builder().brand("OnePlus").quantity(2).price(3232f).category(null).category(List.of()).name("BudsZ").description("Apple").build();
        ResponseEntity<Product> responseEntity = restClient.post()
                .uri(BASE_URL)
                .body(data)
                .retrieve()
                .toEntity(Product.class);

        System.out.println(" Save Product Body : " + responseEntity.getBody());

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }


    @Test
    void deleteProductTest() {
        ResponseEntity<Void> responseEntity = restClient.delete()
                .uri(BASE_URL + "/2")
                .retrieve()
                .toBodilessEntity();
        System.out.println(" Delete Product Body : " + responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getProductIfExistsTest() {
        ResponseEntity<Product> productResponseEntity = restClient.get()
                .uri(BASE_URL + "/1")
                .retrieve()
                .toEntity(Product.class);
        Assertions.assertEquals(HttpStatus.OK, productResponseEntity.getStatusCode());
        System.out.println(" Get Product Body : " + productResponseEntity.getBody());
    }

    @Test
    void getProductsTest() {

        ResponseEntity<List<Product>> listResponseEntity =
                restClient.get()
                        .uri(BASE_URL)
                        .retrieve()
                        .toEntity(new ParameterizedTypeReference<List<Product>>() {
                        });

        Assertions.assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        System.out.println(" Get Products Body : " + listResponseEntity.getBody());

    }

    @Test
    void updateProductTest() {
        var data = Product.builder().brand("OnePlus").id("1").quantity(2).price(3232f).category(null).category(List.of()).name("BudsZ").description("Apple").build();

        ResponseEntity responseEntity = restClient.put()
                .uri(BASE_URL)
                .body(data)
                .retrieve()
                .toBodilessEntity();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(" Update Product status code  : " + responseEntity.getStatusCode());


    }

}
