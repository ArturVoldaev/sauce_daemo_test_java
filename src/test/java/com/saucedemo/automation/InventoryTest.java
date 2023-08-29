package com.saucedemo.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class InventoryTest extends Base {
    @BeforeMethod
    public void setup() {
        logoutIfLoggin();
    }

    @Test
    public void inventoryItemsOnPage() {
        login(STANDARD_USER, PASSWORD);
        assertTrue(isElementDisplayed(".inventory_item"));
        assertTrue(countElementOnPage(".inventory_item", 6));
    }

    @Test
    public void sortingItemsAlphabetic() {
        login(STANDARD_USER, PASSWORD);
        List<String> itemAZName = new ArrayList<>();
        List<String> itemZAName = new ArrayList<>();
        List<WebElement> itemsAZ = getInventoryItems();
        extractTextFromWebElementsAndPopulateList(itemAZName, itemsAZ);
        changeSortingZA();
        List<WebElement> itemsZA = getInventoryItems();
        extractTextFromWebElementsAndPopulateList(itemZAName, itemsZA);
        changeSortingAndCompare(itemAZName, itemZAName);
    }

    @Test
    public void addToCart() {
        login(STANDARD_USER, PASSWORD);
        int countAddToCartButtons = getSize(".btn.btn_primary.btn_small.btn_inventory");
        clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
        assertEquals(getSize(".btn.btn_primary.btn_small.btn_inventory"), countAddToCartButtons-1);


    }


}
