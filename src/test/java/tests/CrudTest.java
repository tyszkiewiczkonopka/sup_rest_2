package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import models.config.StudentConfiguration;
import org.junit.jupiter.api.*;
import services.EndpointProvider;
import services.JsonReader;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudTest extends BaseAPITest{

    private static List<StudentConfiguration> getNewStudents() throws IOException {
        return new JsonReader().getStudentsFromConfig();
    }

    //1. POST - stworzenie studenta
    @Test
    @Order(1)
    @DisplayName("Create new student")
    @Tag("post_method")
    public void should_create_new_student() throws IOException {

        List<StudentConfiguration> newStudents = getNewStudents();

        Response responsePost = given()
                .contentType(ContentType.JSON) // todo: do req spec
                .body()

                .when()
                .post()

                .then()
                .statusCode(201)
                .and()
                .body("first_name", equalTo("Magda"))
                .extract().response();

        log.info("Status code: " + responsePost.statusCode());
        log.info("Student data: " + student);

        // studentId = responsePost.jsonPath().get("id");
    }
//
//  //2. GET - pobranie szczegółów studenta
//    @Test
//    @Order(2)
//    @DisplayName("Find student by ID")
//    @Tag("get_method")
//    public void should_find_new_student_by_id() {
//
//        Student createdStudent =
//                given()
//                .baseUri(baseURI)
//                .basePath(studentsDetailsEndpoint)
//                .when()
//                .get(baseURI + studentsDetailsEndpoint + student.getId())
//                .as(Student.class);
//
//
//        Response responseGet = given()
//                .baseUri(baseURI)
//                .basePath(studentsDetailsEndpoint + createdStudent.getId())
//                .contentType(ContentType.JSON)
//                .log().all()
//
//                .when()
//                .get()
//
//                .then()
//                .statusCode(200)
//                .log().all()
//                .body("data.first_name", equalTo("Magda"))
//                .extract().response();
//
//        //3. PUT - zmiana nazwiska studenta - middle_name
//
//        createdStudent.setMiddle_name("Julia");
//
//
//        Response responsePut = given()
//                .baseUri(baseURI)
//                .basePath(studentsDetailsEndpoint + createdStudent.getId())
//                .contentType(ContentType.JSON)
//                .log().all()
//
//                .when()
//                .body(createdStudent)
//                .put()
//
//                .then()
//                .statusCode(200)
//                .log().all()
//                .body("middle_name", equalTo("Julia"))
//                .extract().response();
//    }


    //4. GET - sprawdzenie, że nowa wartość middle_name jest ustawiona
    //
    //5. DELETE - usunięcie studenta utworzonego w punkcie 1.
    //
    //6. GET - sprawdzenie, że student nie istnieje.
}
