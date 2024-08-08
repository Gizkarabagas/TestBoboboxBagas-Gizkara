package steps;

import com.microsoft.playwright.Tracing;
import config.global.FlowControl;
import config.global.GlobalConfig;
import config.playwright.context.ActiveContext;
import config.playwright.context.BrowserContext;
import config.playwright.context.BrowserContextInitializer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import testdata.ScenarioInformations;

import java.nio.file.Paths;
import java.util.Collection;

public class Hooks {
    private Collection<String> tags = null;

    /**
     * Will invoke before every scenario
     * @param scenario method
     */
    @Before
    public void setup(Scenario scenario) {
        tags = scenario.getSourceTagNames();

        if (FlowControl.isStrictFlow() && !FlowControl.isContinueFlow()) {
            if (ActiveContext.getActiveBrowserContext() == null || ActiveContext.getActiveBrowserContext().pages().isEmpty()) {
                BrowserContextInitializer.initializeBrowserContextOne();
                BrowserContextInitializer.initializeBrowserContextOnePage();
            }
        }

        System.out.println("\n" + scenario.getName() + " is started");
        ScenarioInformations.setScenarioName(scenario.getName());
        ScenarioInformations.setScenarioTags(scenario.getSourceTagNames());
    }

    /**
     * Will invoke after every scenario
     *
     * @param scenario method
     */
    @After
    public void cleanUp(Scenario scenario) {
        FlowControl.setContinueFlow(tags.contains("@continue"));
        FlowControl.setStrictFlow(true);
        if (scenario.isFailed()) {
            System.out.println(scenario.getName() + " is failed");
            scenario.attach(ActiveContext.getActivePage().screenshot(), "image/png", scenario.getName());
            if (GlobalConfig.SET_TRACING) {
                ActiveContext.getActiveBrowserContext().tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/trace/" + scenario.getName().replace(" ", "-").toLowerCase() + "-trace.zip")));
            }
            System.out.println("Failed URL is: " + ActiveContext.getActivePage().url());
        }
        System.out.println(scenario.getName() + " is finished");
    }
}
