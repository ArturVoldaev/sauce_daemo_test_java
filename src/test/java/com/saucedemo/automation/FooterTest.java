package com.saucedemo.automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FooterTest extends TestBase {

    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }

    @Test
    public void unloggedFooter() {
        assertTrue(app.getBaseHelper().isElementNotDisplayed(".footer"));
    }

    @Test
    public void inventoryPageFooter() {
        app.getLoginHelper().loginWithStandartUser();
        assertTrue(app.getBaseHelper().isElementDisplayed(".footer"));
    }

    @Test
    public void cartPageFooter() {
        app.getLoginHelper().loginWithStandartUser();
        app.getCartHelper().openCart();
        assertTrue(app.getBaseHelper().isElementDisplayed(".footer"));
    }

}
