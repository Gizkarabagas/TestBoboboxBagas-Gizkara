package runners;

import com.microsoft.playwright.Playwright;
import com.trivago.cluecumber.core.CluecumberCore;
import com.trivago.cluecumber.engine.exceptions.CluecumberException;
import com.trivago.cluecumber.engine.logging.CluecumberLogger;
import config.global.FlowControl;
import config.global.GlobalConfig;
import config.playwright.PlaywrightSourceManager;
import config.playwright.browser.BrowserInitialize;
import config.playwright.browser.BrowserOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

public class BaseTestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() throws CluecumberException {
        System.out.println("Starting Playwright");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        BrowserInitialize browserInitialize = new BrowserInitialize();

        PlaywrightSourceManager.setLocalPlaywright(Playwright.create());
        PlaywrightSourceManager.setLocalBrowser(browserInitialize.getBrowser(GlobalConfig.BROWSER_NAME, BrowserOptions.launchOptions()));
        FlowControl.setStrictFlow(true);
    }

    @Parameters({"bagas"})
    @AfterTest(alwaysRun = true)
    public void afterTest(String bagas) throws CluecumberException {
        String jsonDirectory = "target/result/" + bagas;
        String reportDirectory = "target/result/" + bagas + "/cluecumber_report";
        PlaywrightSourceManager.getLocalBrowser().close();
        PlaywrightSourceManager.getLocalPlaywright().close();
        new CluecumberCore.Builder()
            .setCustomCssFile("src/test/resources/cluecumber-style/cluecumberStyle.css")
            .setLogLevel(CluecumberLogger.CluecumberLogLevel.MINIMAL)
            .setCustomPageTitle(bagas + "Report")
            .setExpandAttachments(true)
            .build().generateReports(jsonDirectory, reportDirectory);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Playwright Run Is Finished");
    }
}
