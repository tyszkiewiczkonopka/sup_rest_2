package services.api;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.webAPI.Student;
import services.specification.RequestSpecProvider;
import services.specification.ResponseSpecProvider;

import static io.restassured.RestAssured.given;
import static services.api.EndpointProvider.*;

@Slf4j
public class RestAssuredHelper {
    public static Integer postNewStudent(Student studentData) {
        log.info(">>>>> Creating new student from YML data.");

        Response response = given()
                .spec(RequestSpecProvider.getRequestSpecToCreateNewStudent(studentData))

                .when()
                .post()

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(201))
                .extract().response();

        return response.jsonPath().getInt("id");
    }

    public static Response getStudentDetailsByID(Integer studentID) {
        log.info(">>>>> Checking if student exists under given ID.");

        return given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(studentID))

                .when()
                .get(GET_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200))
                .extract().response();
    }

    public static Student updateStudentMiddleName(Student student, Integer studentID, String newMiddleName) {
        log.info(">>>>> Updating student's middle name.");

        Student updatedStudentData = Student.builder()
                .id(studentID)
                .lastName(student.getLastName())
                .firstName(student.getFirstName())
                .middleName(newMiddleName)
                .dateOfBirth(student.getDateOfBirth())
                .build();

        given()
                .spec(RequestSpecProvider.getRequestSpecToUpdateStudentData(studentID, updatedStudentData))

                .when()
                .put(PUT_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200));

        return updatedStudentData;
    }

    public static Student deserializeUpdatedStudentFromResponse(Integer studentID) {
        log.info(">>>>> Retrieving student's data after update.");

        Response response = getStudentDetailsByID(studentID);
        return response.jsonPath().getObject("data", Student.class);
    }

    public static void deleteStudent(Integer studentId) {
        log.info(">>>>> Deleting student.");

        given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(studentId))

                .when()
                .delete(DELETE_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200));
    }

    public static void getStudentAfterDelete(Integer studentID) {
        log.info(">>>>> Checking if student can be found under given ID.");

        given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(studentID))

                .when()
                .get(GET_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpecWithBodyContainingString(200, "No data Found"));
    }
}
