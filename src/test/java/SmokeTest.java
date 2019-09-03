import org.testng.Assert;
import org.testng.annotations.Test;
import po.ListCreationPage;
import po.ListsPage;
import po.LoginPage;
import po.MainPage;
import utils.Context;

public class SmokeTest extends AbstractTest {
    @Test(groups = "smoke")
    public void loginTest() {
        LoginPage loginPage = new LoginPage().open();
        MainPage mainPage = loginPage.fillEmail("testing-dev-alex@yopmail.com").fillPassword("Alex2030").clickSignIn();
        Assert.assertTrue(mainPage.isBuyButtonPresent(), "Log in failed");
    }

    @Test(groups = "smoke", dependsOnMethods = "loginTest")
    public void listCreating() {
        ListCreationPage listCreationPage = new MainPage().openListsTab().openListCreator().clickListTypeCard("Email");
        Assert.assertTrue(listCreationPage.isTargetingSelectDisplayed());
    }

    @Test(groups = "smoke", dependsOnMethods = "listCreating")
    public void selectTargeting() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.fillNameInput(Context.getListName()).clickGeography();
        Assert.assertTrue(listCreationPage.isSegmentBuilderOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "selectTargeting")
    public void selectGeography() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setGeographyAndCheckChanges("Santaquin");
        Assert.assertTrue(listCreationPage.isCityTagAppeared("Santaquin"));
        Assert.assertTrue(countIsChanged);
    }

    @Test(groups = "smoke", dependsOnMethods = "selectGeography")
    public void selectCityOnly() {
        boolean countIsChanged = new ListCreationPage().setCityOnly();
        Assert.assertTrue(countIsChanged);
    }

    @Test(groups = "smoke", dependsOnMethods = "selectCityOnly")
    public void reviewActivate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickReviewAndActivate();
        Assert.assertTrue(listCreationPage.isAppendsOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "reviewActivate")
    public void appendData() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.setParentPid(true);
        Assert.assertTrue(listCreationPage.isAppendAdded(1));
        listCreationPage.setParentInst(true);
        Assert.assertTrue(listCreationPage.isAppendAdded(2));
    }

    @Test(groups = "smoke", dependsOnMethods = "appendData")
    public void reviewOrder() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickReviewOrder();
        Assert.assertTrue(listCreationPage.isReviewAndActivateStage());
    }

    @Test(groups = "smoke", dependsOnMethods = "reviewOrder")
    public void activate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        listCreationPage.clickActivate();
        Assert.assertTrue(listCreationPage.isActivationPopUp());
    }

    @Test(groups = "smoke", dependsOnMethods = "activate")
    public void confirmActivate() {
        ListCreationPage listCreationPage = new ListCreationPage();
        ListsPage listsPage = listCreationPage.clickYesActivate();
        Assert.assertTrue(listCreationPage.isActivationPopUpAbsent());
        Assert.assertEquals(listsPage.getListStatus(Context.getListName()), "Processing");
    }

    @Test(groups = "smoke", dependsOnMethods = "confirmActivate")
    public void duplicateProcessingList() {
        ListsPage listsPage = new ListsPage();
        listsPage.duplicateList(Context.getListName());
        Assert.assertTrue(listsPage.getListStatus(Context.getListName() + " duplicate").contains("Saved For Later"));
    }

    @Test(groups = "smoke", dependsOnMethods = "duplicateProcessingList")
    public void openDuplicatedList() {
        ListCreationPage listCreationPage = new ListsPage().openList(Context.getListName() + " duplicate");
        Assert.assertTrue(listCreationPage.isSegmentBuilderOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "openDuplicatedList")
    public void changeListType() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setType("Direct Mail");
        Assert.assertTrue(countIsChanged);
    }

    @Test(groups = "smoke", dependsOnMethods = "changeListType")
    public void changeTargetingType() {
        ListCreationPage listCreationPage = new ListCreationPage();
        boolean countIsChanged = listCreationPage.setTargetetType("Institution Only");
        Assert.assertTrue(countIsChanged);
    }

    @Test(groups = "smoke", dependsOnMethods = "changeTargetingType")
    public void saveForLater() {
        ListsPage listsPage = new ListCreationPage().clickFinishLater();
        Assert.assertTrue(listsPage.isDashBoardOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "saveForLater")
    public void startDownload() {
        ListsPage listsPage = new ListsPage();
        listsPage.waitUntilActiveStatus(Context.getListName());
        listsPage.downloadList(Context.getListName());
        Assert.assertTrue(listsPage.isDownloadFormOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "startDownload")
    public void downloadList() {
        ListsPage listsPage = new ListsPage();
        listsPage.chooseFormat("txt").clickDownload();
        Assert.assertTrue(listsPage.isConfirmDownloadOpened());
    }

    @Test(groups = "smoke", dependsOnMethods = "downloadList")
    public void checkDownload() {
        ListsPage listsPage = new ListsPage();
        listsPage.clickYesIGotIt();
        // TODO check a file
        Assert.assertTrue(listsPage.isConfirmDownloadAbsent());
    }

    @Test(groups = "smoke", dependsOnMethods = "checkDownload")
    public void duplicateActiveList() {
        ListsPage listsPage = new ListsPage();
        listsPage.duplicateList(Context.getListName());
        Assert.assertTrue(listsPage.getListStatus(Context.getListName() + " duplicate").contains("Saved For Later"));
    }

    @Test(groups = "smoke", dependsOnMethods = "duplicateActiveList")
    public void renameList() {
        ListsPage listsPage = new ListsPage();
        listsPage.renameList(Context.getListName() + " duplicate", Context.getListName() + " RENAMED");
        Assert.assertTrue(listsPage.isListPresent(Context.getListName() + " RENAMED"));
    }

    @Test(groups = "smoke", dependsOnMethods = "renameList")
    public void deleteRenamedList() {
        ListsPage listsPage = new ListsPage();
        listsPage.deleteList(Context.getListName() + " RENAMED");
        Assert.assertTrue(listsPage.isListAbsent(Context.getListName() + " RENAMED"));
    }
}
