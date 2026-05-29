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
@Feature("PATCH User")
public class UpdateUserPatchTest extends BaseConfig {

    @Test
    @Description("Partial update of user job via PATCH — should return 200")
    @Severity(SeverityLevel.CRITICAL)
    public void updateUserWithPatch_positive() {
        Map<String, String> body = new HashMap<>();
        body.put("job", "Automation QA");

        Response response = RestAssured
                .given(requestSpec)
                .body(body)
                .when()
                .patch("/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String job = response.jsonPath().getString("job");
        String updatedAt = response.jsonPath().getString("updatedAt");

        System.out.println("Patched job: " + job + " | UpdatedAt: " + updatedAt);

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        Assert.assertEquals(job, "Automation QA", "Job should be updated");
        Assert.assertNotNull(updatedAt, "UpdatedAt should be present");
    }

    @Test
    @Description("PATCH with multiple fields — should return 200 and update all fields")
    @Severity(SeverityLevel.NORMAL)
    public void updateUserWithPatch_multipleFields() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Bermet");
        body.put("job", "Lead QA");

        Response response = RestAssured
                .given(requestSpec)
                .body(body)
                .when()
                .patch("/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Patched: " + name + " | " + job);

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(name, "Bermet");
        Assert.assertEquals(job, "Lead QA");
    }
}