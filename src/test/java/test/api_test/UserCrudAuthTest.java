package test.api_test;

import api.UserHelper;
import api.helper.RequestHelper;
import com.github.javafaker.Faker;
import entity.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.AbstractTest;

import java.util.Locale;
import java.util.logging.Logger;

@Epic("API tests")
@Feature("User CRUD operation when authorized")
@Test(singleThreaded = true)
public class UserCrudAuthTest extends AbstractTest {

        private static Logger logger = Logger.getLogger(UserCrudAuthTest.class.getName());

        static Faker faker = new Faker(new Locale("en","TEST"));

        static User userUnderTest;
        static User user = new User.Builder()
                .setName(faker.name().fullName())
                .setEmail(faker.internet().emailAddress())
                .setStatus("active")
                .setGender("male")
                .build();

        @Test(groups = {"all", "api", "user", "crud_user"}, priority = 1)
        @Story("Add user with authorized post request")
        public void checkAddUser() {
            logger.info("Start add user test execution");
            userUnderTest = UserHelper.addUser(user);
            logger.info("User - " + userUnderTest.toString() + " was created");
            Assert.assertTrue(UserHelper.compareUser(user, userUnderTest));
            logger.info("Add user test passed");
        }

        @Test(groups = {"all", "api","user", "crud_user"}, priority = 2)
        @Story("Get user with authorized get request")
        public void checkGetUser() {
            logger.info("Start get user test execution");
            User getUser = UserHelper.getUser(userUnderTest.getId());
            logger.info("User - " + getUser.toString() + " was retrieved");
            Assert.assertTrue(UserHelper.compareUser(getUser, userUnderTest));
            logger.info("Get user test passed");
        }


        @Test(groups = {"all", "api", "user", "crud_user"}, priority = 3)
        @Story("Check update user with authorized put request")
        public void checkUpdateUser() {
            logger.info("Start check update user test execution");
            User forUpdate = UserHelper.getUser(userUnderTest.getId());
            logger.info("Old user name - " + forUpdate.getName());
            forUpdate.setName("new test name");
            logger.info("New user name - " + forUpdate.getName());
            User afterUpdate = UserHelper.putUser(forUpdate);
            logger.info("User - " + afterUpdate.toString() + " was updated");
            Assert.assertEquals(afterUpdate.getName(), forUpdate.getName());
            logger.info("Check user update user test passed");
        }

        @Test(groups = {"all", "api", "user", "crud_user"}, priority = 4)
        @Story("Check delete user with authorized put request")
        public void checkDeleteUser() {
            logger.info("Start delete user test execution");
            UserHelper.deleteUser(userUnderTest);
            logger.info("User - " + userUnderTest.toString() + " was deleted");
            int responseCode = RequestHelper.doGetRequest("users/" + userUnderTest.getId(), true).jsonPath().getInt("code");
            Assert.assertEquals(responseCode, 404);
            logger.info("Delete user test execution passed");
        }

}
