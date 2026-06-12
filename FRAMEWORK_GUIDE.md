# Selenium Test Framework - Project Structure

## New Project Structure

```
selenium-tests/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java                 # Base test class with setup/teardown
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java             # Config file reader
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java                 # Base page object
│   │   │   │   └── GooglePage.java               # Google page object
│   │   │   └── utils/
│   │   │       ├── BrowserFactory.java           # WebDriver creation factory
│   │   │       ├── WaitUtils.java                # Explicit wait utilities
│   │   │       └── ScreenshotUtils.java          # Screenshot capture utilities
│   │   └── resources/
│   ├── test/
│   │   ├── java/
│   │   │   └── tests/
│   │   │       ├── GoogleSearchTest.java         # Simple search tests
│   │   │       └── GooglePageObjectTest.java     # POM-based tests
│   │   └── resources/
│   │       ├── config.properties                 # Configuration properties
│   │       └── testing.xml                       # TestNG configuration
├── pom.xml                                       # Maven configuration
└── README.md                                     # Documentation
```

## Key Classes

### 1. BrowserFactory (utils)
- Creates WebDriver instances for Chrome, Firefox, Edge
- Manages WebDriver configuration and timeouts
- Provides comprehensive logging

### 2. BaseTest (base)
- Initializes WebDriver before each test
- Cleans up driver after each test
- Can be extended by all test classes

### 3. BasePage (pages)
- Base class for all Page Objects
- Contains common page methods
- Initializes PageFactory elements

### 4. ConfigReader (config)
- Reads from config.properties
- Provides configuration values
- Supports default values

### 5. WaitUtils (utils)
- Explicit wait methods
- Wait for visible/clickable elements
- Page load wait

### 6. ScreenshotUtils (utils)
- Captures screenshots with timestamp
- Saves to test-output/screenshots/
- Error handling built-in

## How to Use

### Run tests with specific browser:
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Run specific test:
```bash
mvn test -Dtest=GoogleSearchTest
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testing.xml
```

## Configuration

Edit `src/test/resources/config.properties` to customize:
- Browser type
- Base URL
- Wait timeouts
- Logging level

## Page Object Pattern

Create new page objects by:

1. Extend `BasePage`
2. Use `@FindBy` annotations for locators
3. Create methods for page actions

Example:
```java
public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        // ... rest of login logic
    }
}
```

## Dependencies Added

- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- WebDriverManager 5.7.0
- Apache Commons IO 2.14.0
