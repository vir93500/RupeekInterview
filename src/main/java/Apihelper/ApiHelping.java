package Apihelper;

import ApiUtilities.Httpmethods;
import Request.JWTRequest;
import Response.*;
import Utilities.GenericResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import static Constants.BaseContants.*;

public class ApiHelping {
    RequestSpecification requestSpecification;
    Httpmethods httpmethods = new Httpmethods();
    GenericResponseDto genericResponseDto = new GenericResponseDto();

    public ApiHelping(){
        RestAssured.baseURI = baseURI;
    }

    public GenericResponseDto<JWTTokenResponse> jwtTokenApiBuilder(final JWTRequest requestBody){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(JWT_TOKEN_ENDPOINT);
        requestSpecBuilder.setBody(requestBody);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        Response response = httpmethods.postResponse(requestSpecification);
        System.out.println(response.asString());
        return genericResponseDto.toClass(response, JWTTokenResponse.class);
    }


    public JsonPath allUserDetailsApiBuilder(final String authorizationToken){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(ALLUSER_DETAILS_ENDPOINT);
        requestSpecBuilder.addHeader("Authorization",authorizationToken);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        Response response = httpmethods.getResponse(requestSpecification);
        System.out.println(response.asString());
        return response.jsonPath();
    }

    public Response allUserDetailsInvalidApiBuilder(final String authorizationToken){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(ALLUSER_DETAILS_ENDPOINT);
        requestSpecBuilder.addHeader("Authorization",authorizationToken);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        Response response = httpmethods.getResponse(requestSpecification);
        System.out.println(response.asString());
        return response;
    }

    public GenericResponseDto<AllUserResponse> userDetailsByPhoneApiBuilder(final String authorizationToken,final String phone){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(USER_DETAILS_BY_PHONE_ENDPOINT+"/"+phone);
        requestSpecBuilder.addHeader("Authorization","Bearer "+authorizationToken);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        Response response = httpmethods.getResponse(requestSpecification);
        System.out.println("-----------"+response.asString());
        return genericResponseDto.toClass(response, AllUserResponse.class);
    }

    public Response userDetailsInvalidByPhoneApiBuilder(final String authorizationToken,final String phone){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(USER_DETAILS_BY_PHONE_ENDPOINT+"/"+phone);
        requestSpecBuilder.addHeader("Authorization",authorizationToken);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();
        Response response = httpmethods.getResponse(requestSpecification);
        System.out.println("-----------"+response.asString());
        return response;
    }


}
