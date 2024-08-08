package utilities;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.options.Cookie;

import java.util.List;

public class PlaywrightBrowserContextHelpers {
    BrowserContext context;

    public PlaywrightBrowserContextHelpers(BrowserContext context) {
        this.context = context;
    }

    /**
     * Get all cookies from the current context
     * @return List of Cookies
     */
    public List<Cookie> getCookies() {
        return context.cookies();
    }

    /**
     * Get all cookies from the current context
     * @param url String
     */
    public void getCookies(String url) {
        context.cookies(url);
    }

    /**
     * Add cookies to the current context
     * @param cookies List of Cookies
     */
    public void addCookies(List<Cookie> cookies) {
        context.addCookies(cookies);
    }

    /**
     * Clear all cookies from the current context
     */
    public void clearCookies() {
        context.clearCookies();
    }
}
