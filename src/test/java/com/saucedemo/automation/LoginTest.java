package com.saucedemo.automation;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class LoginTest extends Base {
    List<String> userList = List.of(new String[]{STANDARD_USER, PROBLEM_USER, PERF_USER});

//    @Test
//    public void standardUserLogin() {
//        for (String user : userList) {
//            login(user, PASSWORD);
//            assertTrue(isElementDisplayed("#inventory_container"));
//            logout();
//        }
//    }

    @BeforeMethod
    public void setup() {
        logoutIfLoggin();
    }

    @Test
    public void standartUserLogin() {
        login(STANDARD_USER, PASSWORD);
        inventaryContainer();
    }

    @Test
    public void userLogout() {
        login(STANDARD_USER, PASSWORD);
        inventaryContainer();
        logout();
    }


}
