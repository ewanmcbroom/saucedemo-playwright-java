package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {

    @Test
    void checkoutOrder() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);

        loginPage.open();
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        inventoryPage.addBackpackToCart();

        inventoryPage.openCart();

        cartPage.clickCheckout();

        checkoutPage.enterDetails(
                "Ewan",
                "McBroom",
                "TF3 4NT"
        );

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        assertTrue(
                checkoutPage.isOrderCompleted()
        );
    }
}