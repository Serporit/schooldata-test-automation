import org.testng.Assert;
import org.testng.annotations.Test;
import po.ListCreationPage;
import po.ListsPage;
import po.LoginPage;
import po.MainPage;
import utils.Context;

public class SmokeTest extends AbstractTest {
    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage().open();
        MainPage mainPage = loginPage.fillEmail("testing-dev-alex@yopmail.com").fillPassword("Alex2030").clickSignIn();
        Assert.assertTrue(mainPage.isBuyButtonPresent(), "Log in failed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void listCreating() {
        ListCreationPage listCreationPage = new MainPage().openListsTab().openListCreator().clickListTypeCard("Email");
        Assert.assertTrue(listCreationPage.isTargetingSelectDisplayed());
    }

    @Test(dependsOnMethods = "listCreating")
    public void selectTargeting() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.fillNameInput(Context.getListName()).clickGeography();
        Assert.assertTrue(listCreationPage.isSegmentBuilderOpened());
    }

    @Test(dependsOnMethods = "selectTargeting")
    public void selectGeography() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setGeographyAndCheckChanges("Santaquin");
        Assert.assertTrue(listCreationPage.isCityTagAppeared("Santaquin"));
        Assert.assertTrue(countIsChanged);
    }

    @Test(dependsOnMethods = "selectGeography")
    public void selectCityOnly() {
        boolean countIsChanged = new ListCreationPage().setCityOnly();
        Assert.assertTrue(countIsChanged);
    }

    @Test(dependsOnMethods = "selectCityOnly")
    public void reviewActivate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickReviewAndActivate();
        Assert.assertTrue(listCreationPage.isAppendsOpened());
    }

    @Test(dependsOnMethods = "reviewActivate")
    public void appendData() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.setParentPid(true);
        Assert.assertTrue(listCreationPage.isAppendAdded(1));
        listCreationPage.setParentInst(true);
        Assert.assertTrue(listCreationPage.isAppendAdded(2));
    }

    @Test(dependsOnMethods = "appendData")
    public void reviewOrder() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickReviewOrder();
        Assert.assertTrue(listCreationPage.isReviewAndActivateStage());
    }

    @Test(dependsOnMethods = "reviewOrder")
    public void activate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickActivate();
        Assert.assertTrue(listCreationPage.isActivationPopUp());
    }

    @Test(dependsOnMethods = "activate")
    public void confirmActivate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        ListsPage listsPage = listCreationPage.clickYesActivate();
        Assert.assertTrue(listCreationPage.isActivationPopUpAbsent());
        Assert.assertEquals(listsPage.getListStatus(Context.getListName()), "Processing");
    }

    @Test(dependsOnMethods = "confirmActivate")
    public void duplicateProcessingList() {
        ListsPage listsPage = new ListsPage();
        listsPage.duplicateList(Context.getListName());
        Assert.assertTrue(listsPage.getListStatus(Context.getListName() + " duplicate").contains("Saved For Later"));
    }

    @Test(dependsOnMethods = "duplicateProcessingList")
    public void openDuplicatedList() {
        ListCreationPage listCreationPage = new ListsPage().openList(Context.getListName() + " duplicate");
        Assert.assertTrue(listCreationPage.isSegmentBuilderOpened());
    }

    @Test(dependsOnMethods = "openDuplicatedList")
    public void changeListType() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setType("Direct Mail");
        Assert.assertTrue(countIsChanged);
    }

    @Test(dependsOnMethods = "changeListType")
    public void changeTargetingType() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setTargetetType("Institution Only");
        Assert.assertTrue(countIsChanged);
    }

    @Test(dependsOnMethods = "changeTargetingType")
    public void saveForLater() {
        ListsPage listsPage = new ListCreationPage().clickFinishLater();
        Assert.assertTrue(listsPage.isDashBoardOpened());
    }

    @Test(dependsOnMethods = "saveForLater")
    public void startDownload() {
        ListsPage listsPage = new ListsPage();
        listsPage.waitUntilActiveStatus(Context.getListName());
        listsPage.downloadList(Context.getListName());
        Assert.assertTrue(listsPage.isDownloadFormOpened());
    }

    @Test(dependsOnMethods = "startDownload")
    public void downloadList() {
        ListsPage listsPage = new ListsPage();
        listsPage.chooseFormat("txt").clickDownload();
        Assert.assertTrue(listsPage.isConfirmDownloadOpened());
    }

    @Test(dependsOnMethods = "downloadList")
    public void checkDownload() {
        ListsPage listsPage = new ListsPage();
        listsPage.clickYesIGotIt();
        // TODO check a file
        Assert.assertTrue(listsPage.isConfirmDownloadAbsent());
    }

    @Test(dependsOnMethods = "checkDownload")
    public void duplicateActiveList() {
        ListsPage listsPage = new ListsPage();
        listsPage.duplicateList(Context.getListName());
        Assert.assertTrue(listsPage.getListStatus(Context.getListName() + " duplicate").contains("Saved For Later"));
    }

    @Test(dependsOnMethods = "duplicateActiveList")
    public void renameList() {
        ListsPage listsPage = new ListsPage();
        listsPage.renameList(Context.getListName() + " duplicate", Context.getListName() + " RENAMED");
        Assert.assertTrue(listsPage.isListPresent(Context.getListName() + " RENAMED"));
    }

    @Test(dependsOnMethods = "renameList")
    public void deleteRenamedList() {
        ListsPage listsPage = new ListsPage();
        listsPage.deleteList(Context.getListName() + " RENAMED");
        Assert.assertTrue(listsPage.isListAbsent(Context.getListName() + " RENAMED"));
    }
}
