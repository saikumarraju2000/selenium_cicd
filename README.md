# Selenium Java Maven Project

A clean starter project for writing Selenium UI tests with TestNG.

## Requirements
- Java 11+
- Maven 3.6+
- Google Chrome installed

> **No manual ChromeDriver download needed** — WebDriverManager handles it automatically.

---

## Project Structure

```
selenium-tests/
├── pom.xml
├── testng.xml
└── src/test/java/
    ├── tests/
    │   ├── BaseTest.java          ← setup/teardown (extend this in every test)
    │   ├── GoogleSearchTest.java  ← plain test example
    │   └── GooglePageObjectTest.java ← POM-style test example
    └── pages/
        └── GooglePage.java        ← Page Object for Google
```

---

## Running Tests

```bash
# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=GoogleSearchTest

# Run a specific test method
mvn test -Dtest=GoogleSearchTest#testPageTitle
```

---

## Writing a New Test

1. Create a class in `src/test/java/tests/` that **extends BaseTest**
2. Annotate methods with `@Test`
3. Use `driver` (inherited from BaseTest) to interact with the browser
4. Add your class to `testng.xml`

```java
public class MyTest extends BaseTest {

    @Test
    public void testSomething() {
        driver.get("https://example.com");
        Assert.assertTrue(driver.getTitle().contains("Example"));
    }
}
```

---

## Page Object Pattern (recommended)

- Add page classes to `src/test/java/pages/`
- Each class wraps one page: locators + actions, no assertions
- Tests stay readable; locator changes only need updating in one place

---

## Tips

| Goal | How |
|------|-----|
| Run headless | Uncomment `--headless` in `BaseTest.java` |
| Explicit wait | `new WebDriverWait(driver, Duration.ofSeconds(10)).until(...)` |
| Take screenshot | `((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)` |
| Use Firefox | Replace `ChromeDriver` with `FirefoxDriver` and `WebDriverManager.firefoxdriver()` |
