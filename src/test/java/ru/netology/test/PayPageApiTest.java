package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static io.restassured.RestAssured.given;

public class PayPageApiTest {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    @DisplayName("Payment with valid approved card number")
    void shouldSuccessfulTourPayment() {
        given()
                .spec(requestSpec)
                .body(DataGenerator.getValidCardInfo())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Hire-purchase with valid approved card number")
    void shouldSuccessfulTourCredit() {
        given()
                .spec(requestSpec)
                .body(DataGenerator.getValidCardInfo())
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }
}
