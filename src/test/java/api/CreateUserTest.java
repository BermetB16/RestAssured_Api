package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class CreateUserTest {

    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
                "    \"name\": \"Bem\",\n" +
                "    \"job\": \"QA Engineer\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .extract()
                .response();
        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Created user: " + name + ", job: " + job);

        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(name, "Bem");
        Assert.assertEquals(job, "QA Engineer");
    }
}
