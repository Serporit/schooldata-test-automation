import bo.ListCreationParameters;
import org.testng.Assert;
import org.testng.annotations.Test;
import po.ListCreationPage;
import po.ListsPage;
import po.LoginPage;
import po.MainPage;
import utils.Context;

public class ListCreationTest extends AbstractTest {
    @Test(description = "List creation", groups = "creation")
    public void listCreationTest() {
        ListCreationParameters listCreationParameters = ListCreationParameters.builder()
                .withType("All")
                .withGeography("Alaska")
                .withPersonnel("Journalism")
                .withUltParentPID()
                .build();


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

//    @DataProvider(name = "data-provider")
//    public Object[][] dataProvider() {
//        return new Object[][]{
//                //get objects from file(s)
//        };
//    }
}
