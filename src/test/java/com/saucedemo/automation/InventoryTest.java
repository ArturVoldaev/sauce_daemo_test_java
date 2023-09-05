package com.saucedemo.automation;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryTest extends TestBase {
    @BeforeMethod
    public void setup() {
        app.getLoginHelper().logoutUserIfLogged();
    }

    @Test
    public void inventoryItemsOnPage() {
        app.getLoginHelper().loginWithStandartUser();
        assertTrue(app.getBaseHelper().isElementDisplayed(".inventory_item"));
        assertTrue(app.getBaseHelper().countElementsOnPage(".inventory_item", 6));
    }

    @Test
    public void sortingItemsAlphabetic() {
        app.getLoginHelper().loginWithStandartUser();
        List<String> itemAZName = new ArrayList<>();
        List<String> itemZAName = new ArrayList<>();

        List<WebElement> itemsAZ = app.getInvetoryHelper().getInventoryItems();
        app.getSortingHelper().extractTextFromWebElementsAndPopulateList(itemAZName, itemsAZ);
        app.getSortingHelper().changeSortingZA();
        List<WebElement> itemsZA = app.getInvetoryHelper().getInventoryItems();
        app.getSortingHelper().extractTextFromWebElementsAndPopulateList(itemZAName, itemsZA);
        app.getSortingHelper().changeSortingAndCompare(itemAZName, itemZAName);
    }

    @Test
    public void sortingByPrice() {
        System.out.println("СДЕЛАЙ МЕНЯ ДОМА");
    }

    @Test
    public void addToCart() {
        app.getLoginHelper().loginWithStandartUser();
        int countAddToCartBtns = app.getInvetoryHelper().getCountAddInventoryBtns();
        app.getBaseHelper().clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
        app.getBaseHelper().pause(100);
        assertEquals(app.getInvetoryHelper().getCountAddInventoryBtns(),
                countAddToCartBtns - 1);
        assertEquals(app.getInvetoryHelper().getShoppingCartBadgeCount(), 1);
        app.getCartHelper().openCart();
        assertEquals(app.getBaseHelper().getSizeElementsOnPage(".cart_item"), 1);

        // cleanup
        app.getBaseHelper().clickOnElement("#continue-shopping");
        app.getBaseHelper().clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
    }

}
