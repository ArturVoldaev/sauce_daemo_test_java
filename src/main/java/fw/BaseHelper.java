package fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {

    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }


    public final String STANDARD_USER = "standard_user";
    public final String LOCKED_OUT_USER = "locked_out_user";
    public final String PROBLEM_USER = "problem_user";
    public final String PERF_USER = "performance_glitch_user";
    public final String PASSWORD = "secret_sauce";

    public void fillField(String text, String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
        driver.findElement(By.cssSelector(cssSelector)).clear();
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    public boolean isElementDisplayed(String cssLocator) {
        pause(50);
        return getSizeElementsOnPage(cssLocator) > 0;
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementNotDisplayed(String cssLocator) {
        return getSizeElementsOnPage(cssLocator) == 0;
    }

    public boolean countElementsOnPage(String cssSelector, int expectedCount) {
        return getSizeElementsOnPage(cssSelector) == expectedCount;
    }

    public int getSizeElementsOnPage(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).size();
    }

    public String getTextFromElement(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void clickOnElementByXpath(String xpathExpression) {
        driver.findElement(By.xpath(xpathExpression)).click();
    }

    public void clickOnElement(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }


}
