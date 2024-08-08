package config.playwright.context;

import com.microsoft.playwright.Tracing;
import config.global.GlobalConfig;
import config.playwright.PlaywrightSourceManager;
import config.playwright.browser.BrowserOptions;
import java.util.List;

public class BrowserContextInitializer {
    private static List<String> grantPermissions = List.of("geolocation");

    /**
     * Initialize browser context number one
     */

    public static void initializeBrowserContextOne() {
        BrowserContext.setBrowserContextOne(PlaywrightSourceManager.getLocalBrowser().newContext(BrowserOptions.browserContextOptions()));
    }

    /**
     * Initialize browser context number one page
     */
    public static void initializeBrowserContextOnePage() {
        com.microsoft.playwright.BrowserContext browserContext1 = BrowserContext.getBrowserContextOne();
        BrowserContext.getBrowserContextOne().tracing().start(new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true)
            .setSources(false));

        BrowserContext.getBrowserContextOne().grantPermissions(grantPermissions);

        browserContext1.grantPermissions(grantPermissions);
        browserContext1.setDefaultNavigationTimeout(GlobalConfig.DEFAULT_NAVIGATION_TIMEOUT);
        browserContext1.setDefaultTimeout(GlobalConfig.DEFAULT_TIMEOUT);

        BrowserContext.setContextOneActivePage(BrowserContext.getBrowserContextOne().newPage());
        ActiveContext.setActiveBrowserContext(BrowserContext.getBrowserContextOne());
        ActiveContext.setActivePage(BrowserContext.getContextOneActivePage());
    }

    /**
     * Initialize browser context number two
     */
    public static void initializeBrowserContextTwo() {
        BrowserContext.setBrowserContextTwo(PlaywrightSourceManager.getLocalBrowser().newContext(BrowserOptions.browserContextOptions()));
    }

    /**
     * Initialize browser context number two page
     */
    public static void initializeBrowserContextTwoPage() {
        com.microsoft.playwright.BrowserContext browserContext2 = BrowserContext.getBrowserContextTwo();
        BrowserContext.getBrowserContextTwo().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(false));

        BrowserContext.getBrowserContextTwo().grantPermissions(grantPermissions);

        browserContext2.grantPermissions(grantPermissions);
        browserContext2.setDefaultNavigationTimeout(GlobalConfig.DEFAULT_NAVIGATION_TIMEOUT);
        browserContext2.setDefaultTimeout(GlobalConfig.DEFAULT_TIMEOUT);

        BrowserContext.setContextTwoActivePage(BrowserContext.getBrowserContextTwo().newPage());
        ActiveContext.setActiveBrowserContext(BrowserContext.getBrowserContextTwo());
        ActiveContext.setActivePage(BrowserContext.getContextTwoActivePage());
    }
}
