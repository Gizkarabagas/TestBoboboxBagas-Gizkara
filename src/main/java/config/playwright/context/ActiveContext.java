package config.playwright.context;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.Setter;

public class ActiveContext {
    @Setter @Getter
    private static BrowserContext activeBrowserContext;
    @Setter @Getter
    private static Page activePage;
}
