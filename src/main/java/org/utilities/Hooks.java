package org.utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks extends Utilities {

    @Before("@sogeti_UI")
    public void setup() {
        switch (getPropertiesFileValue("browser")) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":

                WebDriverManager.edgedriver().clearDriverCache().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver();
                break;
            default:
                break;
        }
        driver.manage().deleteAllCookies();
        driver.manage().getCookies();
        driver.manage().window().maximize();
    }

    @After("@sogeti_UI")
    public void teardown(){

        driver.close();
        driver.quit();
    }
}
