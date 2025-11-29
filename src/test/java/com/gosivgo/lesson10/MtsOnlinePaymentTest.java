package com.gosivgo.lesson10;

import com.gosivgo.lesson10.pages.MtsOnlinePaymentPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlinePaymentTest extends MtsBaseTest {


    @Test
    void shouldDisplayCorrectBlockTitle() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        String actualTitle = page.getBlockTitleText();

        assertTrue(actualTitle.contains("Онлайн пополнение"),
                "Заголовок должен содержать 'Онлайн пополнение'. Фактически: " + actualTitle);
        assertTrue(actualTitle.contains("без комиссии"),
                "Заголовок должен содержать 'без комиссии'. Фактически: " + actualTitle);
    }

    @Test
    void shouldDisplayAllFivePaymentLogos() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        var logos = page.getPaymentLogos();

        assertEquals(5, logos.size(), "Ожидалось 5 логотипов платёжных систем");

        for (var logo : logos) {
            String alt = logo.getAttribute("alt");
            assertNotNull(alt, "Логотип должен иметь alt-атрибут");
            assertFalse(alt.isEmpty(), "Alt-атрибут не должен быть пустым");
        }
    }

    @Test
    void shouldHaveWorkingServiceDetailsLink() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        assertTrue(page.isDetailsLinkDisplayed(),
                "Ссылка 'Подробнее о сервисе' должна отображаться");

        String href = page.getDetailsLinkHref();
        assertNotNull(href, "Ссылка должна иметь href-атрибут");
        assertTrue(href.contains("/help/poryadok-oplaty"),
                "Ссылка должна вести на страницу с описанием оплаты. Фактически: " + href);
    }


    @Test
    void shouldDefaultToCommunicationServices() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("Услуги связи", page.getCurrentService(),
                "По умолчанию должна быть выбрана 'Услуги связи'");
    }

    @Test
    void shouldHaveCorrectPlaceholdersForConnectionService() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        assertEquals("Номер телефона", page.getPhonePlaceholder(),
                "Неверный плейсхолдер для поля телефона");
        assertEquals("Сумма", page.getSumPlaceholder(),
                "Неверный плейсхолдер для поля суммы");
        assertEquals("E-mail для отправки чека", page.getEmailPlaceholder(),
                "Неверный плейсхолдер для поля email");
    }

    @Test
    void shouldMarkRequiredFieldsCorrectly() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        assertTrue(page.isPhoneFieldRequired(),
                "Поле телефона должно быть обязательным");
        assertTrue(page.isSumFieldRequired(),
                "Поле суммы должно быть обязательным");
        assertFalse(page.isEmailFieldRequired(),
                "Поле email не должно быть обязательным");
    }

    @Test
    void shouldLimitPhoneNumberLength() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("13", page.getPhoneMaxLength(),
                "Максимальная длина номера телефона должна быть 13 символов");
    }

    @Test
    void shouldEnableContinueButtonAfterFillingConnectionForm() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        page.fillConnectionForm("+375297777777", "10", "test@example.com");

        assertTrue(page.isContinueButtonDisplayed(),
                "Кнопка 'Продолжить' должна отображаться после заполнения формы");
        assertTrue(page.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна после заполнения формы");
    }


    @Test
    void shouldHaveCorrectPlaceholdersForAllServices() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            page.selectService(service);
            Map<String, String> placeholders = page.getPlaceholdersForCurrentService();

            assertFalse(placeholders.isEmpty(),
                    "Плейсхолдеры должны быть найдены для сервиса: " + service);

            switch (service) {
                case "Услуги связи":
                    assertEquals("Номер телефона", placeholders.get("phone"));
                    assertEquals("Сумма", placeholders.get("sum"));
                    assertEquals("E-mail для отправки чека", placeholders.get("email"));
                    break;

                case "Домашний интернет":
                    assertEquals("Номер абонента", placeholders.get("phone"));
                    assertEquals("Сумма", placeholders.get("sum"));
                    assertEquals("E-mail для отправки чека", placeholders.get("email"));
                    break;

                case "Рассрочка":
                    assertEquals("Номер счета на 44", placeholders.get("account"));
                    assertEquals("Сумма", placeholders.get("sum"));
                    assertEquals("E-mail для отправки чека", placeholders.get("email"));
                    break;

                case "Задолженность":
                    assertEquals("Номер счета на 2073", placeholders.get("account"));
                    assertEquals("Сумма", placeholders.get("sum"));
                    assertEquals("E-mail для отправки чека", placeholders.get("email"));
                    break;
            }
        }

        page.selectService("Услуги связи");
    }

    @Test
    void shouldSwitchBetweenServicesCorrectly() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        String[] services = {"Домашний интернет", "Рассрочка", "Задолженность", "Услуги связи"};

        for (String service : services) {
            page.selectService(service);
            assertEquals(service, page.getCurrentService(),
                    "После выбора должен отображаться сервис: " + service);
        }
    }


    @Test
    void shouldDisplayCorrectPaymentModalAfterFillingConnectionForm() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);


        page.fillConnectionForm("+375297777777", "10", "test@example.com");
        page.clickContinueButton();


        assertTrue(page.isPaymentModalDisplayed(),
                "Модальное окно оплаты должно отобразиться после нажатия 'Продолжить'");


        String amount = page.getDisplayedAmount();
        assertTrue(amount.contains("10.00") || amount.contains("10,00") || amount.contains("10"),
                "Сумма должна быть 10. Фактически: " + amount);
        assertTrue(amount.contains("BYN"),
                "Валюта должна быть BYN. Фактически: " + amount);


        String phone = page.getDisplayedPhone();
        assertTrue(phone.contains("375297777777"),
                "Номер должен отображаться как 375297777777. Фактически: " + phone);


        assertTrue(page.isCardNumberFieldDisplayed(), "Поле 'Номер карты' должно отображаться");
        assertTrue(page.isCardExpiryFieldDisplayed(), "Поле 'Срок действия' должно отображаться");
        assertTrue(page.isCardCvcFieldDisplayed(), "Поле 'CVC' должно отображаться");


        String payButtonText = page.getPaymentButtonText();
        assertTrue(payButtonText.contains("Оплатить"), "Кнопка должна содержать 'Оплатить'");
        assertTrue(payButtonText.contains("10.00") || payButtonText.contains("10,00") || payButtonText.contains("10"),
                "Кнопка должна содержать сумму 10. Фактически: " + payButtonText);


        var icons = page.getPaymentSystemIcons();
        assertFalse(icons.isEmpty(), "Иконки платёжных систем должны отображаться");
        assertTrue(icons.size() >= 3, "Ожидается как минимум 3 иконки (Visa, Mastercard, Белкарт)");
    }


    @Test
    void shouldDisplayPhonePrefixAndCurrency() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("Услуги связи", page.getCurrentService());

        assertTrue(isElementPresent(By.cssSelector("label[for='connection-phone']")),
                "Должен присутствовать лейбл для префикса телефона");
        assertTrue(isElementPresent(By.cssSelector("label[for='connection-sum']")),
                "Должен присутствовать лейбл для валюты");
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}