package com.saucedemo.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Base {
    WebDriver driver = new FirefoxDriver();

    String URL = "https://www.saucedemo.com/";
    final String STANDARD_USER = "standard_user";
    final String LOCKED_OUT_USER = "locked_out_user";
    final String PROBLEM_USER = "problem_user";
    final String PERF_USER = "performance_glitch_user";

    String PASSWORD = "secret_sauce";

    @BeforeClass
    public void setUp() {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void login(String userName, String password) {
        fillField(userName, By.cssSelector("#user-name"));
        fillField(password, By.cssSelector("#password"));
        clickOnLoginButton();
    }

    public void clickOnLoginButton() {
        clickOnElement("#login-button");
    }

    private void fillField(String userName, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(userName);
    }

    public boolean isElementDisplayed(String cssLocator) {
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }

    public void logout() {
        driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        Assert.assertTrue(isElementDisplayed(".bm-menu-wrap"));
        clickLogoutBtn();
        Assert.assertTrue(isElementDisplayed("#login-button"));
    }

    public void clickOnElement (String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public boolean isUserLoggedIn() {
        return  getSize("#react-burger-menu-btn") > 0;
    }
    public boolean isHamburgerMenuOpened() {
        return  getSize(".bm-item-list") > 0;
    }
    public void inventaryContainer() {
        assertTrue(isElementDisplayed("#inventory_container"));
    }

    public void  pause (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickLogoutBtn() {
        clickOnElement("#logout_sidebar_link");
    }

    public void logoutIfLoggin() {
        if (isUserLoggedIn()) {
            logout();
        }
        if (isHamburgerMenuOpened()) {
            clickLogoutBtn();
        }
    }

    public boolean isElementNotDisplayed (String cssLocator) {
        pause(500);
        return  getSize(cssLocator) == 0;
    }

    public int getSize(String cssLocator) {
        return driver.findElements(By.cssSelector(cssLocator)).size();
    }

    public void openCart() {
        clickOnElement("#shopping_cart_container");
    }

    public boolean countElementOnPage(String amount, int exprx) {
        return getSize(amount) == exprx;
    }

    public List<WebElement> getElementsItem() {
        return driver.findElements(By.cssSelector(".inventory_item_name"));
    }

    public void changeSortingZA() {
        clickOnElement(".product_sort_container");
        clickOnElement("option[value='za']");
    }

    public void changeSortingAndCompare(List<String> itemAZName, List<String> itemZAName) {
        Comparator<String> reverseComparator = Comparator.reverseOrder();
        itemAZName.sort(reverseComparator); //AZ -> ZA
        System.out.println("=====================================");
        System.out.println(Arrays.toString(new List[]{itemAZName}));
        assertEquals(itemAZName, itemZAName);
    }

    public void extractTextFromWebElementsAndPopulateList(List<String> itemZAName, List<WebElement> itemsZA) {
        for (WebElement item : itemsZA) {
            if (item != null) {
                itemZAName.add(item.getText());
            }
        }
    }

    public List<WebElement> getInventoryItems() {
        return driver.findElements(By.cssSelector(".inventory_item_name"));
    }

    public List<WebElement> getInventoryItemsPrice() {
        return driver.findElements(By.cssSelector(".inventory_item_price"));
    }

    public void changePriceSorting(String element, String value) {
        clickOnElement(element);
        clickOnElement(value);
    }

}
