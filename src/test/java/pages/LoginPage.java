package pages;

import com.microsoft.playwright.Page;
import utils.ConfigManager;

public class LoginPage {

    private final Page page;


    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        ConfigManager config =
                new ConfigManager();

        page.navigate(
                config.getUrl());
    }

    public void login(String username, String password) {
        page.locator("#user-name").fill(username);
        page.locator("#password").fill(password);
        page.locator("#login-button").click();
    }

    public String getErrorMessage() {
        return page.locator("[data-test='error']").textContent();
    }
}