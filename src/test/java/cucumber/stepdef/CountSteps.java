package cucumber.stepdef;

import bo.InstitutionsCount;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import po.SearchPage;
import po.SearchResultPage;
import po.ViewsPage;
import utils.ApiHelper;
import utils.CountStorage;
import utils.DbHelper;

public class CountSteps {
    @When("^I search (.+) via (UI) and count institutions$")
    public void iSearchQueryViaUiAndCountInstitutions(String searchQuery, String source) {
        SearchPage searchPage = new ViewsPage().clickSearch();
        SearchResultPage resultPage = searchPage.fillSearchForm(searchQuery).clickSearchButton();
        InstitutionsCount uiCount = new InstitutionsCount(
                resultPage.getDisctrictsCount(),
                resultPage.getSchoolsCount(),
                resultPage.getCollegesCount()
        );
        CountStorage.getInstance().put(source, uiCount);
    }

    @When("^I search (.+) via (API) and count institutions$")
    public void iSearchQueryViaApiAndCountInstitutions(String searchQuery, String source) {
        InstitutionsCount apiCount = new InstitutionsCount(
                ApiHelper.getCount(searchQuery, "districts"),
                ApiHelper.getCount(searchQuery, "schools"),
                ApiHelper.getCount(searchQuery, "colleges")
        );
        CountStorage.getInstance().put(source, apiCount);
    }

    @When("^I search (.+) via (DB) and count institutions$")
    public void iSearchQueryViaDbAndCountInstitutions(String searchQuery, String source) {
        InstitutionsCount dbCount = new InstitutionsCount(
                DbHelper.getDistrictsCount(searchQuery),
                DbHelper.getSchoolsCount(searchQuery),
                DbHelper.getCollegesCount(searchQuery)
        );
        CountStorage.getInstance().put(source, dbCount);
    }

    @Then("^Institutions count from (UI), (API) and (DB) are the same$")
    public void institutionsCountFromUIAPIAndDBAreTheSame(String ui, String api, String db) {
        CountStorage storage = CountStorage.getInstance();
        InstitutionsCount uiCount = storage.get(ui);
        InstitutionsCount apiCount = storage.get(api);
        InstitutionsCount dbCount = storage.get(db);
        Assert.assertTrue(uiCount.equals(apiCount) && apiCount.equals(dbCount));
    }
}
