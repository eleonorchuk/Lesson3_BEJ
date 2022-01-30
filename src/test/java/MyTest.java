
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class MyTest {
    static Map<String,String> headers = new HashMap<>();
    static ResponseSpecification responseSpecification = null;
    // This link for testing existed image
    static String baseLink = "https://api.imgur.com/3/";

    static String username = "zoimy";
    static String albumHash = "xxxxxx";
    static String imageHash = "xxxxxx";

    static String albumLink()
    {
        return baseLink + "album/" + albumHash;
    }
    static String imageLink()
    {
        return baseLink + "image/" + imageHash;
    }

    @BeforeAll
    static void setUp()  {
        headers.put("Authorization", "Bearer f7c7fdf60b6ee204982e6df7f534d2b8a6198f04");
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //https://api.imgur.com/3/account/{{username}}/albums/ids/{{page}}
        String request = baseLink + "account/" + username + "/albums/ids/0";
        ArrayList ja = given()
                .headers(headers)
                .when()
                .get(request)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .get("data");
                albumHash = ja.get(0).toString();
                //https://api.imgur.com/3/account/{{username}}/album/{{albumHash}}
                LinkedHashMap jo = given()
                .headers(headers)
                .when()
                .get(baseLink + "account/" + username + "/album/" + albumHash)
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .get("data");
                ja = (ArrayList) jo.get("images");
                jo = (LinkedHashMap) ja.get(0);
                imageHash = jo.get("id").toString();
        ;
    }
    @Test
    void getAccountInfoTest() {
        given()
                .headers(headers)
                .when()
                .get(baseLink + "account/" + username)
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getAlbumResponseTest() {
        given()
                .headers(headers)
                .when()
                .get(albumLink())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void getImageDataTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()));
    }
    @Test
    void getImageFormatTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");

        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("png")));
    }
    @Test
    void getImageResponseTimeTest() {
        long result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .getTime();

        assertTrue(result < 1200);
    }
    @Test
    void getImageLinkTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("link")));
    }
    @Test
    void getImageIdTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("id")));
    }
    @Test
    void getImageNameTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("name")));
    }
    @Test
    void getImageSizeTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("size")));
    }
    @Test
    void getImageDateTimeTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("datetime")));
    }
    @Test
    void getImageDescriptionTest() {
        String result = given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .jsonPath()
                .getString("data");
        assertTrue((result != null) && (!result.isEmpty()) && (result.contains("description")));
    }
    @Test
    void getImageTest() {
        given()
                .headers(headers)
                .when()
                .get(imageLink())
                .then()
                .spec(responseSpecification);
    }
}
