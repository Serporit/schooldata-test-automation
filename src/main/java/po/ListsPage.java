package po;

import org.openqa.selenium.By;
import reporting.Logger;

public class ListsPage extends MainPage {
    private static final By CREATE_LIST_BUTTON_LOCATOR = By.className("btn__create-list--wrap");


    public ListCreationPage openListCreator() {
        browser.click(CREATE_LIST_BUTTON_LOCATOR);
        return new ListCreationPage();
    }


    public ListsPage waitUntilActiveStatus(String listName) {
        Logger.info("Waiting for List activation...");
        browser.waitForElementPresent(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']//span[@data-e2e='list-status' and contains(text(),'Active')]"), 300);
        return this;
    }

    public String getListStatus(String listName) {
        return browser.read(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']//span[@data-e2e='list-status']"));
    }

    public ListsPage duplicateList(String listName) {
        By listMenuLocator = By.xpath("//span[contains(text(),'" + listName + "') and not(contains(text(),'duplicate'))]/ancestor::div[@class='dashboard-list container']//div[@class='dashboard-list__btn--nav']");
        browser.click(listMenuLocator);
        browser.click(By.xpath("//button[contains(text(),'Duplicate')]"));
        return this;
    }

    public ListCreationPage openList(String listName) {
        browser.click(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']"));
        return new ListCreationPage();
    }

    public void downloadList(String listName) {
        browser.click(By.xpath("//span[contains(text(),'" + listName + "') and not(contains(text(),'duplicate'))]/ancestor::div[@class='dashboard-list container']//div[@class='dashboard-list__btn--nav']"));
        browser.click(By.xpath("//button[contains(text(),'Download')]"));
    }

    public ListsPage chooseFormat(String format) {
        browser.click(By.xpath("//div[3]//bl-radio[1]"));
        return this;
    }

    public void clickDownload() {
        browser.click(By.xpath("//button[@data-e2e='download-btn']"));
    }

    public void clickYesIGotIt() {
        browser.click(By.xpath("//button[@data-e2e='download-yes-btn']"));
        browser.waitForElementDisappear(By.xpath("//button[@data-e2e='download-yes-btn']"));
    }

    public void renameList(String oldName, String newName) {
        browser.click(By.xpath("//span[contains(text(),'" + oldName + "')]/ancestor::div[@class='dashboard-list container']//div[@class='dashboard-list__btn--nav']"));
        browser.click(By.xpath("//button[contains(text(),'Rename')]"));
//        browser.type(By.cssSelector("input.ng-pristine"),newName);
        browser.renameByKeyboard(newName);
//        browser.click(By.xpath("//a[@href='/dashboard/lists']"));
    }

    public void deleteList(String listName) {
        browser.click(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']//div[@class='dashboard-list__btn--nav']"));
        browser.click(By.xpath("//button[contains(text(),'Delete')]"));
        browser.click(By.xpath("//button[@data-e2e='yes-activate-btn']"));
        browser.waitForElementDisappear(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']"));
    }

    public boolean isDashBoardOpened() {
        return browser.isPresent(By.xpath("//div[@data-e2e='all-lists-container']"));
    }

    public boolean isDownloadFormOpened() {
        return browser.isPresent(By.xpath("//div[@data-e2e='download-control-form']"));
    }

    public boolean isConfirmDownloadOpened() {
        return browser.isPresent(By.xpath("//button[@data-e2e='download-yes-btn']"));
    }

    public boolean isConfirmDownloadAbsent() {
        return browser.isAbsent(By.xpath("//button[@data-e2e='download-yes-btn']"));
    }

    public boolean isListPresent(String listName) {
        return browser.isPresent(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']"));
    }

    public boolean isListAbsent(String listName) {
        return browser.isAbsent(By.xpath("//span[contains(text(),'" + listName + "')]/ancestor::div[@class='dashboard-list container']"));
    }
}
