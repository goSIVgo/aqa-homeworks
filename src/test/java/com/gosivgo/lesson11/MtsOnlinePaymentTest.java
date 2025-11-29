package com.gosivgo.lesson11;

import com.gosivgo.lesson11.pages.MtsOnlinePaymentPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Epic("MTS.BY Онлайн оплата")
@Feature("Проверка функциональности онлайн оплаты")
public class MtsOnlinePaymentTest extends MtsBaseTest {

    @Test
    @Story("Проверка заголовка блока")
    @Description("Проверка корректности отображения заголовка блока онлайн пополнения")
    @Severity(SeverityLevel.CRITICAL)
    void shouldDisplayCorrectBlockTitle() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Получение текста заголовка", step -> {
            String actualTitle = page.getBlockTitleText();

            Allure.step("Проверка содержания 'Онлайн пополнение'", step2 -> {
                assertTrue(actualTitle.contains("Онлайн пополнение"),
                        "Заголовок должен содержать 'Онлайн пополнение'");
            });

            Allure.step("Проверка содержания 'без комиссии'", step2 -> {
                assertTrue(actualTitle.contains("без комиссии"),
                        "Заголовок должен содержать 'без комиссии'");
            });
        });
    }

    @Test
    @Story("Проверка логотипов платежных систем")
    @Description("Проверка отображения всех 5 логотипов платежных систем")
    @Severity(SeverityLevel.NORMAL)
    void shouldDisplayAllFivePaymentLogos() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Получение списка логотипов", step -> {
            var logos = page.getPaymentLogos();

            Allure.step("Проверка количества логотипов", step2 -> {
                assertEquals(5, logos.size(), "Ожидалось 5 логотипов");
            });

            Allure.step("Проверка атрибутов логотипов", step2 -> {
                for (var logo : logos) {
                    String alt = logo.getAttribute("alt");
                    assertNotNull(alt, "Логотип должен иметь alt");
                    assertFalse(alt.isEmpty(), "Alt не должен быть пустым");
                }
            });
        });
    }

    @Test
    @Story("Проверка ссылки на подробности")
    @Description("Проверка работоспособности ссылки на дополнительную информацию")
    @Severity(SeverityLevel.MINOR)
    void shouldHaveWorkingServiceDetailsLink() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Проверка отображения ссылки", step -> {
            assertTrue(page.isDetailsLinkDisplayed(), "Ссылка должна отображаться");
        });

        Allure.step("Проверка корректности ссылки", step -> {
            String href = page.getDetailsLinkHref();
            assertNotNull(href, "Ссылка должна иметь href");
            assertTrue(href.contains("/help/poryadok-oplaty"), "Ссылка ведёт не туда: " + href);
        });
    }

    @Test
    @Story("Проверка сервиса по умолчанию")
    @Description("Проверка что по умолчанию выбран сервис 'Услуги связи'")
    @Severity(SeverityLevel.CRITICAL)
    void shouldDefaultToCommunicationServices() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Проверка текущего сервиса", step -> {
            assertEquals("Услуги связи", page.getCurrentService());
        });
    }

    @Test
    @Story("Проверка плейсхолдеров")
    @Description("Проверка корректности плейсхолдеров для сервиса связи")
    @Severity(SeverityLevel.NORMAL)
    void shouldHaveCorrectPlaceholdersForConnectionService() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Проверка плейсхолдеров полей", step -> {
            assertEquals("Номер телефона", page.getPhonePlaceholder());
            assertEquals("Сумма", page.getSumPlaceholder());
            assertEquals("E-mail для отправки чека", page.getEmailPlaceholder());
        });
    }

    @Test
    @Story("Проверка обязательных полей")
    @Description("Проверка корректной маркировки обязательных полей")
    @Severity(SeverityLevel.CRITICAL)
    void shouldMarkRequiredFieldsCorrectly() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Проверка обязательности полей", step -> {
            assertTrue(page.isPhoneFieldRequired());
            assertTrue(page.isSumFieldRequired());
            assertFalse(page.isEmailFieldRequired());
        });
    }

    @Test
    @Story("Проверка ограничения длины номера")
    @Description("Проверка ограничения максимальной длины номера телефона")
    @Severity(SeverityLevel.MINOR)
    void shouldLimitPhoneNumberLength() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Проверка максимальной длины номера", step -> {
            assertEquals("13", page.getPhoneMaxLength());
        });
    }

    @Test
    @Story("Проверка кнопки продолжения")
    @Description("Проверка активации кнопки продолжения после заполнения формы")
    @Severity(SeverityLevel.CRITICAL)
    void shouldEnableContinueButtonAfterFillingConnectionForm() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Заполнение формы связи", step -> {
            page.fillConnectionForm("+375297777777", "10", "test@example.com");
        });

        Allure.step("Проверка кнопки продолжения", step -> {
            assertTrue(page.isContinueButtonDisplayed());
            assertTrue(page.isContinueButtonEnabled());
        });
    }

    @Test
    @Story("Проверка переключения сервисов")
    @Description("Проверка корректного переключения между всеми сервисами")
    @Severity(SeverityLevel.NORMAL)
    void shouldSwitchBetweenServicesCorrectly() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);
        String[] services = {"Домашний интернет", "Рассрочка", "Задолженность", "Услуги связи"};

        for (String service : services) {
            Allure.step("Переключение на сервис: " + service, step -> {
                page.selectService(service);
                assertEquals(service, page.getCurrentService());
            });
        }
    }

    @Test
    @Story("Проверка модального окна оплаты")
    @Description("Проверка отображения модального окна после заполнения формы")
    @Severity(SeverityLevel.CRITICAL)
    @Disabled("Требуется доработка логики определения модального окна")
    void shouldDisplayCorrectPaymentModalAfterFillingConnectionForm() {
        MtsOnlinePaymentPage page = new MtsOnlinePaymentPage(driver);

        Allure.step("Заполнение формы связи", step -> {
            page.fillConnectionForm("+375297777777", "10", "test@example.com");
        });

        Allure.step("Нажатие кнопки продолжения", step -> {
            page.clickContinueButton();
        });

        Allure.step("Проверка модального окна", step -> {
            assertTrue(page.isPaymentModalDisplayed(), "Модальное окно не появилось");
        });

        Allure.step("Проверка отображаемой суммы", step -> {
            String amount = page.getDisplayedAmount();
            assertTrue(amount.contains("10.00") || amount.contains("10,00") || amount.contains("10"));
            assertTrue(amount.contains("BYN"));
        });

        Allure.step("Проверка отображаемого телефона", step -> {
            String phone = page.getDisplayedPhone();
            assertTrue(phone.contains("375297777777"), "Телефон: " + phone);
        });

        Allure.step("Проверка полей карты", step -> {
            assertTrue(page.isCardNumberFieldDisplayed());
            assertTrue(page.isCardExpiryFieldDisplayed());
            assertTrue(page.isCardCvcFieldDisplayed());
        });

        Allure.step("Проверка текста кнопки оплаты", step -> {
            String payBtn = page.getPaymentButtonText();
            assertTrue(payBtn.contains("Оплатить"));
            assertTrue(payBtn.contains("10.00") || payBtn.contains("10,00") || payBtn.contains("10"));
        });

        Allure.step("Проверка иконок платежных систем", step -> {
            var icons = page.getPaymentSystemIcons();
            assertFalse(icons.isEmpty(), "Нет иконок платёжных систем");
            assertTrue(icons.size() >= 3);
        });
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