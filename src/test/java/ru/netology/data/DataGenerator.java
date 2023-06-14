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


    public static String generateMonth(int mm) {
        int value = mm;
        if (value == 0) { // текущий месяц
            return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        } else if (value == -1) { // предыдущий месяц
            return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        } else if (value == 1) { // следующий месяц
            return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        } else if (value == 2) { // рандомный месяц
            final String[] numbers = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
            int month = (int) Math.floor(Math.random() * numbers.length);
            return numbers[month];
        } else {
            return null;
        }
    }

    public static String getMinInvalidMonth() {
        return "00";
    }

    public static String getMaxInvalidMonth() {
        return "13";
    }

    public static String generateYear(int yy) {
        int value = yy;
        if (value == 0) { // текущий год
            return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        } else if (value == -1) { // предыдущий год
            final String[] numbers = new String[]{"22", "21", "20", "19", "18", "17"};
            int year = (int) Math.floor(Math.random() * numbers.length);
            return numbers[year];
        } else if (value == 1) { // следующий год
            return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        } else if (value == 2) { // будущий валидный год
            final String[] numbers = new String[]{"24", "25", "26", "27", "28"};
            int year = (int) Math.floor(Math.random() * numbers.length);
            return numbers[year];
        } else if (value == 3) { // будущий невалидный год
            final String[] numbers = new String[]{"29", "30", "31", "32", "33"};
            int year = (int) Math.floor(Math.random() * numbers.length);
            return numbers[year];
        } else {
            return null;
        }
    }

    public static String getInvalidZeroYear() {
        return "00";
    }

    public static String generateValidOwner(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateInvalidOwnerLastNameWithoutFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName();
    }

    public static String generateInvalidOwnerFirstNameWithoutLastName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String getInvalidZeroCvvCode() {
        return "000";
    }

    public static String generateNumbers(int count) {
        Faker faker = new Faker();
        int number = count;
        return faker.number().digits(number);
    }

    private static String getSpecialSymbols() {
        Random random = new Random();
        final String[] specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+",
                ",", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        String fistSymbol = specialSymbols[random.nextInt(31)];
        String secondSymbol = specialSymbols[random.nextInt(31)];
        return fistSymbol + secondSymbol;
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
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getEmptyFields() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getInvalidCardInfoDeclined() {
        return new CardInfo(getDeclinedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidWithoutCardNumber() {
        return new CardInfo(" ", generateMonth(0), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidCardNumber() {
        return new CardInfo(generateNumbers(10), generateMonth(0), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidZeroMonth() {
        return new CardInfo(getApprovedCardNumber(), getMinInvalidMonth(), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidMaxMonth() {
        return new CardInfo(getApprovedCardNumber(), getMaxInvalidMonth(), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidWithoutMonth() {
        return new CardInfo(getApprovedCardNumber(), " ", generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidPrevMonth() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(-1), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoValidPrevMonth() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(-1), generateYear(1), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoValidNextMonth() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), generateYear(0), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidZeroYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(1), getInvalidZeroYear(), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidPrevYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(-1), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoValidNextYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(2), generateYear(1), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoValidFutureYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(2), generateYear(2), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidFutureYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(2), generateYear(3), generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidWithoutYear() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), "", generateValidOwner("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidOwnersFirstName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateInvalidOwnerLastNameWithoutFirstName("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidOwnersLastName() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateInvalidOwnerFirstNameWithoutLastName("en"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidWithoutOwner() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), " ", generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidOwner() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("ru"), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidOwnerWithSymbols() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), getSpecialSymbols(), generateNumbers(3));
    }

    public static CardInfo getCardInfoInvalidOwnerWithNumbers() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateNumbers(12), generateNumbers(3));
    }

    public static CardInfo getCardInfoZeroInvalidCode() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("en"), getInvalidZeroCvvCode());
    }

    public static CardInfo getCardInfoInvalidCode() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("en"), generateNumbers(2));
    }

    public static CardInfo getCardInfoInvalidWithoutCode() {
        return new CardInfo(getApprovedCardNumber(), generateMonth(0), generateYear(0), generateValidOwner("en"), "");
    }
}

