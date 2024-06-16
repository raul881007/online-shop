package com.online.config;

import io.restassured.RestAssured;
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
                .body("brandId", hasItem(1))
                .body("priority", hasItem(0))
                .body("startDate", hasItem("2020-06-14T00:00:00"))
                .body("endDate", hasItem("2020-12-31T23:59:59"))
                .body("productId", hasItem(35455))
                .body("priceList", hasItem(1))
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
                .body("productId", hasItem(35455))
                .body("startDate", hasItem("2020-06-14T15:00:00"))
                .body("endDate", hasItem("2020-06-14T18:30:00"))
                .body("brandId", hasItem(1))
                .body("price", hasItem(25.45F))
                .body("priceList", hasItem(2))
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
                .body("startDate", hasItem("2020-06-14T00:00:00"))
                .body("endDate", hasItem("2020-12-31T23:59:59"))
                .body("price", hasItem(35.50F))
                .body("productId", hasItem(35455))
                .body("brandId", hasItem(1))
                .body("priceList", hasItem(1))
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
                .body("startDate", hasItem("2020-06-15T00:00:00"))
                .body("endDate", hasItem("2020-06-15T11:00:00"))
                .body("productId", hasItem(35455))
                .body("brandId", hasItem(1))
                .body("priceList", hasItem(3))
                .body("price", hasItem(30.50F))
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
                .body("productId", hasItem(35455))
                .body("startDate", hasItem("2020-06-15T16:00:00"))
                .body("endDate", hasItem("2020-12-31T23:59:59"))
                .body("price", hasItem(38.95F))
                .body("brandId", hasItem(1))
                .body("priceList", hasItem(4))
                .extract();
    }
}
