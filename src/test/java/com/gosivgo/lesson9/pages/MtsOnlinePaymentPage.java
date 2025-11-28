package com.gosivgo.lesson9.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsOnlinePaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By BLOCK_TITLE = By.cssSelector("section.pay h2");
    private final By PAYMENT_LOGOS = By.cssSelector("section.pay .pay__partners img");
    private final By MORE_INFO_LINK = By.cssSelector("section.pay a[href*='/help/poryadok-oplaty']");
    private final By PHONE_INPUT = By.id("connection-phone");
    private final By SUM_INPUT = By.id("connection-sum");
    private final By EMAIL_INPUT = By.id("connection-email");
    private final By CONTINUE_BUTTON = By.cssSelector("form#pay-connection button[type='submit']");
    private final By CONNECTION_FORM = By.id("pay-connection");
    private final By CURRENT_SERVICE = By.cssSelector(".select__now");
    private final By PHONE_PREFIX = By.cssSelector("label[for='connection-phone']");
    private final By CURRENCY_SUFFIX = By.cssSelector("label[for='connection-sum']");

    public MtsOnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Заголовок ---
    public String getBlockTitleText() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
        return title.getText().trim();
    }

    // --- Логотипы ---
    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_LOGOS));
        return driver.findElements(PAYMENT_LOGOS);
    }

    // --- Ссылка "Подробнее" ---
    public String getDetailsLinkHref() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(MORE_INFO_LINK));
        return link.getAttribute("href");
    }

    public boolean isDetailsLinkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MORE_INFO_LINK)).isDisplayed();
    }

    // --- Выпадающий список ---
    public String getCurrentService() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CURRENT_SERVICE)).getText().trim();
    }

    // --- Префиксы и суффиксы ---
    public String getPhonePrefix() {
        return driver.findElement(PHONE_PREFIX).getText();
    }

    public String getCurrencySuffix() {
        return driver.findElement(CURRENCY_SUFFIX).getText();
    }

    // --- Плейсхолдеры и атрибуты ---
    public String getPhonePlaceholder() {
        return driver.findElement(PHONE_INPUT).getAttribute("placeholder");
    }

    public String getSumPlaceholder() {
        return driver.findElement(SUM_INPUT).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(EMAIL_INPUT).getAttribute("placeholder");
    }

    public boolean isPhoneFieldRequired() {
        return driver.findElement(PHONE_INPUT).getAttribute("required") != null;
    }

    public boolean isSumFieldRequired() {
        return driver.findElement(SUM_INPUT).getAttribute("required") != null;
    }

    public boolean isEmailFieldRequired() {
        return driver.findElement(EMAIL_INPUT).getAttribute("required") != null;
    }

    public String getPhoneMaxLength() {
        return driver.findElement(PHONE_INPUT).getAttribute("maxlength");
    }

    // --- Работа с формой ---
    public void fillConnectionForm(String phone, String sum, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONNECTION_FORM));

        WebElement phoneField = driver.findElement(PHONE_INPUT);
        WebElement sumField = driver.findElement(SUM_INPUT);
        WebElement emailField = driver.findElement(EMAIL_INPUT);

        phoneField.clear();
        phoneField.sendKeys(phone);

        sumField.clear();
        sumField.sendKeys(sum);

        emailField.clear();
        emailField.sendKeys(email);
    }

    public boolean isContinueButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON)).isDisplayed();
    }

    public boolean isContinueButtonEnabled() {
        return wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON)).isEnabled();
    }
}