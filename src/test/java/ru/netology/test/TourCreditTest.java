package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.page.PayPage;

import static com.codeborne.selenide.Selenide.open;

public class TourCreditTest {

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
    @DisplayName("Hire-purchase with invalid card number field")
    public void shouldNotCreditWithInvalidCardNumber() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidCardNumber();
        payPage.invalidCardNumberField(info);
    }

    @Test
    @DisplayName("Hire-purchase with empty card number field")
    public void shouldNotCreditWithoutCardNumber() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutCardNumber();
        payPage.emptyCardNumberField(info);
    }

    @Test
    @DisplayName("Hire-purchase with zero month field")
    public void shouldNotCreditWithInvalidMinMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidZeroMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Hire-purchase with invalid month field")
    public void shouldNotCreditWithInvalidMaxMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidMaxMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Hire-purchase with invalid previous month field")
    public void shouldNotCreditWithInvalidPrevMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidPrevMonth();
        payPage.invalidMonthField(info);
    }

    @Test
    @DisplayName("Hire-purchase with valid previous month field")
    public void shouldCreditWithValidPrevMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoValidPrevMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Hire-purchase with valid next month field")
    public void shouldCreditWithValidNextMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoValidNextMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Hire-purchase with empty month field")
    public void shouldNotCreditWithoutMonth() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutMonth();
        payPage.emptyMonthField(info);
    }

    @Test
    @DisplayName("Hire-purchase with zero year field")
    public void shouldNotCreditWithInvalidMinYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidZeroYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Hire-purchase with previous year field")
    public void shouldNotCreditWithPrevYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidPrevYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Hire-purchase with valid next year field")
    public void shouldCreditWithValidNextYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoValidNextMonth();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Hire-purchase with valid future year field")
    public void shouldCreditWithValidFutureYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoValidFutureYear();
        payPage.validPay(info);
    }

    @Test
    @DisplayName("Hire-purchase with invalid future year field")
    public void shouldNotCreditWithInvalidFutureYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidFutureYear();
        payPage.invalidYearField(info);
    }

    @Test
    @DisplayName("Hire-purchase with empty year field")
    public void shouldNotCreditWithoutYear() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutYear();
        payPage.emptyYearField(info);
    }

    @Test
    @DisplayName("Hire-purchase without owner's first name in the cardholder field")
    public void shouldNotCreditWithoutOwnersFirstName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnersFirstName();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase without owner's last name in the cardholder field")
    public void shouldNotCreditWithoutOwnersLastName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnersLastName();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase with cyrillic in the cardholder field")
    public void shouldNotCreditWithInvalidOwnersName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidOwner();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase with symbols in the cardholder field")
    public void shouldNotCreditWithoutSymbolsInsteadOfName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnerWithSymbols();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase with numbers in the cardholder field")
    public void shouldNotCreditWithNumbersInsteadOfName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidOwnerWithNumbers();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase with empty cardholder field")
    public void shouldNotCreditWithoutOwnersName() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutOwner();
        payPage.invalidOwnerField(info);
    }

    @Test
    @DisplayName("Hire-purchase with zero CVC/CVV field")
    public void shouldNotCreditWithZeroCode() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoZeroInvalidCode();
        payPage.invalidCVCField(info);
    }

    @Test
    @DisplayName("Hire-purchase with partially filled CVC/CVV field")
    public void shouldNotCreditWithInvalidCode() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidCode();
        payPage.invalidCVCField(info);
    }

    @Test
    @DisplayName("Hire-purchase with empty CVC/CVV field")
    public void shouldNotCreditWithoutCode() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = DataGenerator.getCardInfoInvalidWithoutCode();
        payPage.emptyCVCField(info);
    }

    @Test
    @DisplayName("Hire-purchase with all empty fields")
    public void shouldNotCreditWithoutInfo() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        payPage.emptyFields();
    }
}
