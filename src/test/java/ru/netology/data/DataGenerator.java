package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
    }

    private static final String approvedCard = "1111 2222 3333 4444";
    private static final String declinedCard = "5555 6666 7777 8888";

    public static String getApprovedCardNumber() {
        return approvedCard;
    }

    public static String getDeclinedCardNumber() {
        return declinedCard;
    }

    public static String generateValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateRandomMonth(){
        final String[] numbers = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        var month = (int)Math.floor(Math.random() * numbers.length);
        return numbers[month];
    }

    public static String getMinInvalidMonth() {
        return "00";
    }

    public static String getMaxInvalidMonth() {
        return "13";
    }

    public static String generatePrevMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateNextMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPrevYear() {
        final String[] numbers = new String[]{"22", "31", "20", "19", "18", "17"};
        var year = (int) Math.floor(Math.random() * numbers.length);
        return numbers[year];
    }

    public static String generateNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateValidFutureYear() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }


    public static String getInvalidZeroYear() {
        return "00";
    }

    public static String generateValidOwner(String locale) {
        Faker faker = new Faker(new Locale(locale));
        faker.number().digits(3);
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateInvalidOwnerLastName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        faker.number().digits(1);
        return faker.name().firstName();
    }

    public static String generateInvalidOwnerFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        faker.number().digits(1);
        return faker.name().lastName();
    }

    public static String generateValidCvvCode() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    public static String generateInvalidCvvCode() {
        Faker faker = new Faker();
        return faker.number().digits(2);
    }

    public static String getInvalidZeroCvvCode() {
        return "000";
    }

    public static String generateNumbers() {
        Faker faker = new Faker();
        return faker.number().digits(14);
    }

    private static String getSpecialSymbols() {
        Random random = new Random();
        final String[] specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+",
                ",", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        var fistSymbol = specialSymbols[random.nextInt(31)];
        var secondSymbol = specialSymbols[random.nextInt(31)];
        return fistSymbol + secondSymbol;
    }

    private static String getOverYear() {
        final String[] numbers = new String[]{"29", "30", "31", "32", "33", "34", "35", "36"};
        var year = (int)Math.floor(Math.random() * numbers.length);
        return numbers[year];
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static CardInfo getValidCardInfo() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getInvalidCardInfoDeclined() {
        return new CardInfo(getDeclinedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutCardNumber() {
        return new CardInfo(" ", generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidCardNumber() {
        return new CardInfo(generateNumbers(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidZeroMonth() {
        return new CardInfo(getApprovedCardNumber(), getMinInvalidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidMaxMonth() {
        return new CardInfo(getApprovedCardNumber(), getMaxInvalidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutMonth() {
        return new CardInfo(getApprovedCardNumber(), " ", generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidPrevMonth() {
        return new CardInfo(getApprovedCardNumber(), generatePrevMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidPrevMonth() {
        return new CardInfo(getApprovedCardNumber(), generatePrevMonth(), generateNextYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidNextMonth() {
        return new CardInfo(getApprovedCardNumber(), generateNextMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidZeroYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), getInvalidZeroYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidPrevYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), getPrevYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidFutureYear() {
        return new CardInfo(getApprovedCardNumber(), generateRandomMonth(), generateValidFutureYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidFutureYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), getOverYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutYear() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnersFirstName() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateInvalidOwnerFirstName("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnersLastName() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateInvalidOwnerLastName("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutOwner() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), " ", generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwner() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("ru"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerWithSymbols() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), getSpecialSymbols(), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerWithNumbers() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateNumbers(), generateValidCvvCode());
    }

    public static CardInfo getCardInfoZeroInvalidCode() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), getInvalidZeroCvvCode());
    }

    public static CardInfo getCardInfoInvalidCode() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateInvalidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutCode() {
        return new CardInfo(getApprovedCardNumber(), generateValidMonth(), generateValidYear(), generateValidOwner("en"), "");
    }
}
