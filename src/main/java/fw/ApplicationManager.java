package fw;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.MyListener;

import java.time.Duration;


public class ApplicationManager {

    EventFiringWebDriver  driver;
    //WebDriver driver;

    CartHelper cartHelper;
    InvetoryHelper invetoryHelper;
    LoginHelper loginHelper;
    SortingHelper sortingHelper;
    BaseHelper baseHelper;

    public CartHelper getCartHelper() {
        return cartHelper;
    }

    public InvetoryHelper getInvetoryHelper() {
        return invetoryHelper;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }

    public SortingHelper getSortingHelper() {
        return sortingHelper;
    }

    public BaseHelper getBaseHelper() {
        return baseHelper;
    }

    public void stop() {
        driver.quit();
    }

    public void init() {
        final String URL = "https://www.saucedemo.com/";
        driver =new EventFiringWebDriver(new ChromeDriver());
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        cartHelper = new CartHelper(driver);
        invetoryHelper = new InvetoryHelper(driver);
        loginHelper = new LoginHelper(driver);
        sortingHelper = new SortingHelper(driver);
        baseHelper = new BaseHelper(driver);

        driver.register(new MyListener());
    }

}