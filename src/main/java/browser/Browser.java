package browser;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";
    private WebDriver driver;
    private static Browser instance = null;

    private Browser(WebDriver driver) {
        this.driver = driver;
    }

    public static Browser getInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static Browser init() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new Browser(driver);
    }

    private void setImplWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void openUrl(String url) {
        Logger.debug("Going to URL: " + url);
        driver.get(url);
    }

    public void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }


    public void waitForElementPresent(By locator, int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementDisappear(By locator) {
        setImplWait(0);
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        setImplWait(COMMAND_DEFAULT_TIMEOUT_SECONDS);
    }

    private void highlightElement(By locator) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", driver.findElement(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 3px solid green;');", driver.findElement(locator));
    }

    private void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    public void click(final By locator) {
        waitForElementVisible(locator);
        Logger.debug("Clicking element '" + driver.findElement(locator).getText().split("\\R", 2)[0] + "' (Located: " + locator + ")");
        highlightElement(locator);
        unHighlightElement(locator);
        driver.findElement(locator).click();
    }

    public void jsClick(final By locator) {
        waitForElementVisible(locator);
        Logger.debug("Clicking element '" + driver.findElement(locator).getText().split("\\R", 2)[0] + "' (Located: " + locator + ")");
        highlightElement(locator);
        unHighlightElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public void clickThrough(final By locator) {
        waitForElementVisible(locator);
        Logger.debug("Clicking element '" + driver.findElement(locator).getText().split("\\R", 2)[0] + "' (Located: " + locator + ")");
        forceMoveElementForward(locator);
        driver.findElement(locator).click();
    }

    public void type(final By locator, String text) {
        waitForElementVisible(locator);
        highlightElement(locator);
        Logger.debug("Typing text '" + text + "' to input field (Located: " + locator + ")");
        driver.findElement(locator).sendKeys(text);
        unHighlightElement(locator);
    }

    public String read(final By locator) {
        waitForElementVisible(locator);
        highlightElement(locator);
        String text = driver.findElement(locator).getText();
        Logger.debug("Reading text: " + text);
        unHighlightElement(locator);
        return text;
    }

    public String readAttribute(final By locator, String attr) {
        waitForElementVisible(locator);
        highlightElement(locator);
        String value = driver.findElement(locator).getAttribute(attr);
        Logger.debug("Reading text: " + value);
        unHighlightElement(locator);
        return value;
    }

    public void scrollUpTo(final By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(locator));
    }

    public void scrollDownTo(final By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
    }

    public void dragAndDrop(final By locator, final By targetLocator) {
        waitForElementVisible(locator);
        waitForElementVisible(targetLocator);
        WebElement element = driver.findElement(locator);
        WebElement target = driver.findElement(targetLocator);
        Logger.debug("Dragging element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")" +
                "to '" + driver.findElement(targetLocator).getText() + "' (Located: " + targetLocator + ")");
        (new Actions(driver)).dragAndDrop(element, target).perform();
    }

    public void selectItems(By firstLocator, By lastLocator) {
        new Actions(driver).clickAndHold(driver.findElement(firstLocator)).moveToElement(driver.findElement(lastLocator)).release().build().perform();
    }

    public void resize(By sizeHandleLocator, int xOffset, int yOffset) {
        WebElement handle = driver.findElement(sizeHandleLocator);
        new Actions(driver).clickAndHold(handle).moveByOffset(xOffset, yOffset).release(handle).build().perform();
    }

    public By selectSeveralElements(List<By> locators) {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL);
        WebElement element;
        for (By locator : locators) {
            waitForElementVisible(locator);
            highlightElement(locator);
            Logger.debug("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
            element = driver.findElement(locator);
            action.moveToElement(element).click();
        }
        action.keyUp(Keys.CONTROL).perform();
        return locators.get(0);
    }

    public boolean isPresent(By locator) {
        boolean succeed = driver.findElements(locator).size() > 0;
        if (succeed) {
            Logger.debug("Element " + driver.findElement(locator).getText().split("\\R", 2)[0] + " is present.");
            highlightElement(locator);
            unHighlightElement(locator);
        } else Logger.debug("Element located " + locator + " not found.");
        return succeed;
    }

    public boolean isAbsent(By locator) {
        setImplWait(0);
        boolean succeed = driver.findElements(locator).size() == 0;
        if (succeed) {
            Logger.debug("Element located " + locator + " is absent");
        } else Logger.debug("Element located " + locator + " is present");
        setImplWait(COMMAND_DEFAULT_TIMEOUT_SECONDS);
        return succeed;
    }

    public void renameByKeyboard(String newName) {
        new Actions(driver)
                .keyDown(Keys.LEFT_CONTROL)
                .sendKeys("a")
                .keyUp(Keys.LEFT_CONTROL)
                .sendKeys(newName)
                .sendKeys(Keys.ENTER)
                .build().perform();
    }

    public void forceMoveElementBehind(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'z-index: 0')", driver.findElement(locator));
    }

    public void forceMoveElementForward(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'z-index: 1000')", driver.findElement(locator));
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            String scrPath = screenshotName + ".jpg";
            File copy = new File(scrPath);
            FileUtils.copyFile(screenshot, copy);
            reporting.Logger.debug("Saved screenshot: " + screenshotName);
            Logger.attach(scrPath, "Screenshot");
        } catch (IOException e) {
            Logger.error("Failed to make screenshot");
        }
    }


    public static void kill() {
        if (instance != null) {
            try {
                instance.driver.quit();
            } finally {
                instance = null;
            }
        }
    }

    public void switchToFrame() {
        driver.switchTo().frame(0);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void scrollUpToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}