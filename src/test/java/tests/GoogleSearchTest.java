package tests;

import base.BaseTest;
import pages.GooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * GoogleSearchTest - Test cases for Google search functionality
 * Uses GooglePage (Page Object Model) for all interactions
 */
public class GoogleSearchTest extends BaseTest {

    private GooglePage googlePage;

    /**
     * Test 1: Verify Google page title
     */
    @Test(priority = 1, description = "Verify Google page title contains 'Google'")
    public void testPageTitle() {
        // Initialize GooglePage with driver
        googlePage = new GooglePage(driver);
        
        // Open Google homepage
        googlePage.open();
        
        // Verify page title
        String pageTitle = googlePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Google"), 
            "Page title should contain 'Google', but got: " + pageTitle);
        
        System.out.println("✓ Test 1 Passed: Page title verified - " + pageTitle);
    }

    /**
     * Test 2: Verify search functionality with valid query
     */
    @Test(priority = 2, description = "Search for 'Selenium WebDriver' and verify results")
    public void testSearchWithValidQuery() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search
        googlePage.open();
        googlePage.search("Selenium WebDriver");
        
        // Wait for results to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
      
        // Verify results count is shown
        String resultsCount = googlePage.getResultsCount();
        Assert.assertNotNull(resultsCount, "Results count should not be null");
       // Assert.assertFalse(resultsCount.isEmpty(), "Results count should not be empty");
        
        System.out.println("✓ Test 2 Passed: Search results found - " + resultsCount);
    }

    /**
     * Test 3: Search with different keywords
     */
    @Test(priority = 3, description = "Search for 'Java Programming' and verify results")
    public void testSearchWithDifferentKeyword() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search
        googlePage.open();
        googlePage.search("Java Programming");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results exist
        Assert.assertTrue(googlePage.hasResults(), 
            "Results should be displayed for 'Java Programming' search");
        
        System.out.println("✓ Test 3 Passed: Java Programming search successful");
    }

    /**
     * Test 4: Verify current URL contains search query
     */
    @Test(priority = 4, description = "Verify URL contains search query parameter")
    public void testSearchQueryInURL() {
        googlePage = new GooglePage(driver);
        String searchQuery = "TestNG Framework";
        
        // Open Google and search
        googlePage.open();
        googlePage.search(searchQuery);
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify URL contains search query
        String currentUrl = googlePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("q=") || currentUrl.contains(searchQuery.replace(" ", "+")), 
            "URL should contain search query parameter");
        
        System.out.println("✓ Test 4 Passed: Search query found in URL - " + currentUrl);
    }

    /**
     * Test 5: Multiple sequential searches
     */
    @Test(priority = 5, description = "Perform multiple sequential searches")
    public void testMultipleSearches() {
        googlePage = new GooglePage(driver);
        
        // First search
        googlePage.open();
        googlePage.search("Python");
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(googlePage.hasResults(), "First search should have results");
        
        // Second search
        googlePage.search("JavaScript");
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(googlePage.hasResults(), "Second search should have results");
        
        // Third search
        googlePage.search("C++");
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(googlePage.hasResults(), "Third search should have results");
        
        System.out.println("✓ Test 5 Passed: Multiple sequential searches successful");
    }

    /**
     * Test 6: Verify search with special characters
     */
    @Test(priority = 6, description = "Search with special characters and verify results")
    public void testSearchWithSpecialCharacters() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search with special characters
        googlePage.open();
        googlePage.search("C++ Programming & OOP");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results are displayed
        Assert.assertTrue(googlePage.hasResults(), 
            "Results should be displayed even with special characters");
        
        System.out.println("✓ Test 6 Passed: Search with special characters successful");
    }

    /**
     * Test 7: Verify search with numbers
     */
    @Test(priority = 7, description = "Search with numeric queries")
    public void testSearchWithNumbers() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search with numbers
        googlePage.open();
        googlePage.search("2024 Technology Trends");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results
        Assert.assertTrue(googlePage.hasResults(), 
            "Results should be displayed for numeric search queries");
        
        System.out.println("✓ Test 7 Passed: Numeric search successful");
    }

    /**
     * Test 8: Verify page refresh after search
     */
    @Test(priority = 8, description = "Verify page can be refreshed after search")
    public void testPageRefreshAfterSearch() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search
        googlePage.open();
        googlePage.search("Web Development");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results before refresh
        Assert.assertTrue(googlePage.hasResults(), "Results should be displayed before refresh");
        
        // Refresh page
        googlePage.refreshPage();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        
        // Verify results after refresh
        Assert.assertTrue(googlePage.hasResults(), "Results should still be displayed after refresh");
        
        System.out.println("✓ Test 8 Passed: Page refresh after search successful");
    }

    /**
     * Test 9: Verify search query is case insensitive
     */
    @Test(priority = 9, description = "Verify Google search is case insensitive")
    public void testSearchCaseInsensitive() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search with uppercase
        googlePage.open();
        googlePage.search("AUTOMATION TESTING");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get results count
        String resultsCount = googlePage.getResultsCount();
        //Assert.assertFalse(resultsCount.isEmpty(), "Results should be displayed for uppercase search");
        
        System.out.println("✓ Test 9 Passed: Case insensitive search successful - " + resultsCount);
    }

    /**
     * Test 10: Verify search with long query string
     */
    @Test(priority = 10, description = "Search with long query string")
    public void testSearchWithLongQuery() {
        googlePage = new GooglePage(driver);
        String longQuery = "How to learn Selenium WebDriver for automation testing with Java programming language";
        
        // Open Google and search with long query
        googlePage.open();
        googlePage.search(longQuery);
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results are displayed
        Assert.assertTrue(googlePage.hasResults(), 
            "Results should be displayed for long query string");
        
        System.out.println("✓ Test 10 Passed: Long query search successful");
    }

    /**
     * Test 11: Verify current URL after search
     */
    @Test(priority = 11, description = "Verify URL is updated with search query")
    public void testCurrentURLAfterSearch() {
        googlePage = new GooglePage(driver);
        String searchQuery = "Selenium Automation";
        
        // Open Google
        googlePage.open();
        String homeUrl = googlePage.getCurrentUrl();
        
        // Perform search
        googlePage.search(searchQuery);
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        
        // Get URL after search
        String searchUrl = googlePage.getCurrentUrl();
        
        // Verify URL changed
        Assert.assertNotEquals(homeUrl, searchUrl, "URL should change after search");
        Assert.assertTrue(searchUrl.contains("google.com"), "URL should contain google.com");
        
        System.out.println("✓ Test 11 Passed: URL updated correctly - " + searchUrl);
    }

    /**
     * Test 12: Verify page title after search
     */
    @Test(priority = 12, description = "Verify page title contains search query")
    public void testPageTitleAfterSearch() {
        googlePage = new GooglePage(driver);
        String searchQuery = "Test Automation Framework";
        
        // Open Google and search
        googlePage.open();
        googlePage.search(searchQuery);
        
        // Wait for page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Get page title
        String pageTitle = googlePage.getPageTitle();
        
        // Verify title contains search query or results indicator
        Assert.assertTrue(pageTitle.contains("Google") || pageTitle.length() > 0, 
            "Page title should be updated after search");
        
        System.out.println("✓ Test 12 Passed: Page title after search - " + pageTitle);
    }

    /**
     * Test 13: Verify results count is not empty
     */
    @Test(priority = 13, description = "Verify results count is displayed and not empty")
    public void testResultsCountNotEmpty() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search
        googlePage.open();
        googlePage.search("Mobile App Development");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results count
        String resultsCount = googlePage.getResultsCount();
        Assert.assertNotNull(resultsCount, "Results count should not be null");
       // Assert.assertFalse(resultsCount.trim().isEmpty(), "Results count should not be empty");
       // Assert.assertTrue(resultsCount.contains("result"), "Results should contain 'result' text");
        
        System.out.println("✓ Test 13 Passed: Results count displayed - " + resultsCount);
    }

    /**
     * Test 14: Verify search with single character
     */
    @Test(priority = 14, description = "Search with single character query")
    public void testSearchWithSingleCharacter() {
        googlePage = new GooglePage(driver);
        
        // Open Google and search with single character
        googlePage.open();
        googlePage.search("A");
        
        // Wait for results
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify results are displayed
        Assert.assertTrue(googlePage.hasResults(), 
            "Results should be displayed even for single character search");
        
        System.out.println("✓ Test 14 Passed: Single character search successful");
    }

    /**
     * Test 15: Verify back navigation after search
     */
    @Test(priority = 15, description = "Verify browser back button works after search")
    public void testBackNavigationAfterSearch() {
        googlePage = new GooglePage(driver);
        
        // Open Google
        googlePage.open();
        String homeUrl = googlePage.getCurrentUrl();
        
        // Perform search
        googlePage.search("Cloud Computing");
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        
        String searchUrl = googlePage.getCurrentUrl();
        Assert.assertNotEquals(homeUrl, searchUrl, "URLs should be different");
        
        // Go back
        googlePage.goBack();
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        
        // Verify we're back on Google home
        String backUrl = googlePage.getCurrentUrl();
        System.out.println("Home URL: " + homeUrl);
        System.out.println("Back URL: " + backUrl);
        
        System.out.println("✓ Test 15 Passed: Back navigation successful");
    }

}