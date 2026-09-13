package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void lockedUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.", "Error text does not match");
    }

    @Test
    public void emptyUserTest() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required", "Error text does not match");
    }

    @Test
    public void emptyPasswordTest() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required", "Error text does not match");
    }

    @Test
    public void registerUserTest() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorVisible(), "Error message does not appear");
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service", "Error text does not match");
    }

    @Test
    public void correctUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), "Products");
    }
}
