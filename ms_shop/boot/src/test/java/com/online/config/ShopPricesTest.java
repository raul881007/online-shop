package com.online.config;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.hasItem;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopPricesTest {


    @LocalServerPort
    private Integer port;

    @Test
    void test1() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-14T10:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("brandId", Matchers.equalTo(1))
                .body("priority", Matchers.equalTo(0))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("productId", Matchers.equalTo(35455))
                .body("priceList", Matchers.equalTo(1))
                .body("price", Matchers.is(35.5F))
                .extract();
    }

    @Test
    void test2() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-14T16:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("startDate", Matchers.equalTo("2020-06-14T15:00:00"))
                .body("endDate", Matchers.equalTo("2020-06-14T18:30:00"))
                .body("brandId", Matchers.equalTo(1))
                .body("price",  Matchers.is(25.45F))
                .body("priceList", Matchers.equalTo(2))
                .extract();
    }

    @Test
    void test3() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-14T21:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price",  Matchers.is(35.50F))
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(1))
                .extract();
    }
    @Test
    void test4() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-15T10:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("startDate", Matchers.equalTo("2020-06-15T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-06-15T11:00:00"))
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(3))
                .body("price", Matchers.is(30.50F))
                .extract();
    }

    @Test
    void test5() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-16T21:00:00&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("startDate", Matchers.equalTo("2020-06-15T16:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(38.95F))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(4))
                .extract();
    }

    @Test
    void testProductIdIncorrect() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-16T21:00:00&productId=prodId&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(400).and()
                .body("error", Matchers.equalTo("There is an error"))
                .body("message", Matchers.containsString("Failed to convert value of type 'java.lang.String' to required type 'int'"))
                .extract();
    }
    @Test
    void testDateMissed() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?productId=354551&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(400).and()
                .body("error", Matchers.equalTo("There is an error"))
                .body("message", Matchers.equalTo("Required request parameter 'date' for method parameter type LocalDateTime is not present"))
                .extract();
    }

    @Test
    void testBrandIdMissed() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=2020-06-16T21:00:00&productId=354551")
                .then().log().body()
                .contentType("application/json")
                .statusCode(400).and()
                .body("error", Matchers.equalTo("There is an error"))
                .body("message", Matchers.equalTo("Required request parameter 'brandId' for method parameter type int is not present"))
                .extract();
    }

    @Test
    void testDateFormatIncorrect() {

        RestAssured
                .when()
                .get("http://localhost:" + port + "/api/prices/?date=thisIsOneDate&productId=35455&brandId=1")
                .then().log().body()
                .contentType("application/json")
                .statusCode(400).and()
                .body("error", Matchers.equalTo("There is an error"))
                .body("message", Matchers.containsString("Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDateTime'"))
                .extract();
    }
}


