package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserPatchTest{

    @Test
    public void updateUserWithPatch() {
        String requestBody = """
            {
                "job": "Automation QA"
            }
        """;

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String job = response.jsonPath().getString("job");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(job, "Automation QA");
    }
}
