package listeners;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import tests.BaseTest;

import java.nio.file.Paths;

public class ScreenshotOnFailureExtension
        implements TestWatcher {

    @Override
    public void testFailed(
            ExtensionContext context,
            Throwable cause) {

        Page page = BaseTest.getPage();

        if (page != null) {

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(
                                    Paths.get(
                                            "src/test/java/tests/tests_results/screenshots/"
                                                    + context.getDisplayName()
                                                    + ".png"
                                    )
                            )
            );
        }
    }
}