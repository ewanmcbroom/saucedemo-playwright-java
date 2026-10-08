package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.LoginPage;
import pages.InventoryPage;
import utils.TestDataReader;
import utils.UserData;
import tests.LoginTest;

import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    void successfulLogin() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);

        loginPage.open();

        TestDataReader reader = new TestDataReader();

        List<UserData> users = reader.getUsers();

        for (UserData user : users) {

            loginPage.login(
                    user.getUsername(),
                    user.getPassword()
            );
        }

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

    @ParameterizedTest
    @ValueSource(strings = {
            "standard_user",
            "problem_user",
            "performance_glitch_user"
    })
    void loginTest(String username) {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        loginPage.login(
                username,
                "secret_sauce"
        );
    }

    @Test
    void screenshotTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        takeScreenshot("homePage");
    }
}