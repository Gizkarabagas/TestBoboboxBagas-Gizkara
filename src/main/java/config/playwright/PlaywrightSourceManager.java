package config.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import lombok.Getter;
import lombok.Setter;

public class PlaywrightSourceManager {
    @Getter @Setter
    private static Browser localBrowser;
    @Getter @Setter
    private static Playwright localPlaywright;
}
