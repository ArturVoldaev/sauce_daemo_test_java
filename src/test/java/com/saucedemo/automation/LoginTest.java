package com.saucedemo.automation;

import model.User;
import org.testng.annotations.*;
import utils.DataProviders;


public class LoginTest extends TestBase {

    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }


    @Test
    @Parameters({"username", "password"})
    public void standardUserLogin(String use, String pas) {
        System.out.println(use + pas);
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

   // @Test(dataProvider = "positiveUsers", dataProviderClass = DataProviders.class)
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
