package steps.transaction;

import com.microsoft.playwright.Page;
import config.playwright.context.ActiveContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.transaction.TransactionPO;

import java.util.List;

public class transaction {
    Page page = ActiveContext.getActivePage();
    TransactionPO transaction = new TransactionPO(page);


    @When("user fill firstname {string}")
    public void user_fill_firstname(String name) {
        transaction.fillfirstname(name);

    }
    @When("user fill lastname {string}")
    public void user_fill_lastname(String lastname) {
        transaction.filllastname(lastname);
    }
    @When("user fill zipcode {string}")
    public void user_fill_zipcode(String zipcode) {
        transaction.zipcode(zipcode);
    }

    @Then("user successfully checkout and show {string}")
    public void userSuccessfullyCheckoutAndShow(String completeoder) {
        transaction.continuecheckout();
        transaction.overeviewfinish();
        Assert.assertEquals(completeoder, transaction.setCompleteOrdertext(),   completeoder + " is not exist");
    }

    @And("user checkout cart")
    public void userCheckoutCart() {
        transaction.checkoutbutton();
    }

    @And("user continue information")
    public void userContinueInformation() {
        transaction.continuecheckout();
    }

    @And("user remove item from cart")
    public void userRemoveItemFromCart(List<String> items) {
        for (String item : items){
            transaction.removecart(item);
        }
    }
}
