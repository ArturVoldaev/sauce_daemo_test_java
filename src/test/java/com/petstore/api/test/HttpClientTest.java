package com.petstore.api.test;

import api.dto.CreateUserErrorDto;
import api.dto.CreateUserNameErrorDto;
import api.dto.CreateUserRequestDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientTest {
    Integer id = 123;
    String username = "fdsfsdfsdfd";
    String firstName = "strfdfdsfsdfing";
    String lastName = "fsdfsdfsdfsdfsdf";
    String email = "fsdfsdfsdfsdfsd";
    String password = "fsdfsdfsdfsdfsd";
    String phone = "fsdfsdfsdfsdfsdfsdfsdf";
    Integer userStatus = 0;
    @Test
    public void  createUserHttpClientTest() throws IOException {


        Response response = Request.Post("https://petstore.swagger.io/v2/user")
                .bodyString("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"username\": \"" + username + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\",\n" +
                        "  \"lastName\": \"" + lastName + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"userStatus\": " + userStatus + "\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();


        System.out.println(response);

        String responseJson = response.returnContent().asString();

        System.out.println(responseJson);

        JsonElement element = JsonParser.parseString(responseJson);

        JsonElement message = element.getAsJsonObject().get("message");
        System.out.println(message.getAsInt());
        Assert.assertEquals(message.getAsInt(), id);


    }

    @Test
    public void  createUserHttpClientTestSecond() throws IOException {
        Gson gson = new Gson();
        CreateUserRequestDto requestDto = CreateUserRequestDto.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus).build();

        Response response = Request.Post("https://petstore.swagger.io/v2/user").
                bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void  createUserNameNegative() throws IOException {
        Gson gson = new Gson();
        CreateUserRequestDto requestDto = CreateUserRequestDto.builder()
                .username("asdasd")
                .build();


        String apiUrl = "https://petstore.swagger.io/v2/user" + requestDto.getUsername();

//        Response response = Request.Get(apiUrl)
//                .addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
//                .execute();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);

        httpGet.setHeader("accept", "application/json");

        HttpResponse httpResponse = httpClient.execute(httpGet);
        ;
        System.out.println(httpResponse.getEntity().getContent());
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream inputStream = httpResponse.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        CreateUserErrorDto errorDto = gson.fromJson(stringBuilder.toString(), CreateUserErrorDto.class);
//        System.out.println(errorDto.getCode());
        Assert.assertEquals(errorDto.getCode(), 404);

    }
}
