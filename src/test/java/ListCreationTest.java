import bo.ListCreationParameters;
import browser.Browser;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import po.ListCreationPage;
import po.ListsPage;
import po.LoginPage;
import po.MainPage;
import utils.Context;
import utils.YamlLoader;

@Listeners({ReportPortalTestNGListener.class})
public class ListCreationTest {
    @Test(description = "List creation", dataProvider = "dataProvider")
    public void listCreationTest(ListCreationParameters listCreationParameters) {
        LoginPage loginPage = new LoginPage().open();
        MainPage mainPage = loginPage.fillEmail("testing-dev-alex@yopmail.com").fillPassword("Alex2030").clickSignIn();
        ListCreationPage listCreationPage = new MainPage().openListsTab().openListCreator();
        listCreationPage
                .clickListTypeCard(listCreationParameters.getType())
                .fillNameInput(Context.getListName())
                .clickInstTypes()
                .setInstTypes(listCreationParameters.getInstitutionTypes())
                .setGeography(listCreationParameters.getGeography())
                .setPersonnel(listCreationParameters.getPersonnel())
                .setNames(listCreationParameters.getNames())
                .clickReviewAndActivate()
                .setParentPid(listCreationParameters.isParentPid())
                .setParentInst(listCreationParameters.isParentInst())
                .setUltParentPid(listCreationParameters.isUltParentPid())
                .setUltParentInst(listCreationParameters.isUltParentInst())
                .clickReviewOrder()
                .clickActivate()
                .clickYesActivate();
        String status = new ListsPage()
                .waitUntilActiveStatus(Context.getListName())
                .getListStatus(Context.getListName());
        Assert.assertTrue(status.contains("Active"));
    }

    @DataProvider
    public static Object[][] dataProvider() {
        return YamlLoader.loadLcpArrayFromDir("src/test/resources/listCreationConfigs");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Browser.getInstance().takeScreenshot();
        }
        Browser.kill();
        Context.clear();
    }
}
