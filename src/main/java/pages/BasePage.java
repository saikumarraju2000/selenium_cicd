package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage class for all page objects.
 * Contains common methods and WebDriver initialization.
 */
public class BasePage {

    protected WebDriver driver;

    /**
     * Constructor to initialize WebDriver and PageFactory.
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the page title.
     *
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Gets the current URL.
     *
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigates to a specific URL.
     *
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Navigates back to the previous page.
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * Navigates forward to the next page.
     */
    public void goForward() {
        driver.navigate().forward();
    }
}
