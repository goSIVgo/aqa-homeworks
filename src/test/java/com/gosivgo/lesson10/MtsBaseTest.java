package com.gosivgo.lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsBaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;

        driver.get("https://www.mts.by");


        closeCookieBanner();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void closeCookieBanner() {
        try {

            List<By> cookieSelectors = List.of(
                    By.cssSelector(".cookie"),
                    By.cssSelector(".cookie.show"),
                    By.cssSelector("[class*='cookie']"),
                    By.cssSelector(".cookie-banner"),
                    By.cssSelector(".cookie-notification")
            );

            WebElement cookieBanner = null;
            for (By selector : cookieSelectors) {
                try {
                    List<WebElement> elements = driver.findElements(selector);
                    if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                        cookieBanner = elements.get(0);
                        break;
                    }
                } catch (Exception e) {

                }
            }

            if (cookieBanner != null) {

                List<By> buttonSelectors = List.of(
                        By.cssSelector(".cookie__button"),
                        By.cssSelector(".cookie-button"),
                        By.cssSelector("[class*='cookie'] button"),
                        By.cssSelector("button[onclick*='cookie']"),
                        By.xpath(".//button[contains(text(), 'Принять')]"),
                        By.xpath(".//button[contains(text(), 'Согласен')]"),
                        By.xpath(".//button[contains(text(), 'OK')]"),
                        By.xpath(".//button[contains(text(), 'Accept')]")
                );

                WebElement acceptButton = null;
                for (By selector : buttonSelectors) {
                    try {
                        List<WebElement> buttons = cookieBanner.findElements(selector);
                        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
                            acceptButton = buttons.get(0);
                            break;
                        }
                    } catch (Exception e) {

                    }
                }

                if (acceptButton != null) {

                    js.executeScript("arguments[0].click();", acceptButton);

                    Thread.sleep(1000);
                } else {

                    js.executeScript("arguments[0].style.display = 'none';", cookieBanner);
                }
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not found or could not be closed: " + e.getMessage());
        }
    }
}