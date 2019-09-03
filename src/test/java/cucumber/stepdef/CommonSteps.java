package cucumber.stepdef;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import po.LoginPage;
import po.SearchPage;
import po.ViewsPage;

public class CommonSteps {
    @Given("^I log in as \"([^\"]*)\" : \"([^\"]*)\"$")
    public void iLogInAs(String login, String password) {
        LoginPage loginPage = new LoginPage().open();
        loginPage.fillEmail(login).fillPassword(password).clickSignIn();
    }

    @Given("^I log in to schoolData$")
    public void iLogInToSchoolData() {
        iLogInAs("testing-dev-alex@yopmail.com", "Alex2030");
    }

    @And("^I search \"([^\"]*)\" on UI$")
    public void iSearch(String searchQuery) throws Throwable {
        SearchPage searchPage = new ViewsPage().clickSearch();
        searchPage.fillSearchForm(searchQuery).clickSearchButton();
    }

}
