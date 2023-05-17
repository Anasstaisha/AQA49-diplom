package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TourPage {
    private static SelenideElement purchaseButton = $(byText("Купить"));
    private static SelenideElement hirePurchaseButton = $(byText("Купить в кредит"));

    public TourPage byCardTour() {
        purchaseButton.click();
        return new TourPage();
    }

    public TourPage byCreditTour() {
        hirePurchaseButton.click();
        return new TourPage();
    }
}
