import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import po.*;
import utils.ApiHelper;
import utils.DbHelper;

public class CountTest extends AbstractTest {
    @Test(description = "Verify that count from UI, API and DB query are identical")
    public void countTest() {
        String searchQuery = "Alaska";

        LoginPage loginPage = new LoginPage().open();
        MainPage mainPage = loginPage.fillEmail("testing-dev-alex@yopmail.com").fillPassword("Alex2030").clickSignIn();
        SearchPage searchPage = new ViewsPage().clickSearch();
        SearchResultPage resultPage = searchPage.fillSearchForm(searchQuery).clickSearchButton();
        int districtsCountFromUi = resultPage.getCount("districts");
        int schoolsCountFromUi = resultPage.getCount("schools");
        int collegesCountFromUi = resultPage.getCount("colleges");

        int districtsCountFromApi = ApiHelper.getCount(searchQuery, "districts");
        int schoolsCountFromApi = ApiHelper.getCount(searchQuery, "schools");
        int collegesCountFromApi = ApiHelper.getCount(searchQuery, "colleges");

        int districtsCountFromDb = DbHelper.getDistrictsCount(searchQuery);
        int schoolsCountFromDb = DbHelper.getSchoolsCount(searchQuery);
        int collegesCountFromDb = DbHelper.getCollegesCount(searchQuery);

        Assert.assertTrue(districtsCountFromUi == districtsCountFromApi && districtsCountFromApi == districtsCountFromDb);
        Assert.assertTrue(schoolsCountFromUi == schoolsCountFromApi && schoolsCountFromApi == schoolsCountFromDb);
        Assert.assertTrue(collegesCountFromUi == collegesCountFromApi && collegesCountFromApi == collegesCountFromDb);
    }

//    @Test(dataProvider = "provideData")
//    public void test(int i1, int i2) {
//        System.out.println(i1);
//        System.out.println(i2);
//        Assert.assertEquals(i1 + 10, i2);
//    }
//
//        @DataProvider(name = "provideNumbers")
//        public Object[][] provideData() {
//            return new Object[][]{
//                    {10, 20},
//                    {100, 110},
//                    {200, 210}
//            };
//        }
}
