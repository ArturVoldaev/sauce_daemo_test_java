package fw;

import model.User;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class LoginHelper extends BaseHelper {

    public LoginHelper(WebDriver driver) {
        super(driver);
    }




    public void login(User user) {
        fillField(user.getUserName(), "#user-name");
        fillField(user.getPassword(), "#password");
        clickOnLoginButton();
    }

    private void clickOnLoginButton() {
        clickOnElement("#login-button");
    }

    public void logout() {
        clickOnElement("#react-burger-menu-btn");
        assertTrue(isElementDisplayed(".bm-menu-wrap"));
        clickLogoutBtn();
        assertTrue(isElementDisplayed("#login-button"));
    }

    public boolean isHamburgerMenuOpened() {
        return getSizeElementsOnPage(".bm-item-list") > 0;
    }

    public boolean isUserLoggedIn() {
        return getSizeElementsOnPage("#react-burger-menu-btn") > 0;
    }

    public void clickLogoutBtn() {
        clickOnElement("#logout_sidebar_link");
    }

    public void logoutUserIfLogged() {
        if (isUserLoggedIn()) {
            logout();
        }
        if (isHamburgerMenuOpened()) {
            clickLogoutBtn();
        }
    }

    public void loginWithStandartUser() {
        login(new User().setUserName(STANDARD_USER).setPassword(PASSWORD));
    }

    public void loginErrorMessageIsDisplayed() {
        assertTrue(isElementDisplayed(".error-message-container.error"));
    }

}
