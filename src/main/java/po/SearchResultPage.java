package po;

import org.openqa.selenium.By;

public class SearchResultPage extends AbstractPage {
    private static final By DISTRICTS_COUNT_LOCATOR = By.xpath("//bl-tab[@ng-reflect-label='districts']/a/span[2]");
    private static final By SCHOOLS_COUNT_LOCATOR = By.xpath("//bl-tab[@ng-reflect-label='schools']/a/span[2]");
    private static final By COLLEGES_COUNT_LOCATOR = By.xpath("//bl-tab[@ng-reflect-label='colleges']/a/span[2]");
    private static final String DATA_COUNT_LOCATOR_PATTERN = "//bl-tab//span[contains(text(),'%s')]/following-sibling::span";

    public int getDisctrictsCount() {
        return Integer.parseInt(browser.read(DISTRICTS_COUNT_LOCATOR));
    }

    public int getSchoolsCount() {
        return Integer.parseInt(browser.read(SCHOOLS_COUNT_LOCATOR));
    }

    public int getCollegesCount() {
        return Integer.parseInt(browser.read(COLLEGES_COUNT_LOCATOR));
    }

    public int getCount(String dataType) {
        return Integer.parseInt(browser.read(By.xpath(String.format(DATA_COUNT_LOCATOR_PATTERN, dataType))));
    }
}
