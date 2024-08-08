package config.playwright.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import config.global.GlobalConfig;

import java.nio.file.Paths;
public class BrowserOptions {
    public static BrowserType.LaunchOptions launchOptions() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = GlobalConfig.HEADLESS;
        launchOptions.devtools = GlobalConfig.DEV_TOOLS;
        launchOptions.slowMo = GlobalConfig.SLOW_MO;
        return launchOptions;
    }

    public static Browser.NewContextOptions browserContextOptions() {
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.setAcceptDownloads(GlobalConfig.SET_ACCEPT_DOWNLOAD);
        contextOptions.setScreenSize(GlobalConfig.SCREEN_WIDTH, GlobalConfig.SCREEN_HEIGHT);
        contextOptions.setViewportSize(GlobalConfig.SCREEN_WIDTH, GlobalConfig.SCREEN_HEIGHT);
        if (GlobalConfig.SET_VIDEO_RECORD)
            contextOptions.setRecordVideoDir(Paths.get("target/videos/"));
        return contextOptions;
    }
}
