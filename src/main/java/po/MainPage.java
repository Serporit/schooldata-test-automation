package po;

import org.openqa.selenium.By;

public class MainPage extends AbstractPage {
    private static final By BUY_CREDITS_BUTTON_LOCATOR = By.xpath("//a[@data-e2e='buy-more-btn']");
    private static final By LIST_TAB_LOCATOR = By.xpath("//a[@href='./dashboard/lists']");
    private static final By VIEW_TAB_LOCATOR = By.xpath("//a[@href='./dashboard/views']");

    public boolean isBuyButtonPresent() {
        return browser.isPresent(BUY_CREDITS_BUTTON_LOCATOR);
    }

    public ViewsPage openViewsTab() {
        browser.click(VIEW_TAB_LOCATOR);
        return new ViewsPage();
    }

    public ListsPage openListsTab() {
        browser.click(LIST_TAB_LOCATOR);
        return new ListsPage();
    }
}