package com.gosivgo.lesson9;

import com.gosivgo.lesson9.pages.MtsOnlinePaymentPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlinePaymentTest extends MtsBaseTest {

    @Test
    void shouldDisplayCorrectBlockTitle() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        String actual = page.getBlockTitleText();
        assertTrue(actual.contains("Онлайн пополнение") && actual.contains("без комиссии"),
                "Заголовок должен содержать обе части. Фактически: " + actual);
    }

    @Test
    void shouldDisplayAllFivePaymentLogos() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        var logos = page.getPaymentLogos();
        assertEquals(5, logos.size(), "Ожидалось 5 логотипов платёжных систем");

        String[] expectedAlts = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (int i = 0; i < logos.size(); i++) {
            assertEquals(expectedAlts[i], logos.get(i).getAttribute("alt"));
        }
    }

    @Test
    void shouldHaveWorkingServiceDetailsLink() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertTrue(page.isDetailsLinkDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается");
        String href = page.getDetailsLinkHref();
        assertNotNull(href, "href не должен быть null");
        assertTrue(href.contains("/help/poryadok-oplaty"), "Ссылка ведёт не туда. Фактически: " + href);
    }

    @Test
    void shouldDefaultToCommunicationServices() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("Услуги связи", page.getCurrentService(), "По умолчанию должна быть выбрана 'Услуги связи'");
    }

    @Test
    void shouldEnableContinueButtonAfterFillingConnectionForm() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        page.fillConnectionForm("297777777", "10", "test@example.com");

        assertTrue(page.isContinueButtonDisplayed(), "Кнопка 'Продолжить' не отображается");
        assertTrue(page.isContinueButtonEnabled(), "Кнопка 'Продолжить' неактивна");
    }

    // --- Дополнительные проверки ---

    @Test
    void shouldDisplayPhonePrefixAndCurrency() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("+375", page.getPhonePrefix(), "Префикс телефона должен быть +375");
        assertEquals("Руб.", page.getCurrencySuffix(), "Валюта должна быть 'Руб.'");
    }

    @Test
    void shouldHaveCorrectPlaceholders() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("Номер телефона", page.getPhonePlaceholder());
        assertEquals("Сумма", page.getSumPlaceholder());
        assertEquals("E-mail для отправки чека", page.getEmailPlaceholder());
    }

    @Test
    void shouldMarkRequiredFieldsCorrectly() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertTrue(page.isPhoneFieldRequired(), "Поле телефона должно быть обязательным");
        assertTrue(page.isSumFieldRequired(), "Поле суммы должно быть обязательным");
        assertFalse(page.isEmailFieldRequired(), "Поле email не должно быть обязательным");
    }

    @Test
    void shouldLimitPhoneNumberLength() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        assertEquals("13", page.getPhoneMaxLength(), "Макс. длина номера — 13 символов");
    }
}