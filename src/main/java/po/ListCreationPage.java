package po;

import org.openqa.selenium.By;

public class ListCreationPage extends MainPage {

    private static final By LIST_NAME_INPUT_LOCATOR = By.xpath("//input[@data-e2e='add-list-name-input']");
    private static final By INSTITUTION_TYPE_CARD_LOCATOR = By.xpath("//span[contains(text(),'Institution Types')]");
    private static final By REVIEW_AND_ACTIVATE_BUTTON_LOCATOR = By.xpath("//button[@data-e2e='review-activate-btn']");

    public ListCreationPage clickListTypeCard(String listType) {
        browser.click(By.xpath(String.format("//div[@class='name' and contains(text(),'%s')]", listType)));
        return this;
    }

    public ListCreationPage fillNameInput(String listName) {
        browser.type(LIST_NAME_INPUT_LOCATOR, listName);
        return this;
    }

    public ListCreationPage clickInstTypes() {
        browser.click(INSTITUTION_TYPE_CARD_LOCATOR);
        return this;
    }

    public ListCreationPage clickGeography() {
        browser.click(By.xpath("//span[contains(text(),'Geography')]"));
        return this;
    }

    public ListCreationPage clickReviewOrder() {
        browser.click(By.xpath("//a[text()='Review your Order']"));
        return this;
    }

    public ListCreationPage clickReviewAndActivate() {
        browser.click(REVIEW_AND_ACTIVATE_BUTTON_LOCATOR);
        return this;
    }

    public ListCreationPage clickActivate() {
        browser.click(By.xpath("//button[@data-e2e='activate-btn' and contains(text(),'Activate')]"));
        return this;
    }

    public ListsPage clickYesActivate() {
        By yesActivateButtonLocator = By.xpath("//button[@data-e2e='yes-activate-btn']");
        browser.click(yesActivateButtonLocator);
        browser.waitForElementDisappear(yesActivateButtonLocator);
        try {
            browser.waitForElementPresent(By.cssSelector("div.overlay"), 5);
        } catch (Exception e) {
        }
        return new ListsPage();
    }

    public ListCreationPage setInstTypes(String instType) {
        if (instType != null && !instType.isEmpty()) {
            browser.click(By.xpath("//span[contains(text(),'" + instType + "')]"));
            waitForSavingChanges();
        }
        By instTypesTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Institution Types')]/../..");
        browser.scrollUpTo(instTypesTabLocator);
        browser.clickThrough(instTypesTabLocator);
        return this;
    }

    public ListCreationPage setGeography(String geography) {
        if (geography != null && !geography.isEmpty()) {
            By geographyTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Geography')]/../..");
            browser.click(geographyTabLocator);
            browser.type(By.xpath("//input[contains(@placeholder,'Search by ')]"), geography);
            browser.click(By.xpath("(//li//*[contains(@class,'suggestion')])[1]"));
            waitForSavingChanges();
            browser.scrollUpTo(geographyTabLocator);
            browser.click(geographyTabLocator);
        }
        return this;
    }

    public boolean setCityOnly() {
        By geographyTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Geography')]/../..");
        browser.click(geographyTabLocator);
//        browser.forceMoveElementForward(By.xpath("//div[@class='geo-tag']"));
        browser.waitForElementEnabled(By.xpath("//div[@class='geo-tag']"));
        browser.click(By.xpath("//div[@class='geo-tag']"));
        browser.click(By.xpath("//span[@class='action']"));
        boolean changed = waitForSavingChanges();
        browser.scrollUpTo(geographyTabLocator);
        browser.click(geographyTabLocator);
        return changed;
    }

    public boolean setGeographyAndCheckChanges(String geography) {
        boolean changed = false;
        if (geography != null && !geography.isEmpty()) {
            By geoTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Geography')]/../..");
//            browser.click(geoTabLocator);
            browser.type(By.xpath("//input[contains(@placeholder,'Search by ')]"), geography);
            browser.click(By.xpath("(//li//*[contains(@class,'suggestion')])[1]"));
            changed = waitForSavingChanges();
            browser.scrollUpTo(geoTabLocator);
            browser.click(geoTabLocator);
        }
        return changed;
    }

    public ListCreationPage setPersonnel(String personnel) {
        if (personnel != null && !personnel.isEmpty()) {
            By personnelTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Personnel')]/../..");
            browser.click(personnelTabLocator);
            browser.type(By.xpath("//input[contains(@placeholder,'Science')]"), personnel);
            browser.click(By.xpath("(//li//*[contains(@class,'suggestion')])[1]"));
            waitForSavingChanges();
            browser.scrollUpTo(personnelTabLocator);
            browser.click(personnelTabLocator);
        }
        return this;
    }

    public ListCreationPage setNames(String names) {
        if (names != null && !names.isEmpty()) {
            By namesTabLocator = By.xpath("//span[@data-e2e='targeting-name' and contains(text(),'Names')]/../..");
            browser.click(namesTabLocator);
            browser.type(By.xpath("//input[contains(@placeholder,'Institution Names')]"), names);
            browser.scrollUpTo(By.xpath("(//li//*[contains(@class,'suggestion')])[1]"));
            browser.click(By.xpath("(//li//*[contains(@class,'suggestion')])[1]"));
            waitForSavingChanges();
            browser.click(By.xpath("//div[@class='pids-only']//span[@class='fake-checkbox']"));
            browser.click(By.xpath("//button[contains(text(),'Target Names and PIDs only')]"));
            waitForSavingChanges();
            browser.click(namesTabLocator);
        }
        return this;
    }

