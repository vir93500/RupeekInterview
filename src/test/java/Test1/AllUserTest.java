package Test1;

import Apihelper.ApiHelping;
import BaseTest.TestSetup;
import Request.JWTRequest;
import Response.*;
import Response.JWTTokenResponse;
import Utilities.GenericResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.GenericType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static Constants.JWTCredentials.INVALID_AUTHORIZATION_TOKEN;
import static Constants.JWTCredentials.PASSWORD;
import static Constants.JWTCredentials.PHONE;
import static Constants.JWTCredentials.USERNAME;

public class AllUserTest extends TestSetup {

    public JWTTokenResponse emailResponse;
    public ApiHelping apiHelping;
    public JWTRequest jwtRequest;

    @BeforeMethod
    public void AllUserTest() {
        emailResponse = new JWTTokenResponse();
        apiHelping = new ApiHelping();
        jwtRequest = new JWTRequest();
    }

    @Test(description = "Verify happy flow to get user details")
    public void allUserDataTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String jwtBearerToken = "Bearer "+apiHelping.jwtTokenApiBuilder(jwtRequest).getData().getToken();

        JsonPath allUserDetailsResponse = apiHelping.allUserDetailsApiBuilder(jwtBearerToken);
        List<AllUserResponse> allUserResponsesList = allUserDetailsResponse.get();
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < allUserResponsesList.size(); i++) {
            AllUserResponse response = mapper.convertValue(allUserResponsesList.get(i),AllUserResponse.class);
            softAssert.assertTrue(response.getFirst_name()!=null,"verified firstname successfuly");
            softAssert.assertTrue(response.getLast_name()!=null,"verified lastname successfuly");
            softAssert.assertTrue(response.getCareer()!=null,"verified career successfuly");
            softAssert.assertTrue(response.getPhone()!=null,"verified phone No. successfuly");
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify get user details with invalid authorization token")
    public void allUserDataInvalidTokenTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String invalidJWTBearerToken = INVALID_AUTHORIZATION_TOKEN;
        Response response =  apiHelping.allUserDetailsInvalidApiBuilder(invalidJWTBearerToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Verify http code");
        softAssert.assertEquals(response.asString(),"","verified response body is empty");
        softAssert.assertAll();

    }

    @Test(description = "Verify get user details with empty authorization token")
    public void allUserDataNullTokenTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        Response response =  apiHelping.allUserDetailsInvalidApiBuilder("");
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Verify http code");
        softAssert.assertAll();

    }

}