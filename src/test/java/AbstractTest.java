import browser.Browser;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import utils.Context;

@Listeners({ReportPortalTestNGListener.class})
public class AbstractTest {
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Browser.getInstance().takeScreenshot();
        }
    }

    @AfterClass
    public void killBrowser() {
        Browser.kill();
        Context.clear();
    }
}
