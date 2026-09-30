package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginBtn = By.id("login-button");
    private final By error = By.cssSelector(".error-message-container");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под кредами пользователя")
    public void login(User user) {
        driver.findElement(usernameInput).sendKeys(user.getUser());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginBtn).click();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorVisible() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверка текста сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
