package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.CheckoutData;
import utils.CsvDataReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void validateBasketSubtotal() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);

        // Login
        loginPage.open();
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        // Calculate expected subtotal
        double expectedSubtotal = 0;

        expectedSubtotal += inventoryPage.getProductPrice(
                "Sauce Labs Backpack"
        );

        expectedSubtotal += inventoryPage.getProductPrice(
                "Sauce Labs Bike Light"
        );

        expectedSubtotal += inventoryPage.getProductPrice(
                "Sauce Labs Fleece Jacket"
        );

        // Add products
        inventoryPage.addProductToCart(
                "Sauce Labs Backpack"
        );

        inventoryPage.addProductToCart(
                "Sauce Labs Bike Light"
        );

        inventoryPage.addProductToCart(
                "Sauce Labs Fleece Jacket"
        );

        // Checkout
        inventoryPage.openCart();

        cartPage.clickCheckout();

        checkoutPage.enterDetails(
                "Ewan",
                "McBroom",
                "TF3 4NT"
        );

        checkoutPage.clickContinue();

        // Read displayed subtotal
        double actualSubtotal =
                checkoutPage.getDisplayedSubtotal();

        System.out.println(
                "Expected: " + expectedSubtotal
        );

        System.out.println(
                "Actual: " + actualSubtotal
        );

        // Validate
        assertEquals(
                expectedSubtotal,
                actualSubtotal,
                0.01
        );
    }

    @Test
    void checkoutUsingCsvData() {

        CsvDataReader reader =
                new CsvDataReader();

        List<CheckoutData> data =
                reader.getCheckoutData();

        for (CheckoutData row : data) {

            LoginPage loginPage =
                    new LoginPage(page);

            InventoryPage inventoryPage =
                    new InventoryPage(page);

            CartPage cartPage =
                    new CartPage(page);

            CheckoutPage checkoutPage =
                    new CheckoutPage(page);

            loginPage.open();

            loginPage.login(
                    "standard_user",
                    "secret_sauce"
            );

            inventoryPage.addProductToCart(
                    "Sauce Labs Backpack"
            );

            inventoryPage.openCart();

            cartPage.clickCheckout();

            checkoutPage.enterDetails(
                    row.getFirstName(),
                    row.getLastName(),
                    row.getPostcode()
            );

            checkoutPage.clickContinue();
            checkoutPage.clickFinish();

            assertTrue(
                    checkoutPage.isOrderCompleted()
            );
        }
    }
}