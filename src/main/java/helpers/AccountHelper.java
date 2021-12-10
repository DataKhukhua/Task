package helpers;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.*;

import static io.restassured.RestAssured.given;


public class AccountHelper {
    private static final String BASE_URL = "https://bookstore.toolsqa.com/Account/v1";

    public AccountHelper() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response userPost(Login credentials) {
        RequestSpecification request = given().contentType(ContentType.JSON).body(credentials);
        return request.post(Endpoints.USER);
    }

    public String authorizePost(Login credentials) {
        RequestSpecification request = given().contentType(ContentType.JSON).body(credentials);
        return request.post(Endpoints.AUTHORIZED).asString();

    }

    public Token generateToken(Login credentials) {
        RequestSpecification request = given().contentType(ContentType.JSON).body(credentials);
        return request.post(Endpoints.GENERATE_TOKEN).as(Token.class);
    }


}
