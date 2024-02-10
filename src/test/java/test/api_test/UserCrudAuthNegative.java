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
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.logging.Logger;

import static api.ApiConfig.USERS_ENDPOINT;

@Epic("API tests")
@Feature("Unable create new user with the same data")
@Test(singleThreaded = true)
public class UserCrudAuthNegative {

    private static Logger logger = Logger.getLogger(UserCrudAuthTest.class.getName());

    static Faker faker = new Faker(new Locale("en","TEST"));


    @Test(groups = {"all", "api", "user", "crud_user"}, priority = 1)
    @Story("Unable create new user with the same email")
    public void checkAddUser() {
        // preconditions
        User userUnderTest = null;
        String email = faker.internet().emailAddress();
        User user = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(email)
                .setStatus("active")
                .setGender("male")
                .build();
        User anotherUser = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(email)
                .setStatus("active")
                .setGender("male")
                .build();

        logger.info("Create new user");
        userUnderTest = UserHelper.addUser(user);
        logger.info("User - " + userUnderTest.toString() + " was created");
        Assert.assertTrue(UserHelper.compareUser(user, userUnderTest));
        // test
        logger.info("Try to add anotherUser - " + anotherUser.toString());
        Response response = RequestHelper.doPostRequest(USERS_ENDPOINT, RequestHelper.createJsonFromObject(anotherUser), true);
        Assert.assertEquals(response.getBody().jsonPath().getString("code"), "422");
        logger.info("Unable create new user with the same email");

        //postconditions
        UserHelper.deleteUser(userUnderTest);
    }

}
