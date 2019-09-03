package po;

import org.openqa.selenium.By;

public class LoginPage extends AbstractPage {

    private static final By LOGIN_INPUT_LOCATOR = By.xpath("//input[@name='email']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.cssSelector("button.btn--lg");
    private static final By ERROR_MESSAGE_LOCATOR = By.className("common-form-error");

    public LoginPage open() {
        browser.openUrl("https://stage.build-a-list-test.com");
        return this;
    }

    public LoginPage fillEmail(String email) {
        browser.type(LOGIN_INPUT_LOCATOR, email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        browser.type(PASSWORD_INPUT_LOCATOR, password);
        return this;
    }

    public MainPage clickSignIn() {
        browser.click(SIGN_IN_BUTTON_LOCATOR);
        return new MainPage();
    }

    public boolean errorMessagePresent() {
        return browser.isPresent(ERROR_MESSAGE_LOCATOR);
    }
}
