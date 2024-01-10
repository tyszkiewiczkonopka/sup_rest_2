package tests;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

public class BaseAPITest {
    private static String uri = "https://thetestingworldapi.com";
    private static String basePath = "/api/studentsDetails";


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = uri;
        RestAssured.basePath = basePath;
    }

}
