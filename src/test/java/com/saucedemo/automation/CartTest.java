package com.saucedemo.automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends TestBase {

    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }

    @Test
    public void removeAddedItemFromCart() {
        app.getLoginHelper().loginWithStandartUser();
        app.getBaseHelper().clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
        app.getBaseHelper().pause(300);
        app.getCartHelper().openCart();
        assertEquals(app.getBaseHelper().getSizeElementsOnPage(".cart_item"), 1);
        app.getBaseHelper().clickOnElement("#remove-sauce-labs-backpack");
        assertEquals(app.getBaseHelper().getSizeElementsOnPage(".cart_item"), 0);
    }

}
