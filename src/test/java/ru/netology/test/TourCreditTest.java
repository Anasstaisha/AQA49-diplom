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
    @DisplayName("Purchase with invalid card number field")
    public void shouldNotPaymentWithInvalidCardNumber() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidCardNumber();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with empty card number field")
    public void shouldNotPaymentWithoutCardNumber() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidWithoutCardNumber();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with zero month field")
    public void shouldNotPaymentWithInvalidMinMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidZeroMonth();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with invalid month field")
    public void shouldNotPaymentWithInvalidMaxMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidMaxMonth();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Purchase with invalid previous month field")
    public void shouldNotPaymentWithInvalidPrevMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidPrevMonth();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Purchase with valid previous month field")
    public void shouldPaymentWithValidPrevMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getCardInfoValidPrevMonth();
        payPage.validPay(validCard);
        payPage.bankApproved();
    }

    @Test
    @DisplayName("Purchase with valid next month field")
    public void shouldPaymentWithValidNextMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getCardInfoValidNextMonth();
        payPage.validPay(validCard);
        payPage.bankApproved();
    }

    @Test
    @DisplayName("Purchase with empty month field")
    public void shouldNotPaymentWithoutMonth() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidWithoutMonth();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with zero year field")
    public void shouldNotPaymentWithInvalidMinYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidZeroYear();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Purchase with previous year field")
    public void shouldNotPaymentWithPrevYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidPrevYear();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Purchase with valid next year field")
    public void shouldPaymentWithValidNextYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getCardInfoValidNextYear();
        payPage.validPay(validCard);
        payPage.bankApproved();
    }

    @Test
    @DisplayName("Purchase with valid future year field")
    public void shouldPaymentWithValidFutureYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getCardInfoValidFutureYear();
        payPage.validPay(validCard);
        payPage.bankApproved();
    }

    @Test
    @DisplayName("Purchase with invalid future year field")
    public void shouldNotPaymentWithInvalidFutureYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidFutureYear();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Purchase with empty year field")
    public void shouldNotPaymentWithoutYear() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidWithoutYear();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase without owner's last name in the cardholder field")
    public void shouldNotPaymentWithoutOwnersLastName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidOwnersFirstName();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase without owner's first name in the cardholder field")
    public void shouldNotPaymentWithoutOwnersFirstName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidOwnersLastName();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with cyrillic in the cardholder field")
    public void shouldNotPaymentWithInvalidOwnersName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidOwner();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with symbols in the cardholder field")
    public void shouldNotPaymentWithoutSymbolsInsteadOfName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidOwnerWithSymbols();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with numbers in the cardholder field")
    public void shouldNotPaymentWithNumbersInsteadOfName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidOwnerWithNumbers();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with empty cardholder field")
    public void shouldNotPaymentWithoutOwnersName() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidWithoutOwner();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Purchase with zero CVC/CVV field")
    public void shouldNotPaymentWithZeroCode() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoZeroInvalidCode();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with partially filled CVC/CVV field")
    public void shouldNotPaymentWithInvalidCode() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidCode();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }

    @Test
    @DisplayName("Purchase with empty CVC/CVV field")
    public void shouldNotPaymentWithoutCode() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getCardInfoInvalidWithoutCode();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Purchase with all empty fields")
    public void shouldNotPaymentWithoutInfo() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo invalidCard = DataGenerator.getEmptyFields();
        payPage.validPay(invalidCard);
        payPage.shouldBeError("Неверный формат");
    }
}
