package config.playwright.context;

import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.Setter;

public class BrowserContext {
    @Setter @Getter
    private static com.microsoft.playwright.BrowserContext browserContextOne;
    @Setter @Getter
    private static Page contextOneActivePage;
}
