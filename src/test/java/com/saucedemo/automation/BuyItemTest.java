package com.saucedemo.automation;

import model.CheckoutUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BuyItemTest extends TestBase {

    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }

    @Test
    public void positiveBuyItemTest() {
        app.getLoginHelper().loginWithStandartUser();
        app.getBaseHelper().clickOnElementByXpath("(//button)[5]");
        String itemName = app.getBaseHelper().getTextFromElement("(//div[@class='inventory_item_name'])[3]");
        String itemPrice = app.getBaseHelper().getTextFromElement("(//div[@class='inventory_item_price'])[3]");
        app.getCartHelper().openCart();
        assertEquals(app.getBaseHelper().getTextFromElement("//div[@class='inventory_item_name']"), itemName);
        assertEquals(app.getBaseHelper().getTextFromElement("//div[@class='inventory_item_price']"), itemPrice);
        app.getBaseHelper().clickOnElement("#checkout");
        app.getCartHelper().fillCheckoutForm(new CheckoutUser()
                .setName("Name")
                .setLastName("LastName")
                .setZipCode("12345")); // Дома можно добавить факера
        app.getBaseHelper().clickOnElement("#continue");
        assertEquals(app.getBaseHelper().getTextFromElement("//div[@class='inventory_item_name']"), itemName);
        assertEquals(app.getBaseHelper().getTextFromElement("//div[@class='inventory_item_price']"), itemPrice);
        app.getBaseHelper().clickOnElement("#finish");
        assertTrue(app.getBaseHelper().getSizeElementsOnPage(".complete-header") > 0);
        app.getBaseHelper().clickOnElement("#back-to-products");
        app.getInvetoryHelper().inventoryPageDisplayed();
    }

}
