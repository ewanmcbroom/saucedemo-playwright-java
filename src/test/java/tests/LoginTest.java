package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import pages.LoginPage;
import pages.InventoryPage;

public class LoginTest extends BaseTest {

    @Test
    void successfulLogin() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);

        loginPage.open();

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        assertTrue(
                inventoryPage.isDisplayed()
        );
    }

    @Test
    void invalidLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        loginPage.login(
                "invalid_user",
                "wrong_password"
        );

        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage()
        );
    }
}