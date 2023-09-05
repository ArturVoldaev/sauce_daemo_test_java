package fw;

import model.CheckoutUser;
import org.openqa.selenium.WebDriver;

public class CartHelper extends BaseHelper{

    public CartHelper(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        clickOnElement("#shopping_cart_container");
    }

    /**
     * @param checkoutUser
     */
    public void fillCheckoutForm(CheckoutUser checkoutUser) {
        fillField(checkoutUser.getName(), " #first-name");
        fillField(checkoutUser.getLastName(), " #last-name");
        fillField(checkoutUser.getZipCode(), " #postal-code");
    }
}
