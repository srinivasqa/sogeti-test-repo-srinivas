package org.utilities;


import io.cucumber.java.After;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONObject;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import java.awt.Color;

public class Utilities {

    private static WebDriver driver;
    private Actions action;

    public String colorName1=null;
    public String colorName2=null;

    public void launchBrowser(String browserType) {
        switch (browserType) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("enable-automation");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-extensions");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--disable-gpu");
                options.setHeadless(false);
                options.addArguments("start-maximized");
                options.addArguments("disable-infobars");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-in-process-stack-traces");
                options.addArguments("--disable-logging");
                options.addArguments("--log-level=3");

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

    public String getElement(String finalxpath) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/Locator.json")));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JSONObject json = new JSONObject(content);
        String strXpath = json.getString(finalxpath);
        return strXpath;
    }
    public static String getPropertiesFileValue(String key) {
        Properties properties = new Properties();
        String userDirectory = System.getProperty("user.dir");
        try {
            properties.load(new FileInputStream(userDirectory + "\\src\\main\\config\\config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = properties.get(key);
        String value = (String) obj;
        return value;
    }

    public void getUrl(String url) {
        driver.get(url);
     //   btnClick(getElement("btnDeclineOptionalCookies"));
    }

    public void visibilityOfElement(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementToBeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void btnClick(String strPath) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getElement(strPath))));
            if (button.isDisplayed()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", button);
            } else {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void mouseHover(String strPath) {
        WebElement element = driver.findElement(By.xpath(getElement(strPath)));
        visibilityOfElement(element);
        action= new Actions(driver);
        action.moveToElement(element).perform();
    }
    public void mouseHoverAndClick(String strPath) {

        WebElement element = driver.findElement(By.xpath(getElement(strPath)));
        visibilityOfElement(element);
        action= new Actions(driver);
        action.moveToElement(element).click().perform();
    }

    public void verifyText(String text,String strPath ){

        WebElement element = driver.findElement(By.xpath(getElement(strPath)));
        visibilityOfElement(element);
        String strActualtext = element.getText();

        assertEquals(text, strActualtext);

    }

    public void i_verify_color_of_elements(String element1,String element2){

        WebElement eleServices = driver.findElement(By.xpath(getElement(element1)));
        WebElement eleAutomation = driver.findElement(By.xpath(getElement(element2)));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOf(eleAutomation));
        wait.until(ExpectedConditions.visibilityOf(eleServices));

        String strColor1 = eleServices.getCssValue("color");
        String strColor2 = eleAutomation.getCssValue("color");

        Color color1 = ColorFinder.parseRgbaString(strColor1);
        Color color2 = ColorFinder.parseRgbaString(strColor2);

         colorName1 = ColorFinder.getColorNameFromRGBA(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha());
         colorName2 = ColorFinder.getColorNameFromRGBA(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha());


    }

    public void scrollDownUntilElement(String target) {
        WebElement element = driver.findElement(By.xpath(getElement(target)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void sendKeys(String strPath, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        WebElement element = driver.findElement(By.xpath(strPath));
        visibilityOfElement(element);
        element.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        js.executeScript("arguments[0].value = arguments[1];", element, text);
    }

    public void switchToCaptchaFrameandClick(){

        WebElement iframe = driver.findElement(By.xpath(getElement("captchaFrame")));
        driver.switchTo().frame(iframe);

        WebElement element = driver.findElement(By.cssSelector(getElement("captchaCheckBox")));
        element.click();
    }

    public void collectLinks(String strPath) {

        WebElement countryListDiv = driver.findElement(By.xpath(getElement(strPath)));
        List<WebElement> links = countryListDiv.findElements(By.tagName("a"));

        String originalWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                System.out.println("Testing link: " + href);

                // Click the link
                link.click();

                // Switch to the new tab/window
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandle.equals(originalWindow)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getElement("btnDeclineOptionalCookies")))).click();

                wait.until(ExpectedConditions.titleIs(driver.getTitle()));

                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals(href)) {
                    System.out.println("Successfully opened: " + currentUrl);
                } else {
                    System.out.println("Failed to open: " + href);
                }

            }
        }
    }


    public void checkAllLinks(String strPath) {

        try {
            WebElement countryListDiv = driver.findElement(By.xpath(getElement(strPath)));
            List<WebElement> links = countryListDiv.findElements(By.tagName("a"));


            // Check each link
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && !href.isEmpty()) {
                    System.out.println("Checking link: " + href);
                    int responseCode = getResponseCode(href);
                    Assert.assertTrue(responseCode == 200 );
                    System.out.println("Result: "+href + " returned positive response");
                }
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private int getResponseCode(String urlString) {
        int responseCode = -1;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }



}
