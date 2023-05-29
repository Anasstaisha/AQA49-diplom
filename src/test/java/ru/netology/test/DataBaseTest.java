package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.PayPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataBaseHelper.*;
import static ru.netology.data.DataGenerator.getInvalidCardInfoDeclined;
import static ru.netology.data.DataGenerator.getValidCardInfo;

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
    public void shouldCreditValidCard() throws InterruptedException {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = getValidCardInfo();

        payPage.validPay(info);
        TimeUnit.SECONDS.sleep(17);

        var expected = "APPROVED";
        var paymentInfo = getCreditReqInformation();
        assertEquals(paymentInfo, expected);
        payPage.bankApproved();
    }

    @Test
    @DisplayName("Purchase by valid card")
    public void shouldPaymentByValidCard() throws InterruptedException {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = getValidCardInfo();

        payPage.validPay(info);
        TimeUnit.SECONDS.sleep(17);

        var expected = "APPROVED";
        var paymentInfo = getPayInformation();
        assertEquals(paymentInfo, expected);
        payPage.bankApproved();
    }

    //Sad path tests for purchase by card and hire-purchase
    @Test
    @DisplayName("Hire-purchase by invalid card")
    public void shouldNotCreditByInvalidCard() throws InterruptedException {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = getInvalidCardInfoDeclined();

        payPage.validPay(info);
        TimeUnit.SECONDS.sleep(17);

        var expected = "DECLINED";
        var paymentInfo = getCreditReqInformation();
        assertEquals(paymentInfo, expected);
        payPage.bankDeclined();
    }

    @Test
    @DisplayName("Purchase by invalid card")
    public void shouldNotPaymentInvalidCard() throws InterruptedException {
        var payPage = new PayPage();
        payPage.byCardPurchase();
        var info = getInvalidCardInfoDeclined();

        payPage.validPay(info);
        TimeUnit.SECONDS.sleep(17);

        var expected = "DECLINED";
        var paymentInfo = getPayInformation();
        assertEquals(paymentInfo, expected);
        payPage.bankDeclined();
    }
}
