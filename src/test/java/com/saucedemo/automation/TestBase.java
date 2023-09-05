package com.saucedemo.automation;

import fw.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import  java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeClass
    public void setUp() {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] a) {
        logger.info("Start tests with method " + m.getName() + "DATA" + Arrays.asList(a));
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: test method " + result.getMethod().getMethodName());
        } else {
            logger.info("FAlLED: with test method " + result.getMethod().getMethodName());
        }
        logger.info("---------------------------------------------------------------");
    }

}
