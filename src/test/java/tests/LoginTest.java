package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.LoginPage;
import pages.InventoryPage;
import utils.TestDataReader;
import utils.UserData;
import tests.LoginTest;

import java.util.List;

@ExtendWith(listeners.ScreenshotOnFailureExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends BaseTest {

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
            "standard_user1",
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

        System.out.println(
                "Running on thread: "
                        + Thread.currentThread().getName()
        );
    }

    @Test
    void screenshotTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        takeScreenshot("homePage");
    }

    @Test
    void failTest() {
        assertTrue(false);
    }
}