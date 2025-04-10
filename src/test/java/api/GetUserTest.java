package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class GetUserTest {

    @Test
    public void getSingleUser() {
        Response response = RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");

        System.out.println("User: " + firstName + " " + lastName);

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        Assert.assertEquals(firstName, "Janet", "First name should be Janet");
    }
}
