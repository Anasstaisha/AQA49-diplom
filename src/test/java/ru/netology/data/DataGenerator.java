package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {
    }

    private static final String validCardNumber = "1111 2222 3333 4444";
    private static final String invalidCardNumber = "5555 6666 7777 8888";

    public static String getValidCardNumber() {
        return validCardNumber;
    }

    public static String getInvalidCardNumber() {
        return invalidCardNumber;
    }

    public static String generateValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
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

    public static String generatePrevYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidFutureYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
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

    private static String getSpecialSymbols() {
        Random random = new Random();
        final String[] specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+",
                ",", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        var fistSymbol = specialSymbols[random.nextInt(31)];
        var secondSymbol = specialSymbols[random.nextInt(31)];
        return fistSymbol + secondSymbol;
    }

    private static String getNumbers() {
        Random random = new Random();
        final String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        var fistNumber = numbers[random.nextInt(10)];
        var secondNumber = numbers[random.nextInt(10)];
        return fistNumber + secondNumber;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardMonth;
        String cardYear;
        String cardOwner;
        String cvvCode;
    }

    @Value
    public static class CardStatus {
        String status;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static CardInfo getValidCardInfo() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public CardInfo getInvalidCardInfoDeclined() {
        return new CardInfo(invalidCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public CardInfo getCardInfoInvalidWithoutCardNumber() {
        return new CardInfo(" ", generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidZeroMonth() {
        return new CardInfo(validCardNumber, getMinInvalidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidMaxMonth() {
        return new CardInfo(validCardNumber, getMaxInvalidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutMonth() {
        return new CardInfo(validCardNumber, " ", generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidPrevMonth() {
        return new CardInfo(validCardNumber, generatePrevMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidPrevMonth() {
        return new CardInfo(validCardNumber, generatePrevMonth(), generateNextYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidNextMonth() {
        return new CardInfo(validCardNumber, generateNextMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidZeroYear() {
        return new CardInfo(validCardNumber, generateValidMonth(), getInvalidZeroYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidPrevYear() {
        return new CardInfo(validCardNumber, generateValidMonth(), generatePrevYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoValidFutureYear() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidFutureYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidFutureYear() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateInvalidFutureYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutYear() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerLastName() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), generateInvalidOwnerFirstName("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerFirstName() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), generateInvalidOwnerLastName("en"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidWithoutOwner() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), " ", generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwner() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("ru"), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerWithSymbols() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), getSpecialSymbols(), generateValidCvvCode());
    }

    public static CardInfo getCardInfoInvalidOwnerWithNumbers() {
        return new CardInfo(validCardNumber, generateValidMonth(), generateValidYear(), getNumbers(), generateValidCvvCode());
    }

    public static CardInfo getCardInfoZeroInvalidCode() {
        return new CardInfo(invalidCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("en"), getInvalidZeroCvvCode());
    }

    public static CardInfo getCardInfoInvalidCode() {
        return new CardInfo(invalidCardNumber, generateValidMonth(), generateValidYear(), generateValidOwner("en"), generateInvalidCvvCode());
    }

    @Data
    @RequiredArgsConstructor
    public class CreditRequestEntityInfo {
        private String id;
        private String bankId;
        private String created;
        private String status;
    }

    @Data
    @RequiredArgsConstructor
    public class PaymentEntityInfo {
        private String id;
        private String quantity;
        private String transactionNum;
        private String created;
        private String status;
    }

    @Data
    @RequiredArgsConstructor
    public class OrderEntityInfo {
        private String id;
        private String creditId;
        private String created;
        private String paymentId;
    }
}
