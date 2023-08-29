package com.saucedemo.automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends Base {

    @BeforeMethod
    public void setup() {
        logoutIfLoggin();
    }

    @Test
    public void removeAddedItemFromCart() {
        login(STANDARD_USER, PASSWORD);
        clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
        openCart();

        clickOnElement("btn.btn_secondary.btn_small.cart_button");
    }




}
