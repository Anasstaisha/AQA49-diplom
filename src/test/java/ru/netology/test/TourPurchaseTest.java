package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.page.PayPage;

import static com.codeborne.selenide.Selenide.open;

public class TourPurchaseTest {
    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
        open("http://localhost:8080");
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Purchase with invalid card number field")
    public void shouldNotPaymentWithInvalidCardNumber() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidCardNumber();
        payPage.invalidCardNumberField(info);
    }

    @Test
    @DisplayName("Purchase with empty card number field")
    public void shouldNotPaymentWithoutCardNumber() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutCardNumber();
        payPage.emptyCardNumberField(info);
    }

    @Test
    @DisplayName("Purchase with zero month field")
    public void shouldNotPaymentWithInvalidMinMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidZeroMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Purchase with invalid month field")
    public void shouldNotPaymentWithInvalidMaxMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidMaxMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Purchase with invalid previous month field")
    public void shouldNotPaymentWithInvalidPrevMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidPrevMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Purchase with valid previous month field")
    public void shouldPaymentWithValidPrevMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoValidPrevMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Purchase with valid next month field")
    public void shouldPaymentWithValidNextMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoValidNextMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Purchase with empty month field")
    public void shouldNotPaymentWithoutMonth() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutMonth();
        payPage.emptyMonthField(info);
    }

    @Test
    @DisplayName("Purchase with zero year field")
    public void shouldNotPaymentWithInvalidMinYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidZeroYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Purchase with previous year field")
    public void shouldNotPaymentWithPrevYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidPrevYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Purchase with valid next year field")
    public void shouldPaymentWithValidNextYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoValidNextMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Purchase with valid future year field")
    public void shouldPaymentWithValidFutureYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoValidFutureYear();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Purchase with invalid future year field")
    public void shouldNotPaymentWithInvalidFutureYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidFutureYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Purchase with empty year field")
    public void shouldNotPaymentWithoutYear() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutYear();
        payPage.emptyYearField(info);
    }

    @Test
    @DisplayName("Purchase without owner's last name in the cardholder field")
    public void shouldNotPaymentWithoutOwnersLastName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnersFirstName();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase without owner's first name in the cardholder field")
    public void shouldNotPaymentWithoutOwnersFirstName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnersLastName();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase with cyrillic in the cardholder field")
    public void shouldNotPaymentWithInvalidOwnersName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidOwner();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase with symbols in the cardholder field")
    public void shouldNotPaymentWithoutSymbolsInsteadOfName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnerWithSymbols();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase with numbers in the cardholder field")
    public void shouldNotPaymentWithNumbersInsteadOfName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnerWithNumbers();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase with empty cardholder field")
    public void shouldNotPaymentWithoutOwnersName() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutOwner();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Purchase with zero CVC/CVV field")
    public void shouldNotPaymentWithZeroCode() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoZeroInvalidCode();
        payPage.invalidCVCField(info);
    }

    @Test
    @DisplayName("Purchase with partially filled CVC/CVV field")
    public void shouldNotPaymentWithInvalidCode() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidCode();
        payPage.invalidCVCField(info);
    }

    @Test
    @DisplayName("Purchase with empty CVC/CVV field")
    public void shouldNotPaymentWithoutCode() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutCode();
        payPage.emptyCVCField(info);
    }

    @Test
    @DisplayName("Purchase with all empty fields")
    public void shouldNotPaymentWithoutInfo() {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        payPage.emptyFields();
    }
}
