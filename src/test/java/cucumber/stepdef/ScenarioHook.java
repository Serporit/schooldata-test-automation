package cucumber.stepdef;

import browser.Browser;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import utils.Context;
import utils.CountStorage;

public class ScenarioHook {
    @After
    public void afterScenario(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            Browser.getInstance().takeScreenshot();
        }
        Browser.kill();
        CountStorage.getInstance().clear();
        Context.clear();
    }
}
