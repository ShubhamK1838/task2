package com.zestindia.t2.wiremock;


import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class WireMockStandaloneTest {

    private static  WireMockServer wireMockServer;
    private static final RestTemplate restTemplate=new RestTemplate();



    @BeforeAll
    static void initServer()
    {
        wireMockServer=new WireMockServer(8080);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/test")).willReturn(
           aResponse()
                   .withStatus(200)
                   .withHeader("content-type:","application-json")
                   .withBody("<h1> Hello one this is me </h1>" )
        ));
    }

    @Test
    void testServer()
    {
        String output= restTemplate.getForObject("http://localhost:8080/test", String.class);
        System.out.println(output);

    }



//    private static WireMockServer wireMockServer;
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @BeforeAll
//    static void startWireMock() {
//        wireMockServer = new WireMockServer(8080);
//        wireMockServer.start();
//
//        // Mock response
//        wireMockServer.stubFor(get(urlEqualTo("/mocked-endpoint"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Mocked Response\"}")));
//    }
//
//    @AfterAll
//    static void stopWireMock() {
//        wireMockServer.stop();
//    }
//
//    @Test
//    void testMockedApi() {
//        String response = restTemplate.getForObject("http://localhost:8080/mocked-endpoint", String.class);
//        assertEquals("{\"message\": \"Mocked Response\"}", response);
//    }
}
