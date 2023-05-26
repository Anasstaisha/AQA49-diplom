package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.time.Duration;

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
    private SelenideElement cardNumberFieldErr = $x("//*[text()='Номер карты']/..//*[@class='input__sub']");
    private SelenideElement monthFieldErr = $x("//*[text()='Месяц']/..//*[@class='input__sub']");
    private SelenideElement yearFieldErr = $x("//*[text()='Год']/..//*[@class='input__sub']");
    private SelenideElement ownerFieldErr = $x("//*[text()='Владелец']/..//*[@class='input__sub']");
    private SelenideElement cvvFieldErr = $x("//*[text()='CVC/CVV']/..//*[@class='input__sub']");
    private SelenideElement declineErrMessage = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement acceptMessage = $(byText("Операция одобрена Банком."));
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
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
    }

    public void emptyFields() {
        continueButton.click();
        cardNumberFieldErr.shouldBe(visible);
        monthFieldErr.shouldBe(visible);
        yearFieldErr.shouldBe(visible);
        ownerFieldErr.shouldBe(visible);
        cvvFieldErr.shouldBe(visible);
    }

    private void cardNumberFieldErrorHidden() {
        monthFieldErr.shouldBe(Condition.hidden);
        yearFieldErr.shouldBe(Condition.hidden);
        ownerFieldErr.shouldBe(Condition.hidden);
        cvvFieldErr.shouldBe(Condition.hidden);
    }

    public void emptyCardNumberField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        cardNumberFieldErr.shouldBe(visible);
        cardNumberFieldErrorHidden();
    }

    private void monthFieldErrorHidden() {
        cardNumberFieldErr.shouldBe(Condition.hidden);
        yearFieldErr.shouldBe(Condition.hidden);
        ownerFieldErr.shouldBe(Condition.hidden);
        cvvFieldErr.shouldBe(Condition.hidden);
    }

    public void emptyMonthField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        monthFieldErr.shouldBe(visible);
        monthFieldErrorHidden();
    }

    private void yearFieldErrorHidden() {
        cardNumberFieldErr.shouldBe(Condition.hidden);
        monthFieldErr.shouldBe(Condition.hidden);
        ownerFieldErr.shouldBe(Condition.hidden);
        cvvFieldErr.shouldBe(Condition.hidden);
    }

    public void emptyYearField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        yearFieldErr.shouldBe(visible);
        yearFieldErrorHidden();
    }

    private void ownerFieldErrorHidden() {
        cardNumberFieldErr.shouldBe(Condition.hidden);
        monthFieldErr.shouldBe(Condition.hidden);
        yearFieldErr.shouldBe(Condition.hidden);
        cvvFieldErr.shouldBe(Condition.hidden);
    }

    public void emptyOwnerField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        ownerFieldErr.shouldBe(visible);
        ownerFieldErrorHidden();
    }

    private void cvcFieldErrorHidden() {
        cardNumberFieldErr.shouldBe(Condition.hidden);
        monthFieldErr.shouldBe(Condition.hidden);
        yearFieldErr.shouldBe(Condition.hidden);
        ownerFieldErr.shouldBe(Condition.hidden);
    }

    public void emptyCVCField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        continueButton.click();
        cvvFieldErr.shouldBe(visible);
        cvcFieldErrorHidden();
    }

    public void invalidCardNumberField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        cardNumberFieldErr.shouldBe(visible);
        cardNumberFieldErrorHidden();
    }

    public void invalidMonthField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        monthFieldErr.shouldBe(visible);
        monthFieldErrorHidden();
    }

    public void invalidYearField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        yearFieldErr.shouldBe(visible);
        yearFieldErrorHidden();
    }

    public void invalidOwnerField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        ownerFieldErr.shouldBe(visible);
        ownerFieldErrorHidden();
    }

    public void invalidCVCField(DataGenerator.CardInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getCardMonth());
        yearField.setValue(info.getCardYear());
        ownerField.setValue(info.getCardOwner());
        cvvField.setValue(info.getCvvCode());
        continueButton.click();
        cvvFieldErr.shouldBe(visible);
        cvcFieldErrorHidden();
    }

    public void bankApproved() {
        okMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void bankDeclined() {
        errMessage.shouldBe(visible, Duration.ofSeconds(15));
    }
}
