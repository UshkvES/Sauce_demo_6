package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Страница со списком товаров магазина")
@Owner("Элина Ушакова, @Ushkv_ES")
@Feature("Наполнение корзины")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Fleece Jacket",
                    "Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Bolt T-Shirt");

    @Story("Добавление товаров в корзину, отображение кол-ва товаров в корзине")
    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }

        productsPage.addGoodsToCart(1);

        assertTrue(productsPage.isNumberVisible());
        assertEquals(productsPage.checkCountersValue(), "4");
        assertEquals(productsPage.checkCountersColor(), "rgba(226, 35, 26, 1)");
    }
}
