package po;

import org.openqa.selenium.By;

public class SearchPage extends AbstractPage {
    private static final By SEARCH_QUERY_LOCATOR = By.cssSelector("input.with-search-icon");
    private static final By SEARCH_BUTTON_LOCATOR = By.cssSelector("i.icon-search");

    public SearchPage fillSearchForm(String query) {
        browser.type(SEARCH_QUERY_LOCATOR, query);
        return this;
    }

    public SearchResultPage clickSearchButton() {
        browser.click(SEARCH_BUTTON_LOCATOR);
        return new SearchResultPage();
    }
}
