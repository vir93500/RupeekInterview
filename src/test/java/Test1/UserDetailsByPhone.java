package Test1;

import Apihelper.ApiHelping;
import BaseTest.TestSetup;
import Request.JWTRequest;
import Response.AllUserResponse;
import Response.JWTTokenResponse;
import Utilities.GenericResponseDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static Constants.JWTCredentials.PASSWORD;
import static Constants.JWTCredentials.*;
import static Constants.JWTCredentials.USERNAME;

public class UserDetailsByPhone extends TestSetup {

    public JWTTokenResponse emailResponse;
    public ApiHelping apiHelping;
    public JWTRequest jwtRequest;

    @BeforeMethod
    public void UserDetailsByPhone() {
        emailResponse = new JWTTokenResponse();
        apiHelping = new ApiHelping();
        jwtRequest = new JWTRequest();
    }

    @Test(description = "Verify happy flow to get user details by phone no.")
    public void allUserDataTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String jwtBearerToken = apiHelping.jwtTokenApiBuilder(jwtRequest).getData().getToken();
        GenericResponseDto<AllUserResponse> response =  apiHelping.userDetailsByPhoneApiBuilder(jwtBearerToken,PHONE);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Verify http code");
        softAssert.assertTrue(response.getData().getFirst_name()!=null,"verified firstname successfuly");
        softAssert.assertTrue(response.getData().getLast_name()!=null,"verified lastname successfuly");
        softAssert.assertTrue(response.getData().getCareer()!=null,"verified career successfuly");
        softAssert.assertEquals(response.getData().getPhone(),PHONE,"verified phone No. successfuly");
        softAssert.assertAll();
    }

    @Test(description = "Verify get user details with invalid phone no.")
    public void allUserDataInvalidPhoneTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String jwtBearerToken = apiHelping.jwtTokenApiBuilder(jwtRequest).getData().getToken();
        Response response =  apiHelping.userDetailsInvalidByPhoneApiBuilder("Bearer "+jwtBearerToken,INVALID_PHONE);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Verify http code");
        softAssert.assertEquals(response.asString(),"","verified response body is empty");
        softAssert.assertAll();

    }

    @Test(description = "Verify get user details with null phone no.")
    public void allUserDataNullPhoneTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String jwtBearerToken = apiHelping.jwtTokenApiBuilder(jwtRequest).getData().getToken();
        Response response =  apiHelping.userDetailsInvalidByPhoneApiBuilder("Bearer "+jwtBearerToken,null);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Verify http code");
        softAssert.assertEquals(response.asString(),"","verified response body is empty");
        softAssert.assertAll();

    }

    @Test(description = "Verify get user details with empty phone no.")
    public void allUserDataEmptyPhoneTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String jwtBearerToken = apiHelping.jwtTokenApiBuilder(jwtRequest).getData().getToken();
        Response response =  apiHelping.userDetailsInvalidByPhoneApiBuilder("Bearer "+jwtBearerToken,"");
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Verify http code");
        softAssert.assertEquals(response.asString(),"","verified response body is empty");
        softAssert.assertAll();

    }

    @Test(description = "Verify get user details with invalid authorization token")
    public void allUserDataInvalidTokenTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        String invalidJWTBearerToken = INVALID_AUTHORIZATION_TOKEN;
        Response response =  apiHelping.userDetailsInvalidByPhoneApiBuilder(invalidJWTBearerToken,PHONE);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Verify http code");
        softAssert.assertEquals(response.asString(),"","verified response body is empty");
        softAssert.assertAll();

    }

    @Test(description = "Verify get user details with empty authorization token")
    public void allUserDataNullTokenTest() {
        jwtRequest.setUsername(USERNAME);
        jwtRequest.setPassword(PASSWORD);
        Response response =  apiHelping.userDetailsInvalidByPhoneApiBuilder("",PHONE);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Verify http code");
        softAssert.assertAll();

    }

}