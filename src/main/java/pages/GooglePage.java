package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * GooglePage class represents the Google search page.
 * Uses Page Object Model pattern.
 */
public class GooglePage extends BasePage {

    // Locators
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    private By resultsLocator = By.id("search");

    /**
     * Constructor to initialize GooglePage.
     *
     * @param driver WebDriver instance
     */
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Google homepage.
     */
    public void open() {
        driver.get("https://www.google.com");
        System.out.println("Navigated to Google homepage");
    }

    /**
     * Searches for a query on Google.
     *
     * @param searchQuery Query to search
     */
    public void search(String searchQuery) {
        System.out.println("Searching for: " + searchQuery);
        searchBox.clear();
        searchBox.sendKeys(searchQuery);
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("Search submitted");
    }

    /**
     * Checks if search results are displayed.
     *
     * @return true if results are present, false otherwise
     */
    public boolean hasResults() {
        try {
            return driver.findElement(resultsLocator).isDisplayed();
        } catch (Exception e) {
            System.out.println("Results not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets the search result count from the results page.
     *
     * @return Count of results (approximate)
     */
    public String getResultsCount() {
        try {
            WebElement resultsStats = driver.findElement(By.id("result-stats"));
            return resultsStats.getText();
        } catch (Exception e) {
            System.out.println("Could not get results count: " + e.getMessage());
            return "0";
        }
    }
}
