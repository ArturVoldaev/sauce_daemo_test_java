package com.petstore.api.test;

import api.dto.CreateUserRequestDto;
import api.dto.CreateUserResponseDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class okhttpTest {

    Integer id = 666;
    String username = "fdsfsdfsdfd";
    String firstName = "strfdfdsfsdfing";
    String lastName = "fsdfsdfsdfsdfsdf";
    String email = "fsdfsdfsdfsdfsd";
    String password = "fsdfsdfsdfsdfsd";
    String phone = "fsdfsdfsdfsdfsdfsdfsdf";
    Integer userStatus = 0;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void getUserHttp3() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

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
        System.out.println(requestDto);
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto), JSON);

        Request request = new Request.Builder()
                .url("https://petstore.swagger.io/v2/user")
                .post(requestBody)
                .build();


        Response response = client.newCall(request).execute();

        String resJSON = response.body().string();

        Assert.assertEquals(response.code(), 200);

        System.out.println(response);
        CreateUserResponseDto userResponseDto = gson.fromJson(resJSON, CreateUserResponseDto.class);
        System.out.println(resJSON);

        Assert.assertEquals(userResponseDto.getMessage(), id.toString());
        Assert.assertEquals(userResponseDto.getType(), "unknown");
        Assert.assertEquals(userResponseDto.getCode(), 200);




    }
}
