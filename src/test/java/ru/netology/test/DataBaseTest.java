package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.page.PayPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataBaseHelper.*;

public class DataBaseTest {

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
        databaseCleanUp();
    }

    //Happy path tests for purchase by card and hire-purchase
    @Test
    @DisplayName("Hire-purchase by valid card")
    public void shouldCreditValidCard() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getValidCardInfo();

        payPage.validPay(validCard);
        payPage.bankApproved();

        String expected = "APPROVED";
        String actual = getCreditReqInformation();
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Purchase by valid card")
    public void shouldPaymentByValidCard() {
        PayPage payPage = new PayPage();
        payPage.byCardPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getValidCardInfo();

        payPage.validPay(validCard);
        payPage.bankApproved();

        String expected = "APPROVED";
        String actual = getPayInformation();
        assertEquals(actual, expected);
    }

    //Sad path tests for purchase by card and hire-purchase
    @Test
    @DisplayName("Hire-purchase by invalid card")
    public void shouldNotCreditByInvalidCard() {
        PayPage payPage = new PayPage();
        payPage.byCreditPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getInvalidCardInfoDeclined();

        payPage.validPay(validCard);
        payPage.bankDeclined();

        String expected = "DECLINED";
        String actual = getCreditReqInformation();
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Purchase by invalid card")
    public void shouldNotPaymentInvalidCard() {
        PayPage payPage = new PayPage();
        payPage.byCardPurchase();
        DataGenerator.CardInfo validCard = DataGenerator.getInvalidCardInfoDeclined();

        payPage.validPay(validCard);
        payPage.bankDeclined();

        String expected = "DECLINED";
        String actual = getPayInformation();
        assertEquals(actual, expected);
    }
}
