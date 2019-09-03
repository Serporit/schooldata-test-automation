package cucumber.stepdef;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import po.ListCreationPage;
import po.ListsPage;
import po.MainPage;
import utils.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCreationSteps {
    @When("^I create new List with parameters:$")
    public void iCreateNewListWithParameters(DataTable table) {
        List<String> paramNames = table.topCells();
        List<String> paramValues = table.cells(1).get(0);
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < paramNames.size(); i++) {
            params.put(paramNames.get(i), paramValues.get(i));
        }
        String listType = params.get("Type");
        String instType = params.get("Institution Types");
        String geography = params.get("Geography");
        String personnel = params.get("Personnel");
        String names = params.get("Names");
        String parentPid = params.get("ParentPID");
        String parentInst = params.get("ParentInst");
        String ultParentPid = params.get("UltParentPID");
        String ultParentInst = params.get("UltParentInst");
        ListCreationPage listCreationPage = new MainPage().openListsTab().openListCreator();
        listCreationPage
                .clickListTypeCard(listType)
                .fillNameInput(Context.getListName())
                .clickInstTypes()
                .setInstTypes(instType)
                .setGeography(geography)
                .setPersonnel(personnel)
                .setNames(names)
                .clickReviewAndActivate()
                .setParentPid(parentPid.equals("+"))
                .setParentInst(parentInst.equals("+"))
                .setUltParentPid(ultParentPid.equals("+"))
                .setUltParentInst(ultParentInst.equals("+"))
                .clickReviewOrder();
    }

    @And("^I activate the List$")
    public void iActivateTheList() {
        new ListCreationPage().clickActivate().clickYesActivate();
    }

    @Then("^List status is (Active)$")
    public void listStatusIs(String active) throws Throwable {
        String status = new ListsPage().waitUntilActiveStatus(Context.getListName()).getListStatus(Context.getListName()).trim();
        Assert.assertTrue(status.contains(active));
    }
}
