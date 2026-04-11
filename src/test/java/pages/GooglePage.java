package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model (POM) for Google Search page.
 * Encapsulates locators and actions — keeps tests clean.
 */
public class GooglePage {

    private final WebDriver driver;

    // Locators
    private final By searchBox = By.name("q");
    private final By searchResults = By.id("search");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.google.com");
    }

    public void search(String query) {
        WebElement box = driver.findElement(searchBox);
        box.clear();
        box.sendKeys(query);
        box.sendKeys(Keys.ENTER);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean hasResults() {
        return !driver.findElements(searchResults).isEmpty();
    }
}
