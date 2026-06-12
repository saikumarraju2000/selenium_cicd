package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;

/**
 * TestListener class - Implements ITestListener for test execution monitoring.
 * Captures screenshots on test failures and logs test execution details.
 */
public class TestListener implements ITestListener {

    /**
     * Called before a test method execution starts.
     *
     * @param result ITestResult object containing test method information
     */
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("================================");
        System.out.println("Test Started: " + result.getTestClass().getRealClass().getSimpleName() 
            + "." + result.getMethod().getMethodName());
        System.out.println("Test Description: " + result.getMethod().getDescription());
        System.out.println("================================");
    }

    /**
     * Called when a test method passes successfully.
     *
     * @param result ITestResult object containing test method information
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("\n✓ TEST PASSED: " + result.getMethod().getMethodName());
        System.out.println("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        System.out.println("================================\n");
    }

    /**
     * Called when a test method fails.
     * Captures a screenshot for failure analysis.
     *
     * @param result ITestResult object containing test method information
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("\n✗ TEST FAILED: " + result.getMethod().getMethodName());
        System.out.println("Failure Reason: " + result.getThrowable().getMessage());
        System.out.println("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        
        // Capture screenshot on failure
        try {
            // Try to get the WebDriver instance from the test class
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                BaseTest baseTest = (BaseTest) testClass;
                WebDriver driver = baseTest.getDriver();
                
                if (driver != null) {
                    String screenshotPath = ScreenshotUtils.takeScreenshot(
                        driver, 
                        result.getMethod().getMethodName() + "_FAILED"
                    );
                    System.out.println("Screenshot captured at: " + screenshotPath);
                } else {
                    System.out.println("WebDriver is null, cannot capture screenshot");
                }
            }
        } catch (Exception e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("================================\n");
    }

    /**
     * Called when a test method is skipped.
     *
     * @param result ITestResult object containing test method information
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("\n⊘ TEST SKIPPED: " + result.getMethod().getMethodName());
        System.out.println("Skip Reason: " + result.getThrowable());
        System.out.println("================================\n");
    }

    /**
     * Called when a test method fails but is within success percentage.
     *
     * @param result ITestResult object containing test method information
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("\n⚠ TEST FAILED BUT WITHIN SUCCESS PERCENTAGE: " + result.getMethod().getMethodName());
        System.out.println("================================\n");
    }

    /**
     * Called before a test suite execution starts.
     *
     * @param context ITestContext containing test suite information
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          TEST SUITE EXECUTION STARTED                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Suite Name: " + context.getSuite().getName());
        System.out.println("║ Test Name: " + context.getName());
        System.out.println("║ Total Tests: " + context.getAllTestMethods().length);
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Called after a test suite execution completes.
     *
     * @param context ITestContext containing test suite information
     */
    @Override
    public void onFinish(ITestContext context) {
        int totalTests = context.getAllTestMethods().length;
        int passedTests = context.getPassedTests().size();
        int failedTests = context.getFailedTests().size();
        int skippedTests = context.getSkippedTests().size();
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          TEST SUITE EXECUTION COMPLETED                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Suite Name: " + context.getSuite().getName());
        System.out.println("║ Test Name: " + context.getName());
        System.out.println("║ ────────────────────────────────────────────────────────── ║");
        System.out.println("║ Total Tests: " + totalTests);
        System.out.println("║ Passed: " + passedTests + " ✓");
        System.out.println("║ Failed: " + failedTests + " ✗");
        System.out.println("║ Skipped: " + skippedTests + " ⊘");
        System.out.println("║ ────────────────────────────────────────────────────────── ║");
        System.out.println("║ Success Rate: " + String.format("%.2f%%", 
            (passedTests * 100.0 / totalTests)));
        System.out.println("║ Duration: " + (context.getEndDate().getTime() - context.getStartDate().getTime()) 
            + " ms");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

}
