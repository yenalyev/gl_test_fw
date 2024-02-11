package test.api_test;


import api.UserHelper;
import api.helper.RequestHelper;
import com.github.javafaker.Faker;
import entity.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Locale;
import java.util.logging.Logger;

import static api.ApiConfig.USERS_ENDPOINT;
import static org.testng.Assert.assertEquals;

@Epic("API tests")
@Feature("Unable create new user with the same data")
@Test(singleThreaded = true)
public class UserCrudAuthNegative {

    private static Logger logger = Logger.getLogger(UserCrudAuthTest.class.getName());

    User userUnderTest = null;
    User user = null;
    User anotherUser = null;

    @BeforeClass(alwaysRun = true)
    public void testDataGeneration(){
        Faker faker = new Faker(new Locale("en","TEST"));

        String email = faker.internet().emailAddress();
        user = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(email)
                .setStatus("active")
                .setGender("male")
                .build();
        anotherUser = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(email)
                .setStatus("active")
                .setGender("male")
                .build();
    }


    @Test(groups = {"all", "api", "user", "crud_user"}, priority = 1)
    @Story("Unable create new user with the same email")
    public void checkAddUser() {

        logger.info("Create new user");
        userUnderTest = UserHelper.addUser(user);
        logger.info("User - " + userUnderTest.toString() + " was created");
        Assert.assertTrue(UserHelper.compareUser(user, userUnderTest));
        // test
        logger.info("Try to add anotherUser - " + anotherUser.toString());
        Response response = RequestHelper.doPostRequest(USERS_ENDPOINT, RequestHelper.createJsonFromObject(anotherUser), true);
        assertEquals(response.getBody().jsonPath().getString("code"), "422");
        logger.info("Unable create new user with the same email");
    }


    @Test(groups = {"all", "api", "user", "crud_user"}, priority = 2)
    @Story("Validate json schema while create new user with the same email")
    public void validateJSONSchema() {

        // test
        logger.info("Try to add anotherUser - " + anotherUser.toString());
        Response response = RequestHelper.doPostRequest(USERS_ENDPOINT, RequestHelper.createJsonFromObject(anotherUser), true);
        logger.info("validate json schema");
        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("json_schema/same_email_response.json")
        );
    }


    @AfterClass(alwaysRun = true)
    public void clearData(){
        UserHelper.deleteUser(userUnderTest);
    }

}
