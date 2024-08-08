package pageobject.transaction;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utilities.PlaywrightHelpers;

public class TransactionPO {

    private Page page;
    PlaywrightHelpers playwright;

    Locator checkoutButton;
    Locator firstnamePlaceholder;
    Locator lastnamePlaceholder;
    Locator zipCodePlaceholder;
    Locator continueButton;
    Locator finishButton;
    Locator completeOrdertext;

    public TransactionPO(Page page) {
        this.page = page;
        playwright = new PlaywrightHelpers(page);

        checkoutButton = page.locator("[data-test='checkout']");
        firstnamePlaceholder = page.locator("[data-test='firstName']");
        lastnamePlaceholder = page.locator("[data-test='lastName']");
        zipCodePlaceholder = page.locator("[data-test='postalCode']");
        continueButton =  page.locator("[data-test='continue']");
        finishButton = page.locator("[data-test='finish']");
        completeOrdertext = page.locator("[data-test='complete-header']");

    }

    /**
     * User CLick Checkout Button In Description Cart
     */
    public void checkoutbutton (){
        playwright.clickOn(checkoutButton);
    }

    /**
     * User Fill FirstName In Your confirmation page
     */
    public void fillfirstname(String name){
        playwright.fill(firstnamePlaceholder, name);
    }

    /**
     * User Fill LastName In Your confirmation page
     */
    public void filllastname(String lastname){
        playwright.fill(lastnamePlaceholder, lastname);
    }

    /**
     * User Fill ZIP/Postalcode In Your confirmation page
     */
    public void zipcode(String postalcode){
        playwright.fill(zipCodePlaceholder, postalcode);
    }

    /**
     * User Click Continue In Your confirmation page
     */
    public void continuecheckout(){
        playwright.clickOn(continueButton);
    }

    /**
     * User Click Finish In Overview page
     */
    public void overeviewfinish(){
        playwright.clickOn(finishButton);
    }

    /**
     * Verify Complite Order Success
     */
    public String setCompleteOrdertext (){

        return playwright.getText(completeOrdertext);
    }

    /**
     * Remove Card Items
     */
    public void removecart(String item){
        item = item.toLowerCase().replaceAll(" ", "-");
        var locator = String.format("[data-test='remove-%s']", item);
        playwright.clickOn(page.locator(locator));
    }
}
