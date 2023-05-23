package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.PayPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataBaseHelper.*;
import static ru.netology.data.DataGenerator.getValidCardInfo;

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
    public void shouldPaymentValidCard() throws InterruptedException {
        var payPage = new PayPage();
        payPage.byCreditPurchase();
        var info = getValidCardInfo();

        payPage.validPay(info);
        TimeUnit.SECONDS.sleep(10);

        var expected = "APPROVED";
        var paymentInfo = getPayInformation();
        var orderInfo = getOrderInformation();
        assertEquals(getPayInformation().getStatus(), expected.toLowerCase() );
        assertEquals(getPayInformation().getTransactionNum(), orderInfo.getPaymentId());
        TimeUnit.SECONDS.sleep(10);
        payPage.bankApproved();
        TimeUnit.SECONDS.sleep(10);
    }
}
