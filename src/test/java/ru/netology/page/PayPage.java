package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PayPage {
    private SelenideElement purchaseButton = $(byText("Купить"));
    private SelenideElement purchaseHeading = $(byText("Оплата по карте"));
    private SelenideElement hirePurchaseButton = $(byText("Купить в кредит"));
    private SelenideElement hirePurchaseHeading = $(byText("Кредит по данным карты"));
    private SelenideElement cardNumberField = $("input[maxlength=\"19\"]");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private SelenideElement yearField = $(byText("Год")).parent().$("input");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvvField = $("input[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement errorField = $(".input__sub");
    private SelenideElement okMessage = $(".notification_status_ok");
    private SelenideElement errMessage = $(".notification_status_error");


    public void byCardPurchase() {
        purchaseButton.click();
        purchaseHeading.shouldBe(visible);
    }

    public void byCreditPurchase() {
        hirePurchaseButton.click();
        hirePurchaseHeading.shouldBe(visible);
    }

    public void validPay(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getHolder());
        cvvField.setValue(info.getCvc());
        continueButton.click();
    }

    public void shouldBeError(String errorText) {
        errorField.shouldHave(exactText(errorText)).shouldBe(visible);
    }


    public void bankApproved() {
        okMessage.shouldHave(visible, Duration.ofSeconds(17));
    }

    public void bankDeclined() {
        errMessage.shouldHave(visible, Duration.ofSeconds(17));
    }
}
