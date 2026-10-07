package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    private static final String URL = "https://www.saucedemo.com/";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(URL);
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