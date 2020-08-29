package ApiUtilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Httpmethods {

    public Response getResponse(final RequestSpecification requestSpecification){
        return given().spec(requestSpecification).when().log().all().get().then().extract().response();
    }

    public static Response postResponse(final RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).when().log().all().post().then().extract().response();
    }



}
