package test.api_test;

import api.UserHelper;
import api.helper.RequestHelper;
import entity.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.AbstractTest;
import utilities.log.Log;


public class UserCrudTest extends AbstractTest {


    @Test(groups = {"all"})
    public void test(){
        Response response = RequestHelper.doGetRequest("https://reqbin.com/echo/post/json");
        int code = response.statusCode();
        Assert.assertEquals(code, 404);
        Log.Info("Test passed");
    }
}
