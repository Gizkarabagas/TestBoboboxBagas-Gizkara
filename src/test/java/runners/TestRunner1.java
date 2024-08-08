package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"json:target/result/automation/cucumber-report.json", "html:target/result/automation/cucumber-report.html"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@Test1"
)
public class TestRunner1 extends BaseTestRunner {
}
