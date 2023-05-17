package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.page.PayPage;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataBaseHelper.*;
import static ru.netology.data.DataGenerator.getValidCardInfo;
import static ru.netology.data.DataGenerator.getValidCardNumber;

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
        databaseCleanUp();
    }

    @Test
    @DisplayName("Purchase by valid card")
    public void shouldPaymentValidCard() {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        payPage.validPay(getValidCardInfo());
        var expected = "APPROVED";
        var paymentInfo = getPayInformation();
        var orderInfo = getOrderInformation();
        assertEquals(expected, paymentInfo.getStatus());
        assertEquals(paymentInfo.getTransaction_num(), orderInfo.getPayment_id());
        payPage.bankApproved();
    }
}
