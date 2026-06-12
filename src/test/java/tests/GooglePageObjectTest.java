package tests;

import base.BaseTest;
import pages.GooglePage;
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
    }
}
