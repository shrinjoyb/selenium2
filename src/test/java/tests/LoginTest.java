package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtil;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return ExcelUtil.getData("src/test/resources/testdata.xlsx", "LoginData");
    }

    @Test(dataProvider = "loginData")
    public void testNegativeLogin(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage(driver);

        //Login Complete
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        //Assertion
        if (expectedError.equals("Required")) {
            Assert.assertEquals(loginPage.getRequiredMassage(), expectedError);
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
        }
    }
}
