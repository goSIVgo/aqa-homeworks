package com.gosivgo.lesson11;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsBaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        Allure.step("Открытие сайта MTS.BY", step -> {
            driver.get("https://www.mts.by");
        });

        Allure.step("Закрытие cookie-баннера", step -> {
            closeCookieBanner();
        });
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            attachScreenshot();
            driver.quit();
        }
    }

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Скриншот после выполнения теста", "image/png",
                    new java.io.ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
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
                } catch (Exception ignored) {}
            }

            if (cookieBanner != null) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement acceptButton = cookieBanner.findElement(By.id("cookie-agree"));
                if (acceptButton != null && acceptButton.isDisplayed()) {
                    js.executeScript("arguments[0].click();", acceptButton);
                } else {
                    js.executeScript("arguments[0].style.display = 'none';", cookieBanner);
                }
            }
        } catch (Exception e) {
            System.out.println("Не удалось закрыть cookie-баннер: " + e.getMessage());
        }
    }
}