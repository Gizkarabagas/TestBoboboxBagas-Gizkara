package steps.hompage;

import com.microsoft.playwright.Page;
import config.playwright.context.ActiveContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.homepage.HomepagePO;


import java.util.List;

public class homepage {
    Page page = ActiveContext.getActivePage();
    HomepagePO hompage = new HomepagePO(page);

    @When("user select items to cart")
    public void user_select_items_to_cart(List<String> items) {
        for (String item : items) {
            hompage.addcart(item);
        }
    }

    @And("user move to cart")
    public void userMoveToCart() {
        hompage.cart();
    }

    @And("user click item {string}")
    public void userClickItem(String items) {
        hompage.detailitem(items);
    }

    @Then("user successfully verify detail item {string}")
    public void userSuccessfullyVerifyDetailItem(String items) {
        Assert.assertEquals(items, hompage.verifydetailitem(items), items + "not exist");
    }

    @Then("user verify on cart have {string} items")
    public void userVerifyOnCartHaveItems(String items) {
        Assert.assertEquals(items, hompage.verifycartitems(items), items + "not exist");
    }
}
