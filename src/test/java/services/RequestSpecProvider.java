package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.webAPI.Student;

import static io.restassured.RestAssured.given;

public class RequestSpecProvider {
    private static final String URI = "https://thetestingworldapi.com";
    private static final String BASE_PATH = "/api/studentsDetails";


    public static RequestSpecification getRequestSpecToCreateNewStudent(Student studentData) {
        return new RequestSpecBuilder()
                .setBaseUri(URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .setBody(studentData)
                .build();
    }

    public static RequestSpecification getRequestSpecForStudentId(Integer id) {
        return new RequestSpecBuilder()
                .setBaseUri(URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addPathParam("id", id)
                .build();
    }

    public static RequestSpecification getRequestSpecToUpdateStudentData(Integer id, Student studentData) {
        return new RequestSpecBuilder()
                .setBaseUri(URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addPathParam("id", id)
                .setBody(studentData)
                .build();
    }




}


