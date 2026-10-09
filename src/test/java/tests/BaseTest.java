package tests;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.*;

import org.junit.jupiter.api.extension.ExtendWith;
import listeners.ScreenshotOnFailureExtension;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected static Page page;

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


    public static Page getPage() {
        return page;
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

    @Test
    void printProducts() {

        ProductDataReader reader =
                new ProductDataReader();

        List<ProductData> products =
                reader.getProducts();

        for (ProductData product : products) {

            System.out.println(product.getName());
            System.out.println(product.getPrice());

        }
    }

    @Test
    void printCsvData() {

        CsvDataReader reader =
                new CsvDataReader();

        List<CheckoutData> data =
                reader.getCheckoutData();

        for (CheckoutData row : data) {

            System.out.println(
                    row.getFirstName()
            );
        }
    }

}