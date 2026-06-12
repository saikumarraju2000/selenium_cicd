package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils class provides utility methods for taking screenshots.
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    /**
     * Takes a screenshot of the current page.
     *
     * @param driver WebDriver instance
     * @param screenshotName Name of the screenshot file
     * @return Path of the screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Generate unique filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = screenshotName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            // Take screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Save screenshot to file
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("✓ Screenshot saved: " + filePath);
            return filePath;

        } catch (Exception e) {
            System.err.println("✗ Error taking screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Takes a screenshot with current timestamp as name.
     *
     * @param driver WebDriver instance
     * @return Path of the screenshot file
     */
    public static String takeScreenshot(WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return takeScreenshot(driver, "screenshot_" + timestamp);
    }
}
