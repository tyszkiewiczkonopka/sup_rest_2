package tests;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.webAPI.Student;
import org.junit.jupiter.api.Test;
import services.RequestSpecProvider;
import services.ResponseSpecProvider;
import services.YmlReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static services.EndpointProvider.*;

@Slf4j
public class CrudTest {

    @Test
    public void should_create_new_student() throws IOException {

        // POST

        log.info(">>>>> Creating new student from YML data.");

        Student newStudent = getNewStudent();
        Integer createdStudentID = createNewStudent(newStudent);

        // GET

        log.info(">>>>> Checking if student exists under given ID.");


        given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(createdStudentID))

                .when()
                .get(GET_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200));

        // PUT

        log.info(">>>>> Updating student's middle name.");

        Student updatedStudentData = Student.builder()
                .id(createdStudentID)
                .lastName(newStudent.getLastName())
                .firstName(newStudent.getFirstName())
                .middleName("Tyszkiewicz")
                .dateOfBirth(newStudent.getDateOfBirth())
                .build();

        given()
                .spec(RequestSpecProvider.getRequestSpecToUpdateStudentData(createdStudentID, updatedStudentData))

                .when()
                .put(PUT_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200));

        // GET

        log.info(">>>>> Checking if student's data has updated middle name.");


        Student updateStudentResponse = given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(createdStudentID))

                .when()
                .get(GET_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200))
                .extract().response()
                .jsonPath().getObject("data", Student.class);

        assertThat(updateStudentResponse)
                .usingRecursiveComparison()
                .isEqualTo(updatedStudentData);

        // DELETE

        log.info(">>>>> Deleting student.");

        given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(createdStudentID))

                .when()
                .delete(DELETE_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(200));

        // GET

        log.info(">>>>> Checking if student can be found under given ID.");

        given()
                .spec(RequestSpecProvider.getRequestSpecForStudentId(createdStudentID))

                .when()
                .get(GET_STUDENT_DETAILS_ENDPOINT)

                .then()
                .spec(ResponseSpecProvider.getResponseSpecWithBodyContainingString(200, "No data Found"));

    }

    private Integer createNewStudent(Student studentData) {
        Response response = given()
                .spec(RequestSpecProvider.getRequestSpecToCreateNewStudent(studentData))

                .when()
                .post()

                .then()
                .spec(ResponseSpecProvider.getResponseSpec(201))
                .extract().response();

        return response.jsonPath().getInt("id");
    }

    private static Student getNewStudent() throws IOException {
        return new YmlReader().getStudentFromConfig();
    }



}

