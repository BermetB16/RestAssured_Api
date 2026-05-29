package api.test;

import api.BaseConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("User API")
@Feature("DELETE User")
public class DeleteUserTest extends BaseConfig {

    @Test
    @Description("Delete existing user — should return 204 No Content")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteUser_positive() {
        Response response = RestAssured
                .given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();

        System.out.println("Delete status: " + statusCode + " | Body: '" + body + "'");

        Assert.assertEquals(statusCode, 204, "Status code should be 204 No Content");
        Assert.assertTrue(body.isEmpty(), "Response body should be empty on delete");
    }

    @Test
    @Description("Delete non-existing user — reqres returns 204 (mock API behaviour)")
    @Severity(SeverityLevel.MINOR)
    public void deleteUser_nonExisting() {
        Response response = RestAssured
                .given(requestSpec)
                .when()
                .delete("/users/9999")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();

        System.out.println("Delete non-existing user, status: " + statusCode);

        // reqres.in mock API returns 204 for any DELETE
        Assert.assertEquals(statusCode, 204, "Mock API returns 204 for any DELETE");
    }
}