package com.saucedemo.automation;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

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

    //HomeWork
    @Test
    public void sortingItemsPrice() {
        login(STANDARD_USER, PASSWORD);
        changePriceSorting(".product_sort_container", "option[value='lohi']");
        List<String> itemHiPrice = new ArrayList<>();
        List<String> itemLoPrice = new ArrayList<>();
        List<WebElement> itemsHi = getInventoryItemsPrice();
        extractTextFromWebElementsAndPopulateList(itemHiPrice, itemsHi);
        changePriceSorting(".product_sort_container", "option[value='hilo']");
        List<WebElement> itemsLo = getInventoryItemsPrice();
        extractTextFromWebElementsAndPopulateList(itemLoPrice, itemsLo);
        Collections.reverse(itemHiPrice);
        assertEquals(itemHiPrice, itemLoPrice);
    }


    @Test
    public void addToCart() {
        login(STANDARD_USER, PASSWORD);
        int countAddToCartButtons = getSize(".btn.btn_primary.btn_small.btn_inventory");
        clickOnElement(".btn.btn_primary.btn_small.btn_inventory");
        assertEquals(getSize(".btn.btn_primary.btn_small.btn_inventory"), countAddToCartButtons-1);
    }
}
