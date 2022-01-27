import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class MyTest {
    static Map<String,String> headers = new HashMap<>();
    static Properties prop =new Properties();
    @BeforeAll
    static void setUp()  {
        headers.put("Authorization", "Bearer f7c7fdf60b6ee204982e6df7f534d2b8a6198f04");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    @Test
    void getAccountInfoTest() {
        given()
                .headers(headers)
                .when()
                .get("https://api.imgur.com/3/account/zoimy")
                .then()
                .statusCode(200);
    }

    @Test
    void getAlbumResponseTest() {
        given()
                .headers(headers)
                .when()
                .get("https://api.imgur.com/3/album/fDGpocu")
                .then()
                .statusCode(200);
    }
    @Test
    void getImageDataTest() {
        String result = given()
                .headers(headers)
                .when()
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
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
                .get("https://api.imgur.com/3/image/WoRKa6c")
                .then()
                .statusCode(200);
    }
}
