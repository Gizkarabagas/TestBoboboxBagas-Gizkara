package steps.login;

import com.microsoft.playwright.Page;
import config.playwright.context.ActiveContext;
import data.automation.Data;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.login.LoginPO;
import utilities.PlaywrightHelpers;

public class LoginSteps {
    Page page = ActiveContext.getActivePage();
    PlaywrightHelpers playwright = new PlaywrightHelpers(page);
    LoginPO login = new LoginPO(page);

    @Given("user visit saucedemo")
    public void user_visit_saucedemo() {
        playwright.navigateTo(Data.URL);
    }

    @When("user login with credential")
    public void user_login_with_credential(DataTable dataTable) {
        var credential  = dataTable.asMaps(String.class, String.class);
        var username = credential.get(0).get("username");
        var password = credential.get(0).get("password");
        login.fillusername(username);
        login.fillpassword(password);
        login.clickbuttonlogin();
    }

    @Then("user will redirect to homepage and see text {string}")
    public void user_will_redirect_to_homepage_and_see_text(String text) {
        Assert.assertTrue(playwright.isTextDisplayed(text));
    }

    @Then("user verify {string}")
    public void userVerify(String verify) {
        Assert.assertEquals(verify, login.verifyerrormessage(), verify +" is not exist");

    }
}
