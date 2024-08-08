package utilities;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class PlaywrightHelpers {
    Page page;

    public PlaywrightHelpers(Page page) {
        this.page = page;
    }

    /**
     * Reload page
     */
    public void reloadPage() {
        page.reload();
    }

    /**
     * this method will be reload the active page if element is not visible after loading
     * @param times how many you would to reload
     * @param locator the expected locator that you want to visible
     */
    public void reloadPageIfLocatorNotVisibleAfterLoad(int times, Locator locator) {
        if(this.isLocatorVisibleAfterLoad(locator, 1000.0)){
            for (int i = 0; i < times; i++) {
                reloadPage();
                if (waitTillLocatorIsVisible(locator)) break;
            }
        }
    }

    /**
     * Reloads the current page if a specific element is not visible.
     * This method checks if the provided locator element is visible within a short timeout (e.g., 1 second) after the page loads.
     * If not, it attempts to reload the page up to the specified number of times (`times`) until the element becomes visible.
     *
     * @param times The maximum number of times to attempt reloading the page.
     * @param locator The expected locator element to check for visibility.
     */
    public void reloadPageIfElementNotVisible(int times, Locator locator) {
        for (int i = 0; i < times; i++) {
            reloadPage();
            if (waitTillLocatorIsVisible(locator)) break;
        }
    }

    /**
     * Reload page with timeout
     * @param timeout
     */
    public void reloadPage(Double timeout) {
        page.reload(new Page.ReloadOptions().setTimeout(timeout));
    }

    /**
     * This method navigates to a specified URL
     * default timeout
     */
    public void navigateTo(String url) {
        page.navigate(url);
    }

    /**
     * This method navigates to a specified URL with a specified timeout for the navigation to complete.
     *
     * @param url     String data type of URL format
     * @param timeout Double data type of specific timeout
     */
    public void navigateTo(String url, Double timeout) {
        page.navigate(url, new Page.NavigateOptions().setTimeout(timeout));
    }

    /**
     * This overloaded version of the navigateTo method waits for a specific load state before navigating to the URL.
     *
     * @param url   String data type of URL format
     * @param state The load state to wait for before navigating.
     */
    public void navigateTo(String url, Double timeout, LoadState state) {
        page.waitForLoadState(state);
        page.navigate(url, new Page.NavigateOptions().setTimeout(timeout));
    }

    /**
     * This overloaded version of the navigateTo method navigates to a URL and waits for a specific locator.
     *
     * @param url     String data type of URL format
     * @param locator The locator to wait for.
     */
    public void navigateToAndWaitLocator(String url, Locator locator) {
        page.navigate(url);
        locator.waitFor();
    }

    /**
     * This overloaded version of the navigateTo method navigates to a URL and waits for a specific locator.
     * @param url     String data type of URL format
     * @param locator The locator to wait for.
     * @param timeout Double data type of specific timeout
     */
    public void navigateToAndWaitLocator(String url, Locator locator, Double timeout) {
        page.navigate(url);
        locator.waitFor(new Locator.WaitForOptions().setTimeout(timeout));
    }

    /**
     * Back to previous page
     *
     *
     */
    public void backToPreviousPage() {
        page.goBack();
    }

    //----- Action Part ----\\

    /**
     * Click on a desired locator
     *
     * @param locator target locator
     */
    public void clickOn(Locator locator) {
        locator.click();
    }

    /**
     * click on locator and typing inside of placeholder like real keyboard
     *
     * @param element
     * @param type
     */
    public void clickLocatorAndTypeKeyboard(Locator element, String type) {
        clickOn(element);
        realKeyboardType(type);
    }

    /**
     * Force click on desired locator
     * Use this method for locator that have disabled set to true
     *
     * @param locator target locator
     */
    public void forceClickOn(Locator locator) {
        locator.click(new Locator.ClickOptions().setForce(true));
    }

    /**
     * Delay and then force click to a locator
     *
     * @param locator   Locator data type
     * @param delayTime Delay time in millisecond Double data type
     */
    public void delayAndForceClickOn(Locator locator, Double delayTime) {
        locator.click(new Locator.ClickOptions().setForce(true).setDelay(delayTime));
    }

    /**
     * Delay and then click to a locator
     *
     * @param locator   Locator data type
     * @param delayTime Delay time in millisecond Double data type
     */
    public void delayAndClickOn(Locator locator, Double delayTime) {
        locator.click(new Locator.ClickOptions().setDelay(delayTime));
    }

    /**
     * Double-click on a desired locator
     *
     * @param locator target locator
     */
    public void doubleClick(Locator locator) {
        locator.dblclick();
    }

    /**
     * Double-click on a desired locator
     *
     * @param locator   target locator
     * @param delayTime Delay time in millisecond Double data type
     */
    public void doubleClickAndDelay(Locator locator, Double delayTime) {
        locator.dblclick(new Locator.DblclickOptions().setDelay(delayTime));
    }

    /**
     * Wait till locator is visible then click on desired locator
     *
     * @param locator target locator
     */
    public void waitForLocatorVisibleAndClickOn(Locator locator) {
        waitTillLocatorIsVisible(locator);
        locator.click();
    }

    /**
     * click locator if some locator exist
     * this method is suitable for dynamic element
     * example usage src/main/java/pageobject/owner/fiturpromosi/BroadcastChatPO.java on method clickOnTambahBroadcastChatButton()
     * @param locatorCLick target click locator
     */
    public void clickIfElementVisible(Locator locatorCLick) {
        if (waitTillLocatorIsVisible(locatorCLick)) {
            clickOn(locatorCLick);
        } else {
            logElementNotClickable(locatorCLick);
        }
    }

    /**
     * click locator if some locator exist after load
     * this method is suitable for dynamic pop up
     * @param locatorCLick target click locator
     * @param timeout timeout
     */
    public void clickIfElementVisibleAfterLoad(Locator locatorCLick, double timeout) {
        if (isLocatorVisibleAfterLoad(locatorCLick, timeout)) {
            clickOn(locatorCLick);
        } else {
            logElementNotClickable(locatorCLick);
        }
    }

    /**
     * click locator if some locator exist after load
     * this method is suitable for dynamic pop up
     * @param locatorExist seen locator
     * @param locatorCLick target click locator
     * @param timeout timeout time waiting
     */
    public void clickIfElementVisibleAfterLoad(Locator locatorExist, Locator locatorCLick , double timeout) {
        if (isLocatorVisibleAfterLoad(locatorExist, timeout)) {
            clickOn(locatorCLick);
        } else {
            logElementNotClickable(locatorCLick);
        }
    }

    /**
     * Scroll to coordinate
     * @param deltaX
     * @param deltaY
     */
    public void scrollTo(double deltaX, double deltaY) {
        page.mouse().wheel(deltaX, deltaX);
    }

    /**
     * Simulate tap keyboard
     * @param key
     */
    public void tapKeyboard(String key) {
        page.keyboard().down(key);
    }

    /**
     * Force fill input to locator with disabled fill attribute
     * @param locator targeted locator
     * @param data String data type
     */
    public void forceFill(Locator locator, String data) {
        locator.fill(data, new Locator.FillOptions().setForce(true));
    }

    /**
     * Fill input to locator
     * @param locator targeted locator
     * @param data String data type
     */
    public void fill(Locator locator, String data) {
        locator.fill(data);
    }

    /**
     * Select dropdown by value
     *
     * @param locator Locator type
     * @param value   String data type
     */
    public void selectDropdownByValue(Locator locator, String value) {
        locator.selectOption(value);
    }

    /**
     * Select dropdown by value
     *
     * @param locator Locator type
     * @param object  SelectOption object type
     */
    public void selectDropdownBySelectOption(Locator locator, SelectOption object) {
        locator.selectOption(object);
    }


    //----- Get Part ----\\

    /**
     * Get text content of a locator
     * @param locator target locator
     * @return String data type
     */
    public String getText(Locator locator) {
        return locator.textContent().trim();
    }

    /**
     * check box
     */
    public void checkBox(Locator locator) {
        locator.check();
    }

    /**
     * uncheck box
     */
    public void uncheckBox(Locator locator) {
        locator.uncheck();
    }


    //---- Wait Part ----\\

    /**
     * Wait until element locator is visible
     *
     * @param locator Locator type
     * @return boolean
     */
    public boolean waitTillLocatorIsVisible(Locator locator) {
        return locator.isVisible();
    }

    /**
     * Wait until element locator is visible
     *
     * @param locator Locator type
     * @param timeout Double type
     * @return boolean
     */
    public boolean waitTillLocatorIsVisible(Locator locator, Double timeout) {
        return locator.isVisible(new Locator.IsVisibleOptions().setTimeout(timeout));
    }

    /**
     * Wait for load state before and check visibility of target locator
     * @param locator
     * @param timeout
     * @return
     */
    public boolean isLocatorVisibleAfterLoad(Locator locator, Double timeout) {
        page.waitForLoadState(LoadState.LOAD);
        page.waitForTimeout(timeout);
        return locator.isVisible();
    }

    /**
     * Wait for a locator
     * @param locator  Locator data type
     */
    public void waitFor(Locator locator) {
        locator.waitFor();
    }

    /**
     * Wait for a locator with timeout
     * @param locator  Locator data type
     * @param duration set duration in double
     */
    public void waitFor(Locator locator, Double duration) {
        locator.waitFor(new Locator.WaitForOptions().setTimeout(duration));
    }

    /**
     * Wait till page loaded
     */
    public void waitTillPageLoaded() {
        page.waitForLoadState(LoadState.LOAD);
    }

    /**
     * Wait till page loaded
     * @param timeout double data type
     */
    public void waitTillPageLoaded(Double timeout) {
        page.waitForLoadState(LoadState.LOAD, new Page.WaitForLoadStateOptions().setTimeout(timeout));
    }


    //---- Locator Part ----\\

    /**
     * Get locator set by role and name
     *
     * @param role    AriaRole e.g AriaRole.BUTTON
     * @param setName name of the button
     * @return Locator data type
     */
    public Locator locatorByRoleSetName(AriaRole role, String setName) {
        return page.getByRole(role, new Page.GetByRoleOptions().setName(setName));
    }


    /**
     * Clear text
     *
     * @param locator target locator
     */
    public void clearText(Locator locator){
        locator.clear();
    }


    //---- Scroll Part ----\\

    /**
     * Scroll Helper to the bottom page
     */
    public void pageScrollHeightToBottom() {
        page.evaluate("window.scrollBy(0,document.body.scrollHeight)");
    }

    /**
     * Scroll Helper vertical (per pixel)
     */
    public void pageScrollToDown(int y) {
        page.evaluate("window.scrollBy(0," + y + ")");
    }

    /**
     * Scroll Down Helper until locator is visible (per 200 pixel)
     */
    public void pageScrollUntilElementIsVisible(Locator locator) {
        for (int i = 0; i < 200; i++) {
            page.evaluate("window.scrollBy(0,200)");
            if (waitTillLocatorIsVisible(locator)) break;
        }
    }

    /**
     * Scroll down to locator
     * @param locator
     */
    public void pageScrollInView(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    /**
     * Move Page helper, it will return page object, so it can use to implement in next page taget
     * for example when select kost on promo side from home page, it will be move to kost detail
     * so this helper can be implement to return kost detail object on kostdetailPO with argument this page inside of the kost detail object
     * real example you can see DOM 4 on feature:kost detail and Scenario: [Dweb][Kost Detail] Check promo owner section login
     */
    public Page movePageByClickLocator(Page pageActive, Locator locatorTarget) {
        // move page
        Page nextPage = pageActive.waitForPopup(new Page.WaitForPopupOptions().setTimeout(3000.0), locatorTarget::click);
        nextPage.bringToFront();
        return nextPage;
    }

    /**
     * Hard wait before an action
     *
     * @param time Double data type
     */
    public void hardWait(double time) {
        page.waitForTimeout(time);
    }

    /**
     * Get Active URL page
     *
     * @return String URL Active page
     */
    public String getActivePageURL() {
        String activeUrl = page.evaluate("window.location.href").toString();
        return activeUrl;
    }

    /**
     * Get page URL
     * @return string data type
     */
    public String getPageUrl() {
        return page.url();
    }

    /**
     * Get Active Title page
     *
     * @return String Title Active page
     */
    public String getActivePageTitle() {
        String activeTitle = page.evaluate("document.title").toString();
        return activeTitle;
    }

    /**
     * Click on a desired locator based on text
     *
     * @param words target locator
     *              default timeout
     */
    public void clickOnText(String words) {
        clickOn(page.getByText(words).first());
    }

    /**
     * Click on a desired locator based on text
     *
     * @param words   locator target locator
     * @param timeout Double data type of specific timeout
     */
    public void clickOnText(String words, Double timeout) {
        delayAndClickOn(page.getByText(words), timeout);
    }

    /**
     * Click on a desired locator based on button text
     *
     * @param buttonText target locator
     *                   default timeout
     */
    public void clickOnTextButton(String buttonText) {
        clickOn(locatorByRoleSetName(AriaRole.BUTTON, buttonText));
    }

    /**
     * Click on a desired locator based on button text
     *
     * @param buttonText target locator
     * @param duration   Double data type of specific timeout
     */
    public void clickOnTextButton(String buttonText, double duration) {
        clickOn(locatorByRoleSetName(AriaRole.BUTTON, buttonText));
        hardWait(duration);
    }

    /**
     * Check element based on text is displayed
     *
     * @return status true / false
     */
    public boolean isTextDisplayed(String text) {
        return waitTillLocatorIsVisible(page.getByText(text));
    }

    /**
     * Wait until element locator is visible
     *
     * @param text     Locator type based on text
     * @param duration Double type
     * @return boolean
     */
    public boolean isTextDisplayed(String text, double duration) {
        return isLocatorVisibleAfterLoad(page.getByText(text).first(), duration);
    }

    /**
     * Wait until element locator button based on text is visible
     *
     * @param button Locator type based on text
     *               default timeout
     * @return boolean
     */
    public boolean isButtonWithTextDisplayed(String button) {
        return waitTillLocatorIsVisible(locatorByRoleSetName(AriaRole.BUTTON, button));
    }

    /**
     * Helper to type like real keyboard
     * note: this method need some action such as click locator
     *
     * @param text string
     */
    public void realKeyboardType(String text) {
        page.keyboard().type(text);
    }

    /**
     * press real keyboard, for ex 'Enter'
     *
     * @param keyboardKey
     */
    public void pressKeyboardKey(String keyboardKey) {
        page.keyboard().press(keyboardKey);
    }

    /**
     * Wait until element locator button based on text is visible
     *
     * @param button   Locator type based on text
     * @param duration Double type
     * @return boolean
     */
    public boolean isButtonWithTextDisplayed(String button, double duration) {
        return isLocatorVisibleAfterLoad(locatorByRoleSetName(AriaRole.BUTTON, button), duration);
    }

    public boolean isButtonDisable(Locator locator) {
        return locator.isDisabled();
    }

    public boolean isButtonEnable(Locator locator) {
        return locator.isEnabled();
    }

    //---- Assert Part ----\\

    /**
     * Playwright Assert locator is visible
     *
     * @param locator Locator type
     */
    public void assertVisible(Locator locator) {
        assertThat(locator).isVisible();
    }

    /**
     * Playwright Assert locator is disabled
     *
     * @param locator Locator type
     */
    public void assertDisable(Locator locator) {
        assertThat(locator).isDisabled();
    }

    /**
     * Click on a desired locator based on button text
     *
     * @param linkText target locator
     *                   default timeout
     */
    public void clickOnLinkButton(String linkText) {
        clickOn(locatorByRoleSetName(AriaRole.LINK, linkText).first());
    }

    /**
     * Verify radio button is check
     * @param locator
     */
    public boolean isRadioButtonChecked(Locator locator){
        return locator.isChecked();
    }


    //---- Assert Part ----\\

    // private method part

    /**
     * logging into console if element is clicked or not
     * @param locator clickable
     */
    private void logElementNotClickable(Locator locator) {
        log.info("locator is not clicked or visible {}", locator);
    }

    /**
     *  This method will be used to scroll to up
     */
    public void scrollToUp(Page page) {
        page.evaluate("async () => { " +
                "while (document.documentElement.scrollTop > 0) { " +
                "  document.documentElement.scrollTop -= 100; " +
                "  await new Promise(resolve => requestAnimationFrame(resolve)); " +
                "} " +
                "document.documentElement.scrollTop = 0; " +
                "}");
    }
}
