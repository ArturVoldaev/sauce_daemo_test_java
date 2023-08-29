package com.saucedemo.automation;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTest extends Base {

    @BeforeMethod
    public void setup() {
        logoutIfLoggin();
    }

    @Test
    public void logoutFooter() {
        Assert.assertTrue(isElementNotDisplayed(".footer"));
    }

    @Test
    public void inventoryPageFooter() {
        login(STANDARD_USER, PASSWORD);
        Assert.assertTrue(isElementDisplayed(".footer"));
    }

    @Test
    public void cartPageFooter() {
        login(STANDARD_USER, PASSWORD);
        openCart();
        Assert.assertTrue(isElementDisplayed(".footer"));

    }

}
