package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListener extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Starting finding element with name " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Element with name " + by + " Find successfully");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        logger.info("WE FAILED WITH MESSAGE " + throwable.getCause());
        logger.info("TRY THIS CAUSE: " + throwable.getCause());
        int index = (int) ((System.currentTimeMillis()/100%123));
        String link = "screenshot/screen" + index + ".png";
    }

    public MyListener(){
        super();
    }
}
