package po;

import org.openqa.selenium.By;

public class ViewsPage extends MainPage {
    private static final By SEARCH_BUTTON_LOCATOR = By.className("btn__quick-search--wrap");

    public SearchPage clickSearch() {
        browser.click(SEARCH_BUTTON_LOCATOR);
        return new SearchPage();
    }
}
