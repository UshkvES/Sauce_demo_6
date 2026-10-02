package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//*[text()='%s']" +
            "/ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By cartBadge = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Отображение наименования страницы")
    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Проверка текста наименования страницы")
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }

    @Step("Добавление товаров в корзину по названию")
    public void addGoodsToCart(String goodsName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCartBtn).click();
    }

    @Step("Добавление товаров в корзину по индексу")
    public void addGoodsToCart(int goodsIndex) {
        By addToCartBtn = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartBtn).get(goodsIndex).click();
    }

    @Step("Проверка отображения кол-ва товаров в корзине")
    public boolean isNumberVisible() {
        return driver.findElement(cartBadge).isDisplayed();
    }

    @Step("Проверка текста кол-ва товаров в корзине")
    public String checkCountersValue() {
        return driver.findElement(cartBadge).getText();
    }

    @Step("Проверка цвета иконки с кол-вом товаров")
    public String checkCountersColor() {
        return driver.findElement(cartBadge).getCssValue(
                "background-color");
    }

    @Step("Переход на страницу корзины")
    public void switchToCart () {
        driver.findElement(cartLink).click();
    }
}
