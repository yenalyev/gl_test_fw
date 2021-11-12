package api;

import api.helper.RequestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserHelper {
    private static Logger logger = Logger.getLogger(UserHelper.class.getName());

    public static User addUser(User user){
        RestAssured.defaultParser = Parser.JSON;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error was happened while transforming user " + user.toString() + " into JSON");
            logger.log(Level.WARNING, e.getMessage());
        }
        Response response =  RequestHelper.doPostRequest("users", jsonString);
        User createdUser = new User.Builder()
                .setId(response.jsonPath().getInt("data.id"))
                .setName(response.jsonPath().getString("data.name"))
                .setEmail(response.jsonPath().getString("data.email"))
                .setGender(response.jsonPath().getString("data.gender"))
                .setStatus(response.jsonPath().getString("data.status"))
                .setCreateDate(response.jsonPath().getString("data.created_at"))
                .setUpdateDate(response.jsonPath().getString("data.updated_at"))
                .build();
        logger.info("User " + createdUser.toString() + " was created");
        return createdUser;
    }

    public static void deleteUser(User user){
        RequestHelper.doDeleteRequest("users/" + user.getId());
        logger.info("User with id " + user.getId() + " was deleted");
    }

    public static User getUser(int userId){
        RestAssured.defaultParser = Parser.JSON;
        Response response =  RequestHelper.doGetRequest("users/" + userId);
        User createdUser = new User.Builder()
                .setId(response.jsonPath().getInt("data.id"))
                .setName(response.jsonPath().getString("data.name"))
                .setEmail(response.jsonPath().getString("data.email"))
                .setGender(response.jsonPath().getString("data.gender"))
                .setStatus(response.jsonPath().getString("data.status"))
                .setCreateDate(response.jsonPath().getString("data.created_at"))
                .setUpdateDate(response.jsonPath().getString("data.updated_at"))
                .build();
        logger.info("User " + createdUser.toString() + " was created");
        return createdUser;
    }

    public static User putUser(User user){
        RestAssured.defaultParser = Parser.JSON;
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error was happened while transforming user " + user.toString() + " into JSON");
            logger.log(Level.WARNING, e.getMessage());
        }
        Response response =  RequestHelper.doPutRequest("users/"+user.getId(), jsonString);
        User createdUser = new User.Builder()
                .setId(response.jsonPath().getInt("data.id"))
                .setName(response.jsonPath().getString("data.name"))
                .setEmail(response.jsonPath().getString("data.email"))
                .setGender(response.jsonPath().getString("data.gender"))
                .setStatus(response.jsonPath().getString("data.status"))
                .setCreateDate(response.jsonPath().getString("data.created_at"))
                .setUpdateDate(response.jsonPath().getString("data.updated_at"))
                .build();
        logger.info("User " + createdUser.toString() + " was changed");
        return createdUser;
    }


    public static boolean compareUser(User a, User b){
        return a.getName().equals(b.getName()) && a.getEmail().equals(b.getEmail())
                && a.getGender().equals(b.getGender()) && a.getStatus().equals(b.getStatus());
    }
}
