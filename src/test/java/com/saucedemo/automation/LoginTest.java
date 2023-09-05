package com.saucedemo.automation;

import model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviders;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }

    @Test
    public void standardUserLogin() {
        app.getLoginHelper().loginWithStandartUser();
        app.getInvetoryHelper().inventoryPageDisplayed();
    }

    @Test
    public void userLogout() {
        app.getLoginHelper().loginWithStandartUser();
        app.getInvetoryHelper().inventoryPageDisplayed();
        app.getLoginHelper().logout();
    }




    // test, test -> bad practice here only to show
    @Test(dataProvider = "negativeUsers", dataProviderClass = DataProviders.class)
    public void userWithNotCorrectDataDataProvider(String userName, String userPassword) {
        app.getLoginHelper().login(new User()
                .setUserName(userName)
                .setPassword(userPassword));
        app.getLoginHelper().loginErrorMessageIsDisplayed();
    }

    @Test(dataProvider = "positiveUsers", dataProviderClass = DataProviders.class)
    public void standardUserLoginWithDataProvider(String userName, String userPassword) {
        app.getLoginHelper().login(new User()
                .setUserName(userName)
                .setPassword(userPassword));
        app.getInvetoryHelper().inventoryPageDisplayed();
    }

    @Test(dataProvider = "negativeUsersFromCVS", dataProviderClass = DataProviders.class)
    public void userWithNotCorrectDataFromFile(User user) {
        app.getLoginHelper().login(user);
        app.getLoginHelper().loginErrorMessageIsDisplayed();
    }
}
