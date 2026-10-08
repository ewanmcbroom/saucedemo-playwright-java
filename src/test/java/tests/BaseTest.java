package tests;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigManager;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected void takeScreenshot(String testName) {

        System.out.println("Taking screenshot...");

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(
                                Paths.get(
                                        "src/test/java/tests/tests_results/screenshots/"
                                                + testName
                                                + ".png"
                                )
                        )
        );
    }

    @BeforeEach
    public void setUp() {

        playwright = Playwright.create();

        ConfigManager config = new ConfigManager();

        String browserName =
                config.getBrowser().trim();

        if (browserName.equalsIgnoreCase("firefox")) {

            browser = playwright.firefox()
                    .launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config.isHeadless())
                    );

        } else if (browserName.equalsIgnoreCase("webkit")) {

            browser = playwright.webkit()
                    .launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config.isHeadless())
                    );

        } else {

            browser = playwright.chromium()
                    .launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(config.isHeadless())
                    );
        }

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void tearDown() {
        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}