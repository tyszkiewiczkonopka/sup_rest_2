package services.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.webAPI.Student;

import static io.restassured.RestAssured.given;

public class RequestSpecProvider {
    private static final String URI = "https://thetestingworldapi.com";
    private static final String BASE_PATH = "/api/studentsDetails";

    private static RequestSpecification buildBaseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecToCreateNewStudent(Student studentData) {
        return given()
                .spec(buildBaseRequestSpec())
                .body(studentData);
    }

    public static RequestSpecification getRequestSpecForStudentId(Integer id) {
        return given()
                .spec(buildBaseRequestSpec())
                .pathParam("id", id);
    }

    public static RequestSpecification getRequestSpecToUpdateStudentData(Integer id, Student studentData) {
        return given()
                .spec(buildBaseRequestSpec())
                .pathParam("id", id)
                .body(studentData);
    }
}