    public ListCreationPage setParentPid(boolean parentPid) {
        if (parentPid) {
            browser.click(By.xpath("//bl-list-append-field[1]//bl-list-append-field-btn"));
            browser.waitForElementPresent(By.xpath("//bl-list-append-field[1]//bl-list-append-field-btn//span[text()='Added!' or text()='Remove']"));
        }
        return this;
    }

    public ListCreationPage setParentInst(boolean parentInst) {
        if (parentInst) {
            browser.scrollDownTo(By.xpath("//bl-list-appends-container"));
            browser.click(By.xpath("//bl-list-append-field[2]//bl-list-append-field-btn"));
            browser.waitForElementPresent(By.xpath("//bl-list-append-field[2]//bl-list-append-field-btn//span[text()='Added!' or text()='Remove']"));
        }
        return this;
    }

    public ListCreationPage setUltParentPid(boolean ultParentPid) {
        if (ultParentPid) {
            browser.scrollDownTo(By.xpath("//bl-list-appends-container"));
            browser.click(By.xpath("//bl-list-append-field[3]//bl-list-append-field-btn"));
            browser.waitForElementPresent(By.xpath("//bl-list-append-field[3]//bl-list-append-field-btn//span[text()='Added!' or text()='Remove']"));
        }
        return this;
    }

    public ListCreationPage setUltParentInst(boolean ultParentInst) {
        if (ultParentInst) {
            browser.scrollDownTo(By.xpath("//bl-list-appends-container"));
            browser.click(By.xpath("//bl-list-append-field[4]//bl-list-append-field-btn"));
            browser.waitForElementPresent(By.xpath("//bl-list-append-field[4]//bl-list-append-field-btn//span[text()='Added!' or text()='Remove']"));
        }
        return this;
    }

    public boolean setTargetetType(String targetetType) {
        browser.click(By.xpath("//span[@class='selector']//div[@data-e2e='sub-nav']"));
        browser.click(By.xpath("//span[contains(text(),'" + targetetType + "')]/.."));

        String countBefore = browser.read(By.cssSelector("span.lead-meter__count"));
        browser.waitForElementPresent(By.xpath("//span[contains(@class,'status') and contains(text(),'All changes saved')]"));
        browser.waitForElementPresent(By.xpath("//span[@class='selector']//div[@data-e2e='sub-nav']//span[text()='Institution Only-Targeted']"));
        String countAfter = browser.read(By.cssSelector("span.lead-meter__count"));
        return !countAfter.equals(countBefore);
    }

    public boolean setType(String type) {
        browser.click(By.xpath("//bl-fake-select[@class='selector']//div[@class='drop-down__target']"));
        browser.click(By.xpath("//span[contains(text(),'" + type + "')]/.."));
        return waitForSavingChanges();
    }

    public ListsPage clickFinishLater() {
        browser.click(By.xpath("//a[@class='btn mat-ripple' and contains(text(),'Finish Later')]"));
        return new ListsPage();
    }

    private boolean waitForSavingChanges() {
        String countBefore = browser.read(By.cssSelector("span.lead-meter__count"));
        browser.waitForElementPresent(By.xpath("//span[contains(@class,'status') and contains(text(),'All changes saved')]"));
        browser.waitForElementPresent(By.xpath("//span[@class='lead-meter__count' and not(text()='" + countBefore + "')]"), 5);
        String countAfter = browser.read(By.cssSelector("span.lead-meter__count"));
        return !countAfter.equals(countBefore);
    }


    public boolean isTargetingSelectDisplayed() {
        return browser.isPresent(By.xpath("//div[@data-e2e='targeting-type']"));
    }

    public boolean isSegmentBuilderOpened() {
        return browser.isPresent(By.cssSelector("div.segment-nav__container"));
    }

    public boolean isCityTagAppeared(String city) {
        return browser.isPresent(By.xpath("//span[@class='geo-tag__name' and contains(text(),'" + city + "')]"));
    }

    public boolean isAppendsOpened() {
        return browser.isPresent(By.xpath("//bl-list-append-field"));
    }

    public boolean isAppendAdded(int appendNumber) {
        return browser.isPresent(By.xpath("//bl-list-append-field[" + appendNumber + "]//bl-list-append-field-btn//span[text()='Added!' or text()='Remove']"));
    }

    public boolean isReviewAndActivateStage() {
        return browser.isPresent(By.xpath("//h3[contains(text(),'Review and Activate')]"));
    }

    public boolean isActivationPopUp() {
        return browser.isPresent(By.xpath("//button[@data-e2e='yes-activate-btn']"));
    }

    public boolean isActivationPopUpAbsent() {
        return browser.isAbsent(By.xpath("//button[@data-e2e='yes-activate-btn']"));
    }
}
