package utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LocatorHelpers {
    private Page page;
    public Locator span;
    public Locator div;
    public AriaRole roleButton;
    public AriaRole roleImg;
    public AriaRole roleComplementary;
    public AriaRole roleOption;
    public LocatorHelpers(Page page) {
        this.page = page;
        this.span = page.locator("span");
        this.div = page.locator("div");
        this.roleButton = AriaRole.BUTTON;
        this.roleImg = AriaRole.IMG;
        this.roleComplementary = AriaRole.COMPLEMENTARY;
        this.roleOption = AriaRole.OPTION;
    }
}
