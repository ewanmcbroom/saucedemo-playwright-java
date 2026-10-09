package tests.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqResApiTests {

    @Test
    void getUser() {

        Response response = given()
                .when()
                .get("https://reqres.in/api/users/2");

        assertEquals(
                200,
                response.getStatusCode()
        );
    }

    @Test
    void createUser() {

        String requestBody = """
            {
                "name": "Ewan",
                "job": "Tester"
            }
            """;

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users");

        assertEquals(
                201,
                response.getStatusCode()
        );

        assertEquals(
                "Ewan",
                response.jsonPath().getString("name")
        );

        assertEquals(
                "Tester",
                response.jsonPath().getString("job")
        );
    }
}
