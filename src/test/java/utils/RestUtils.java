package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    private static Response response;

    public static Response getResponse() {
        return response;
    }

    public static void setBaseURI(String uri){
        RestAssured.baseURI = uri;
    }

    public static String getBaseURI(){
        return RestAssured.baseURI;
    }

    public static Response post(Object json, ContentType contentType, String endpoint){
        return response = RestAssured.given()
                .contentType(contentType)
                .body(json)
                .when()
                .post(endpoint)
                .thenReturn();
    }

    public static Response get(Map<String, String> header, String endpoint) {
        return response = RestAssured.given()
                .relaxedHTTPSValidation()
                .headers(header)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
