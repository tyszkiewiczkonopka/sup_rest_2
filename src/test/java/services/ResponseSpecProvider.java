package services;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.containsString;

public class ResponseSpecProvider {
    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .build()
                .logDetail(LogDetail.BODY)
                .statusCode(statusCode);
    }
    public static ResponseSpecification getResponseSpecWithBodyContainingString(int statusCode, String bodyString) {
        return new ResponseSpecBuilder()
                .expectBody(containsString(bodyString))
                .build()
                .logDetail(LogDetail.BODY)
                .statusCode(statusCode);
    }
}
