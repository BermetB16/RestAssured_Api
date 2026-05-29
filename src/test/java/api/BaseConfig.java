package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseConfig {

    protected static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api")
            .setContentType(ContentType.JSON)
            .addHeader("x-api-key", "pub_8ec4230bf3a8d956fec39ce04f4139d4d2455874c3125df85fc6aa237ecb9843")
            .build();
    // убрали static блок с RestAssured.requestSpecification
}