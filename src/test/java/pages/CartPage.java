package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public void removeBackpack() {
        page.locator("#remove-sauce-labs-backpack").click();
    }

    public boolean isCartEmpty() {
        return page.locator(".cart_item").count() == 0;
    }

    public void clickCheckout() {
        page.locator("[data-test='checkout']").click();
    }
}