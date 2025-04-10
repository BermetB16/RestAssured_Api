package api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserPutTest {

    @Test
    public void updateUserWithPut() {
        String requestBody = """
            {
                "name": "Bem",
                "job": "Senior QA Engineer"
            }
        """;

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(name, "Bem");
        Assert.assertEquals(job, "Senior QA Engineer");
    }
}


