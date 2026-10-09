package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ProductData;
import utils.ProductDataReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest extends BaseTest {

    @Test
    void addProductToCart() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);

        // Login
        loginPage.open();
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        // Add product
        inventoryPage.addBackpackToCart();

        // Verify cart count
        assertEquals(
                "1",
                inventoryPage.getCartCount()
        );
    }


    @Test
    void removeProduct() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        CartPage cartPage = new CartPage(page);

        // Login
        loginPage.open();
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        inventoryPage.addBackpackToCart();

        inventoryPage.openCart();

        cartPage.removeBackpack();

        assertTrue(cartPage.isCartEmpty());
    }

    @Test
    void validateAllProducts() {

        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        ProductDataReader reader = new ProductDataReader();

        loginPage.open();
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        List<ProductData> products =
                reader.getProducts();

        for (ProductData product : products) {

            assertEquals(
                    product.getName(),
                    inventoryPage.getProductName(
                            product.getName()
                    )
            );

            assertEquals(
                    product.getPrice(),
                    inventoryPage.getProductPrice(
                            product.getName()
                    )
            );

            System.out.println(
                    "Running on thread: "
                            + Thread.currentThread().getName()
            );
        }
    }
}