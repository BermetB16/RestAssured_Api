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
@Feature("GET User")
public class GetUserTest extends BaseConfig {

    @Test
    @Description("Get existing user by ID — should return 200 and correct data")
    @Severity(SeverityLevel.CRITICAL)
    public void getSingleUser_positive() {
        Response response = RestAssured
                .given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");
        String email = response.jsonPath().getString("data.email");

        System.out.println("User: " + firstName + " " + lastName + " | Email: " + email);

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        Assert.assertEquals(firstName, "Janet", "First name should be Janet");
        Assert.assertNotNull(email, "Email should not be null");
    }

    @Test
    @Description("Get non-existing user — should return 404")
    @Severity(SeverityLevel.NORMAL)
    public void getSingleUser_notFound() {
        Response response = RestAssured
                .given(requestSpec)
                .when()
                .get("/users/9999")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();

        System.out.println("Status for non-existing user: " + statusCode);

        Assert.assertEquals(statusCode, 404, "Non-existing user should return 404");
    }

    @Test
    @Description("Get list of users — should return 200 and non-empty list")
    @Severity(SeverityLevel.NORMAL)
    public void getUserList_positive() {
        Response response = RestAssured
                .given(requestSpec)
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        int totalUsers = response.jsonPath().getInt("data.size()");

        System.out.println("Total users on page: " + totalUsers);

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        Assert.assertTrue(totalUsers > 0, "User list should not be empty");
    }
}