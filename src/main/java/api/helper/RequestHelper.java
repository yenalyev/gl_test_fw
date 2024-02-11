package api.helper;

import api.UserHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHelper {
    private static Logger logger = Logger.getLogger(RequestHelper.class.getName());

    @Step("Do post request")
    public static Response doPostRequest(String endpoint, String body, boolean isAuth) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            List<Header> list = new ArrayList<>();
            Header h1= new Header("Content-type", "application/json");
            list.add(h1);
            if (isAuth){
                Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
                list.add(h2);
            }
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


    public static Response doGetRequest(String endpoint, boolean isAuth) {
        Log.Info("go to " + ApplicationConfig.BASE_URL_PATH + endpoint);
        RestAssured.defaultParser = Parser.JSON;
        try {
            List<Header> list = new ArrayList<>();
            Header h1= new Header("Content-type", "application/json");
            list.add(h1);
            if (isAuth){
                Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
                list.add(h2);
            }
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

    public static Response doDeleteRequest(String endpoint, boolean isAuth) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            List<Header> list = new ArrayList<>();
            Header h1= new Header("Content-type", "application/json");
            list.add(h1);
            if (isAuth){
                Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
                list.add(h2);
            }
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

    public static Response doPutRequest(String endpoint, String body, boolean isAuth) {
        RestAssured.defaultParser = Parser.JSON;
        try {
            List<Header> list = new ArrayList<>();
            Header h1= new Header("Content-type", "application/json");
            list.add(h1);
            if (isAuth){
                Header h2 = new Header("Authorization", "Bearer " + ApplicationConfig.ACCESS_TOKEN);
                list.add(h2);
            }
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

    public static <T> String createJsonFromObject(T t){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error was happened while transforming object " + t.toString() + " into JSON");
            logger.log(Level.WARNING, e.getMessage());
        }
        return jsonString;
    }

 }
