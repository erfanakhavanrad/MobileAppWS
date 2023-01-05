package com.example.maws_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserWebServiceEndPointTest {
    private final String CONTEXT_PATH = "/mws";
    private final String EMAIL_ADDRESS = "sertgetkspalorvew1990@gmail.com";
    private final String JSON = "application/json";


    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 1130;

    }

    @Test
    final void testUserLogin() {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", EMAIL_ADDRESS);
        loginDetails.put("passwird", "123");

        Response response = given()
                .contentType(JSON)
                .accept(JSON)
                .body(loginDetails).
                when().post(CONTEXT_PATH + "/users/login")
                .then()
                .statusCode(200)
                .extract().response();

        String authorizationHeader = response.header("Authorization");
        String userId = response.header("UserID");

        assertNotNull(authorizationHeader);
        assertNotNull(userId);
//video number 214 ended
    }

}
