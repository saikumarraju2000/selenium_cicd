package tests;

import pages.GooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Example test using the Page Object Model.
 * This is the recommended pattern — add more page classes as your app grows.
 */
public class GooglePageObjectTest extends BaseTest {

    @Test
    public void testSearchWithPageObject() {
        GooglePage google = new GooglePage(driver);

        google.open();
        google.search("TestNG tutorial");

        // Assert.assertTrue(google.getTitle().contains("TestNG tutorial"), "Page title mismatch");
        // Assert.assertTrue(google.hasResults(), "No results found");
    }
}
