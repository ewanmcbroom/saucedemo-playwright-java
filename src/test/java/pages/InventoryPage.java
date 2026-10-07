package pages;

import com.microsoft.playwright.Page;

public class InventoryPage {

    private final Page page;

    public InventoryPage(Page page) {
        this.page = page;
    }

    public boolean isDisplayed() {
        return page.locator(".inventory_list").isVisible();
    }

    public void addBackpackToCart() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
    }

    public void openCart() {
        page.locator(".shopping_cart_link").click();
    }

    public String getCartCount() {
        return page.locator(".shopping_cart_badge").textContent();
    }
}