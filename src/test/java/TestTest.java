import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestTest {
    String baseURI = "https://thetestingworldapi.com";
    String getStudentsDetailsEndpoint = "/api/studentsDetails";
    String postStudentsDetailsEndpoint = "/api/studentsDetails/";
    private int studentId;

    //1. POST - stworzenie studenta
    @Order(1)
    @Test
    @DisplayName("Create new student")
    @Tag("POST method")
    public void should_create_new_student() {

        String requestBody = """
                {
                  "first_name": "Magda",
                  "middle_name": "Zofia",
                  "last_name": "Konopka",
                  "date_of_birth": "02/02/2022"
                }
                """;

        Response responsePost = given()
                .baseUri(baseURI)
                .basePath(postStudentsDetailsEndpoint)
                .contentType(ContentType.JSON)
                .log().all()

                .when()
                .body(requestBody)
                .post()

                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        studentId = responsePost.jsonPath().get("id");

        Response responseGet = given()
                .baseUri(baseURI)
                .basePath(getStudentsDetailsEndpoint)
                .contentType(ContentType.JSON)
                .log().all()

                .when()
                .get(String.valueOf(studentId))

                .then()
                .statusCode(200)
                .log().all()
                .body("first_name", equalTo("Magda"))
                .extract().response();

    }

    //2. GET - pobranie szczegółów studenta
//    @Order(2)
//    @Test
//    @DisplayName("Find student by ID")
//    @Tag("GET method")
//    public void should_find_new_student_by_id() {
//        given()
//                .baseUri(baseURI)
//                .basePath(getStudentsDetailsEndpoint)
//                .contentType(ContentType.JSON)
//                .log().all()
//
//                .when()
//                .get(String.valueOf(studentId))
//
//                .then()
//                .statusCode(200)
//                .log().all();
//    }

    //3. PUT - zmiana nazwiska studenta - middle_name


    //4. GET - sprawdzenie, że nowa wartość middle_name jest ustawiona
    //
    //5. DELETE - usunięcie studenta utworzonego w punkcie 1.
    //
    //6. GET - sprawdzenie, że student nie istnieje.
}