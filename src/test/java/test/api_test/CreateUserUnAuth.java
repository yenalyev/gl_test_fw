package test.api_test;

import api.UserHelper;
import api.helper.RequestHelper;
import com.github.javafaker.Faker;
import entity.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.logging.Logger;

import static api.ApiConfig.USERS_ENDPOINT;
import static api.helper.RequestHelper.createJsonFromObject;
import static org.testng.Assert.assertEquals;

@Epic("API tests")
@Feature("Unable CRUD operation user while unauthorized")
@Test(singleThreaded = true)
public class CreateUserUnAuth {

    private static Logger logger = Logger.getLogger(CreateUserUnAuth.class.getName());


    User userUnderTest = null;
    User user = null;

    @BeforeClass(alwaysRun = true)
    public void testDataGeneration(){
        Faker faker = new Faker(new Locale("en","TEST"));

        user = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(faker.internet().emailAddress())
                .setStatus("active")
                .setGender("male")
                .build();

    }


    @Test(groups = {"all", "api", "user", "crud_user"}, priority = 1)
    @Story("Unable create new user while unauthorized")
    public void checkAddUser_UnAuth() {
        logger.info("Create new user while UnAuth");
        Response response =  RequestHelper.doPostRequest(USERS_ENDPOINT, createJsonFromObject(user), false);
        assertEquals(response.getBody().jsonPath().getString("code"), "401");
    }

    @Test(groups = {"all", "api", "user", "crud_user"}, priority = 2)
    @Story("Unable create new user while unauthorized")
    public void checkGetUser_UnAuth() {
        logger.info("Create new user while Auth");
        userUnderTest = UserHelper.addUser(user);
        Assert.assertTrue(UserHelper.compareUser(userUnderTest, user));
        logger.info("Get user while UnAuth");
        Response response =  RequestHelper.doPostRequest(USERS_ENDPOINT, createJsonFromObject(user), false);
        assertEquals(response.getBody().jsonPath().getString("code"), "401");

    }

    @AfterClass(alwaysRun = true)
    public void cleanData(){
        UserHelper.deleteUser(user);
    }


}
