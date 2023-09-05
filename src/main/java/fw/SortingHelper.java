package fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SortingHelper extends BaseHelper{

    public SortingHelper(WebDriver driver) {
        super(driver);
    }

    public void changeSortingAndCompare(List<String> itemAZName, List<String> itemZAName) {
        Comparator<String> reverseComparator = Comparator.reverseOrder();
        itemAZName.sort(reverseComparator); //AZ -> ZA
        assertEquals(itemAZName, itemZAName);
    }

    public void changeSortingZA() {
        clickOnElement(".product_sort_container");
        clickOnElement("option[value='za']");
    }

    public void extractTextFromWebElementsAndPopulateList(List<String> itemZAName, List<WebElement> itemsZA) {
        for (WebElement item : itemsZA) {
            if (item != null) {
                itemZAName.add(item.getText());
            }
        }
    }

}
