package api.test;

import api.BaseConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Epic("User API")
@Feature("POST User")
public class CreateUserTest extends BaseConfig {

    @Test
    @Description("Create a new user — should return 201 and correct data")
    @Severity(SeverityLevel.CRITICAL)
    public void createUser_positive() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Bermet");
        body.put("job", "QA Engineer");

        Response response = RestAssured
                .given(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String id = response.jsonPath().getString("id");

        System.out.println("Created user: " + name + " | Job: " + job + " | ID: " + id);

        Assert.assertEquals(statusCode, 201, "Status code should be 201");
        Assert.assertEquals(name, "Bermet");
        Assert.assertEquals(job, "QA Engineer");
        Assert.assertNotNull(id, "ID should be generated");
    }

    @Test
    @Description("Create user with empty body — API accepts it with valid key, returns 201")
    @Severity(SeverityLevel.MINOR)
    public void createUser_emptyBody_unauthorized() {
        Response response = RestAssured
                .given(requestSpec)
                .body("{}")
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        System.out.println("Status with empty body: " + statusCode);

        Assert.assertEquals(statusCode, 201);
    }
}