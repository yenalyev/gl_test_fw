package api.helper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.SkipException;
import utilities.log.Log;
import utilities.properties.ApplicationConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestHelper {
    public static Response doPostRequest(String endpoint, String body) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            body(body).
                            post(new URL(ApplicationConfig.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            Log.Info("Error was happened while send POST request to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doGetRequest(String endpoint) {
        Log.Info("go to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        RestAssured.defaultParser = Parser.JSON;
        try {
            return
                    RestAssured.
                            given().
                            header(new Header("Content-type", "application/json")).
                            get(new URL(ApplicationConfig.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            Log.Info("Error was happened while send GET request to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doGetRequestAuth(String endpoint) {
        Log.Info("go to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            get(new URL(ApplicationConfig.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            Log.Info("Error was happened while send GET request to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doDeleteRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            delete(new URL(ApplicationConfig.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            Log.Info("Error was happened while send DELETE request to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }

    public static Response doPutRequest(String endpoint, String body) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            Header h1= new Header("Content-type", "application/json");
            Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
            List<Header> list = new ArrayList<>();
            list.add(h1);
            list.add(h2);
            Headers headers = new Headers(list);
            return
                    RestAssured.
                            given().
                            headers(headers).
                            body(body).
                            put(new URL(ApplicationConfig.BASE_URL_PATH + endpoint));
        } catch (MalformedURLException e){
            Log.Info("Error was happened while send POST request to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        }
        throw new SkipException("");
    }
}
