package pageobject.login;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utilities.PlaywrightHelpers;


public class LoginPO {
    private Page page;
    PlaywrightHelpers playwright;

    Locator userNamePlaceholder;
    Locator passwordPlacehoder;
    Locator loginButton;
    Locator errorMessageLogin;

    public LoginPO(Page page) {
        this.page = page;
        playwright = new PlaywrightHelpers(page);

        userNamePlaceholder = page.locator("[data-test='username']");
        passwordPlacehoder = page.locator("[data-test='password']");
        loginButton = page.locator("[data-test='login-button']");
        errorMessageLogin = page.locator("[data-test='error']");
    }

    /**
     * Fill username
     */
    public void fillusername(String text){
        playwright.fill(userNamePlaceholder, text);
    }

    /**
     * Fill password
     */
    public void fillpassword(String text){
        playwright.fill(passwordPlacehoder, text);
    }

    /**
     * Click login button
     */
    public void clickbuttonlogin(){
        playwright.clickOn(loginButton);
    }

    /**
     * verify error message login
     */
    public String verifyerrormessage(){
        return playwright.getText(errorMessageLogin);
    }

}
