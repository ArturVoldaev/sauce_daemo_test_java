package com.petstore.api.test;

import api.dto.CreateUserRequestDto;
import api.dto.CreateUserResponseDto;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class assureTest {

    Integer id = 789;
    String username = "fdsfsdfsdfd";
    String firstName = "strfdfdsfsdfing";
    String lastName = "fsdfsdfsdfsdfsdf";
    String email = "fsdfsdfsdfsdfsd";
    String password = "fsdfsdfsdfsdfsd";
    String phone = "fsdfsdfsdfsdfsdfsdfsdf";
    Integer userStatus = 0;

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
        RestAssured.basePath = "user";
    }

    @Test
    public void createdUserAssure() {
        CreateUserRequestDto requestDto = CreateUserRequestDto.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus)
                .build();
        CreateUserResponseDto createUserResponse = RestAssured.given()
                .contentType("application/json")
                .body(requestDto)
                .post()
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(CreateUserResponseDto.class);

        System.out.println(requestDto.getId());
        System.out.println(requestDto);

        String msg =  RestAssured.given()
                .contentType("application/json")
                .body(requestDto)
                .post()
                .then()
                .assertThat().body(Matchers.containsString("message"))
                .extract().path("message");
        System.out.println(msg);
    }
}
