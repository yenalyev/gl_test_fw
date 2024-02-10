package api;

import api.helper.RequestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

import static api.ApiConfig.USERS_ENDPOINT;
import static api.helper.RequestHelper.createJsonFromObject;

public class UserHelper {
    private static Logger logger = Logger.getLogger(UserHelper.class.getName());

    public static User createUserFromResponse(Response response){
        User createdUser = null;
        try {
              createdUser = new User.Builder()
                    .setId(response.jsonPath().getInt("data.id"))
                    .setName(response.jsonPath().getString("data.name"))
                    .setEmail(response.jsonPath().getString("data.email"))
                    .setGender(response.jsonPath().getString("data.gender"))
                    .setStatus(response.jsonPath().getString("data.status"))
                    .build();
        } catch (Exception e){

        }


        return createdUser;
    }

    @Step("add user into service")
    public static User addUser(User user){
        RestAssured.defaultParser = Parser.JSON;
        Response response =  RequestHelper.doPostRequest(USERS_ENDPOINT, createJsonFromObject(user), true);
        User createdUser = createUserFromResponse(response);
        logger.info("User " + createdUser.toString() + " was created");
        return createdUser;
    }

    @Step("delete user from service")
    public static void deleteUser(User user){
        RequestHelper.doDeleteRequest("users/" + user.getId(),true);
        logger.info("User with id " + user.getId() + " was deleted");
    }

    public static User getUser(int userId){
        RestAssured.defaultParser = Parser.JSON;
        Response response =  RequestHelper.doGetRequest("users/" + userId, true);
        User createdUser = createUserFromResponse(response);
        logger.info("User " + createdUser.toString() + " was retrieved");
        return createdUser;
    }

    public static User putUser(User user){
        RestAssured.defaultParser = Parser.JSON;
        Response response =  RequestHelper.doPutRequest("users/"+user.getId(), createJsonFromObject(user), true);
        User createdUser = createUserFromResponse(response);
        logger.info("User " + createdUser.toString() + " was changed");
        return createdUser;
    }


    public static boolean compareUser(User a, User b){
        return a.getName().equals(b.getName()) && a.getEmail().equals(b.getEmail())
                && a.getGender().equals(b.getGender()) && a.getStatus().equals(b.getStatus());
    }
}
