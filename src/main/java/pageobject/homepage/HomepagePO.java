package pageobject.homepage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utilities.PlaywrightHelpers;

public class HomepagePO {
    private Page page;
    PlaywrightHelpers playwright;

    Locator shoppingCart;
    Locator addCart;

    public HomepagePO(Page page) {
        this.page = page;
        this.playwright = new PlaywrightHelpers(page);

        shoppingCart =  page.locator("[data-test='shopping-cart-link']");
        addCart = page.locator("[data-test='shopping-cart-link']");
    }

    /**
     * User Add Multiple Item To Cart
     */
    public void addcart(String item){
        item = item.toLowerCase().replaceAll(" ", "-");
        var addItems = String.format("[data-test='add-to-cart-%s']", item);
        playwright.clickOn(page.locator(addItems));
    }

    /**
     * User Click On Cart
     */
    public void cart(){
        playwright.clickOn(addCart);
    }

    /**
     * User CLick Item In Homepage
     */
    public void detailitem(String item){
        playwright.clickOn(page.locator("//div[.='"+item+"']"));
    }

    /**
     * User Verify In Detail Item
     */
    public String verifydetailitem(String item){
        return playwright.getText(page.locator("//div[.='"+item+"']"));
    }

    /**
     * User Verify In cart Item
     */
    public String verifycartitems(String item){
        return playwright.getText(page.locator("//a[.='"+item+"']"));
    }
}
