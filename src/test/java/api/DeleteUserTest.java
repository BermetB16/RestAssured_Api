package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class DeleteUserTest {
    @Test
    public void deleteUser() {
        Response response = RestAssured
                .given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .extract()
                .response();

        int statusCode = response.getStatusCode();

        System.out.println("Status code on delete: " + statusCode);

        Assert.assertEquals(statusCode, 204);
    }
}
