package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {

    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void enterDetails(String firstName,
                             String lastName,
                             String postalCode) {

        page.locator("[data-test='firstName']").fill(firstName);
        page.locator("[data-test='lastName']").fill(lastName);
        page.locator("[data-test='postalCode']").fill(postalCode);
    }

    public void clickContinue() {
        page.locator("[data-test='continue']").click();
    }

    public void clickFinish() {
        page.locator("[data-test='finish']").click();
    }

    public boolean isOrderCompleted() {
        return page.locator(".complete-header")
                .textContent()
                .equals("Thank you for your order!");
    }

    public double getDisplayedSubtotal() {

        String subtotal = page.locator(
                ".summary_subtotal_label"
        ).textContent();

        subtotal = subtotal
                .replace("Item total: $", "");

        return Double.parseDouble(subtotal);
    }
}