package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("MM.yy"));
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + "" + faker.name().lastName();
    }

    @Value
    public static class UserInfo {
        String cardNumber;
        String cardExpiryDate;
        String cardOwner;
        String cvvCode;
        String cardStatus;
    }
    public static UserInfo getCardInfoValidApproved() {
        return new UserInfo ("4444 4444 4444 4441", generateDate((int) 100), generateName("en"),"123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidDeclined() {
        return new UserInfo ("4444 4444 4444 4442", generateDate((int) 375), generateName("en"),"123", "DECLINED");
    }
    public static UserInfo getCardInfoInvalidWithoutCardNumber() {
        return new UserInfo ("", generateDate((int) 08.23), generateName("en"),"123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidWithoutCode() {
        return new UserInfo ("4444 4444 4444 4441", generateDate((int) 50), generateName("en"),"", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidCode() {
        return new UserInfo ("4444 4444 4444 4441", generateDate((int) 366), generateName("en"),"000", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidWithoutName() {
        return new UserInfo ("4444 4444 4444 4441", generateDate((int) 500), "","123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidName() {
        return new UserInfo ("4444 4444 4444 4441", generateDate((int) 14), generateName("ru"),"123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidWithoutDate() {
        return new UserInfo ("4444 4444 4444 4441", "", "","123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidZeroDate() {
        return new UserInfo ("4444 4444 4444 4441", "00.00", "","123", "APPROVED");
    }
    public static UserInfo getCardInfoInvalidDate() {
        return new UserInfo ("4444 4444 4444 4441", "15.12", "","123", "APPROVED");
    }
}
