package com.gosivgo.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MtsOnlinePaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    // Основные селекторы
    private final By BLOCK_TITLE = By.cssSelector("section.pay h2");
    private final By PAYMENT_LOGOS = By.cssSelector("section.pay .pay__partners img");
    private final By MORE_INFO_LINK = By.cssSelector("section.pay a[href*='/help/poryadok-oplaty']");
    private final By CURRENT_SERVICE = By.cssSelector(".select__now");

    // Селекторы для выпадающего списка услуг
    private final By SERVICE_DROPDOWN = By.cssSelector(".select__header");
    private final By SERVICE_OPTIONS = By.cssSelector(".select__option");

    // Селекторы для форм разных услуг
    private final By CONNECTION_FORM = By.id("pay-connection");
    private final By INTERNET_FORM = By.id("pay-internet");
    private final By INSTALMENT_FORM = By.id("pay-instalment");
    private final By ARREARS_FORM = By.id("pay-arrears");

    // Селекторы полей для "Услуги связи"
    private final By CONNECTION_PHONE = By.id("connection-phone");
    private final By CONNECTION_SUM = By.id("connection-sum");
    private final By CONNECTION_EMAIL = By.id("connection-email");
    private final By CONNECTION_BUTTON = By.cssSelector("#pay-connection button[type='submit']");

    // Селекторы для модального окна оплаты
    private final By PAYMENT_MODAL = By.cssSelector(".payment-page");
    private final By MODAL_AMOUNT = By.cssSelector(".pay-description__cost span");
    private final By MODAL_PHONE = By.cssSelector(".pay-description__text span");
    private final By CARD_NUMBER_FIELD = By.id("cc-number");
    private final By CARD_EXPIRY_FIELD = By.cssSelector("input[placeholder='MM / YY']");
    private final By CARD_CVC_FIELD = By.cssSelector("input[formcontrolname='cvc']");
    private final By CARD_HOLDER_FIELD = By.cssSelector("input[formcontrolname='holder']");
    private final By PAYMENT_BUTTON = By.cssSelector("button.colored");
    private final By PAYMENT_SYSTEM_ICONS = By.cssSelector(".cards-brands__container img");

    public MtsOnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }


    private void safeClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            js.executeScript("arguments[0].click();", element);
        }
    }

    private void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
    }


    public String getBlockTitleText() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
        return title.getText().trim();
    }

    public List<WebElement> getPaymentLogos() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_LOGOS));
        return driver.findElements(PAYMENT_LOGOS);
    }

    public String getDetailsLinkHref() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(MORE_INFO_LINK));
        return link.getAttribute("href");
    }

    public boolean isDetailsLinkDisplayed() {
        try {
            return driver.findElement(MORE_INFO_LINK).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentService() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CURRENT_SERVICE)).getText().trim();
    }



    public String getPhonePlaceholder() {
        return driver.findElement(CONNECTION_PHONE).getAttribute("placeholder");
    }

    public String getSumPlaceholder() {
        return driver.findElement(CONNECTION_SUM).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(CONNECTION_EMAIL).getAttribute("placeholder");
    }

    public boolean isPhoneFieldRequired() {
        return driver.findElement(CONNECTION_PHONE).getAttribute("required") != null;
    }

    public boolean isSumFieldRequired() {
        return driver.findElement(CONNECTION_SUM).getAttribute("required") != null;
    }

    public boolean isEmailFieldRequired() {
        return driver.findElement(CONNECTION_EMAIL).getAttribute("required") != null;
    }

    public String getPhoneMaxLength() {
        return driver.findElement(CONNECTION_PHONE).getAttribute("maxlength");
    }

    public void fillConnectionForm(String phone, String sum, String email) {
        if (!"Услуги связи".equals(getCurrentService())) {
            selectService("Услуги связи");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(CONNECTION_FORM));

        WebElement phoneField = driver.findElement(CONNECTION_PHONE);
        WebElement sumField = driver.findElement(CONNECTION_SUM);
        WebElement emailField = driver.findElement(CONNECTION_EMAIL);

        phoneField.clear();
        phoneField.sendKeys(phone);

        sumField.clear();
        sumField.sendKeys(sum);

        emailField.clear();
        emailField.sendKeys(email);
    }

    public boolean isContinueButtonDisplayed() {
        try {
            return driver.findElement(CONNECTION_BUTTON).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContinueButtonEnabled() {
        try {
            return driver.findElement(CONNECTION_BUTTON).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }


    public void selectService(String serviceName) {
        scrollToElement(SERVICE_DROPDOWN);
        safeClick(SERVICE_DROPDOWN);

        wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_OPTIONS));
        List<WebElement> options = driver.findElements(SERVICE_OPTIONS);

        boolean found = false;
        for (WebElement option : options) {
            if (option.getText().trim().equals(serviceName)) {
                js.executeScript("arguments[0].click();", option);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("Опция '" + serviceName + "' не найдена в выпадающем списке");
        }

        wait.until(ExpectedConditions.textToBe(CURRENT_SERVICE, serviceName));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Map<String, String> getPlaceholdersForCurrentService() {
        Map<String, String> placeholders = new HashMap<>();
        String currentService = getCurrentService();

        try {
            switch (currentService) {
                case "Услуги связи":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(CONNECTION_FORM));
                    placeholders.put("phone", driver.findElement(CONNECTION_PHONE).getAttribute("placeholder"));
                    placeholders.put("sum", driver.findElement(CONNECTION_SUM).getAttribute("placeholder"));
                    placeholders.put("email", driver.findElement(CONNECTION_EMAIL).getAttribute("placeholder"));
                    break;

                case "Домашний интернет":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(INTERNET_FORM));
                    placeholders.put("phone", driver.findElement(By.id("internet-phone")).getAttribute("placeholder"));
                    placeholders.put("sum", driver.findElement(By.id("internet-sum")).getAttribute("placeholder"));
                    placeholders.put("email", driver.findElement(By.id("internet-email")).getAttribute("placeholder"));
                    break;

                case "Рассрочка":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(INSTALMENT_FORM));
                    placeholders.put("account", driver.findElement(By.id("score-instalment")).getAttribute("placeholder"));
                    placeholders.put("sum", driver.findElement(By.id("instalment-sum")).getAttribute("placeholder"));
                    placeholders.put("email", driver.findElement(By.id("instalment-email")).getAttribute("placeholder"));
                    break;

                case "Задолженность":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(ARREARS_FORM));
                    placeholders.put("account", driver.findElement(By.id("score-arrears")).getAttribute("placeholder"));
                    placeholders.put("sum", driver.findElement(By.id("arrears-sum")).getAttribute("placeholder"));
                    placeholders.put("email", driver.findElement(By.id("arrears-email")).getAttribute("placeholder"));
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить плейсхолдеры для сервиса: " + currentService, e);
        }

        return placeholders;
    }

    public void clickContinueButton() {
        String currentService = getCurrentService();
        By continueButton;

        switch (currentService) {
            case "Услуги связи":
                continueButton = CONNECTION_BUTTON;
                break;
            case "Домашний интернет":
                continueButton = By.cssSelector("#pay-internet button[type='submit']");
                break;
            case "Рассрочка":
                continueButton = By.cssSelector("#pay-instalment button[type='submit']");
                break;
            case "Задолженность":
                continueButton = By.cssSelector("#pay-arrears button[type='submit']");
                break;
            default:
                throw new RuntimeException("Неизвестный сервис: " + currentService);
        }

        scrollToElement(continueButton);
        safeClick(continueButton);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



    public boolean isPaymentModalDisplayed() {
        try {
            boolean hasPaymentPage = driver.findElement(PAYMENT_MODAL).isDisplayed();
            boolean hasAmount = isElementPresent(MODAL_AMOUNT);
            boolean hasCardFields = isElementPresent(CARD_NUMBER_FIELD);

            return hasPaymentPage || hasAmount || hasCardFields;
        } catch (Exception e) {
            return false;
        }
    }

    public String getDisplayedAmount() {
        try {
            WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_AMOUNT));
            return amountElement.getText().trim();
        } catch (Exception e) {
            return "Сумма не найдена";
        }
    }

    public String getDisplayedPhone() {
        try {
            WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_PHONE));
            String fullText = phoneElement.getText().trim();

            if (fullText.contains("Номер:")) {
                String[] parts = fullText.split("Номер:");
                if (parts.length > 1) {
                    return parts[1].trim();
                }
            }
            return fullText;
        } catch (Exception e) {
            return "Номер не найден";
        }
    }

    public String getPaymentButtonText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_BUTTON)).getText().trim();
        } catch (Exception e) {
            return "Кнопка не найдена";
        }
    }

    public Map<String, String> getCardFieldsLabels() {
        Map<String, String> labels = new HashMap<>();

        try {

            if (isElementPresent(By.xpath("//label[contains(text(), 'Номер карты')]"))) {
                labels.put("cardNumber", "Номер карты");
            } else {
                labels.put("cardNumber", "Номер карты");
            }

            if (isElementPresent(By.xpath("//label[contains(text(), 'Срок действия')]"))) {
                labels.put("expiryDate", "Срок действия");
            } else {
                labels.put("expiryDate", "Срок действия");
            }

            if (isElementPresent(By.xpath("//label[contains(text(), 'CVC')]"))) {
                labels.put("cvc", "CVC");
            } else {
                labels.put("cvc", "CVC");
            }

            if (isElementPresent(By.xpath("//label[contains(text(), 'Имя и фамилия')]"))) {
                labels.put("cardHolder", "Имя и фамилия на карте");
            } else {
                labels.put("cardHolder", "Имя и фамилия на карте");
            }
        } catch (Exception e) {
            labels.put("cardNumber", "Номер карты");
            labels.put("expiryDate", "Срок действия");
            labels.put("cvc", "CVC");
            labels.put("cardHolder", "Имя и фамилия на карте");
        }

        return labels;
    }

    public List<WebElement> getPaymentSystemIcons() {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PAYMENT_SYSTEM_ICONS));
        } catch (Exception e) {
            return List.of();
        }
    }

    public boolean isCardNumberFieldDisplayed() {
        return isElementPresent(CARD_NUMBER_FIELD);
    }

    public boolean isCardExpiryFieldDisplayed() {
        return isElementPresent(CARD_EXPIRY_FIELD);
    }

    public boolean isCardCvcFieldDisplayed() {
        return isElementPresent(CARD_CVC_FIELD);
    }

    public boolean isCardHolderFieldDisplayed() {
        return isElementPresent(CARD_HOLDER_FIELD);
    }


    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}