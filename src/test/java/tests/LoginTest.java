package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {

    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyUser(), "Epic sadface: Username is required"},
                {withEmptyPassword(), "Epic sadface: Password is required"},
                {withRegistrUser(), "Epic sadface: Username and password do not match any user in " +
                        "this service"}
        };
    }

    @Test(dataProvider = "incorrectData")
    public void incorrectDataLoginTest(User user, String errorMsg) {
        System.out.println("incorrectDataLoginTest is running in thread:" + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), errorMsg, "Error text does not match");
    }

    @Test
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread:" + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
