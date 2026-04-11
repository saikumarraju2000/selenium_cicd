package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void testPageTitle() {
        driver.get("https://www.google.com");
        Assert.assertTrue(driver.getTitle().contains("Google"), "Title should contain 'Google'");
    }

    @Test
    public void testSearchReturnsResults() {
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.sendKeys(Keys.ENTER);

        // Wait for results page with explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement results = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
        Assert.assertNotNull(results, "Search results should be present");
        Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"), "Results title mismatch");
    }
}
