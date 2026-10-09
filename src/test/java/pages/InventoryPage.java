package pages;

import com.microsoft.playwright.Locator;
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

    public double getProductPrice(String productName) {

        String priceText = page.locator(".inventory_item")
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(productName)
                )
                .locator(".inventory_item_price")
                .textContent();

        return Double.parseDouble(
                priceText.replace("$", "")
        );
    }

    public String getProductName(String productName) {

        return page.locator(".inventory_item")
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(productName)
                )
                .locator(".inventory_item_name")
                .textContent();
    }

    public void addProductToCart(String productName) {

        page.locator(".inventory_item")
                .filter(
                        new Locator.FilterOptions()
                                .setHasText(productName)
                )
                .locator("button")
                .click();
    }


}