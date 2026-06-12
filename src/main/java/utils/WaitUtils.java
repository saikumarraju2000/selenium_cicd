package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitUtils class provides utility methods for explicit waits.
 */
public class WaitUtils {

    private static final long DEFAULT_WAIT_TIME = 10;  // seconds

    /**
     * Waits for an element to be visible on the page.
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return WebElement once it is visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be visible on the page with custom timeout.
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @param timeOutInSeconds Custom timeout in seconds
     * @return WebElement once it is visible
     */
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable on the page.
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return WebElement once it is clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be clickable on the page with custom timeout.
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @param timeOutInSeconds Custom timeout in seconds
     * @return WebElement once it is clickable
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be present in the DOM.
     *
     * @param driver WebDriver instance
     * @param locator By locator of the element
     * @return WebElement once it is present
     */
    public static WebElement waitForElementToBePresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for page to load completely.
     *
     * @param driver WebDriver instance
     */
    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        wait.until(driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete"));
    }
}
