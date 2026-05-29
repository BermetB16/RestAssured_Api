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

import java.util.HashMap;
import java.util.Map;

@Epic("User API")
@Feature("PUT User")
public class UpdateUserPutTest extends BaseConfig {
    @Test
    @Description("Full update of user via PUT — should return 200 and updated data")
    @Severity(SeverityLevel.CRITICAL)
    public void updateUserWithPut_positive() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Bermet");
        body.put("job", "Senior QA Engineer");

        Response response = RestAssured
                .given(requestSpec)
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Updated user: " + name + " | Job: " + job);

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(name, "Bermet");
        Assert.assertEquals(job, "Senior QA Engineer");
    }

    @Test
    @Description("PUT update on non-existing user — should return 200")
    @Severity(SeverityLevel.MINOR)
    public void updateUserWithPut_nonExistingUser() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Ghost");
        body.put("job", "Unknown");

        Response response = RestAssured
                .given(requestSpec)
                .body(body)
                .when()
                .put("/users/9999")
                .then()
                .extract()
                .response();

        System.out.println("PUT on non-existing user, status: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}