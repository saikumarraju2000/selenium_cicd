package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class to read configuration from properties file.
 */
public class ConfigReader {

    private static Properties properties;

    static {
        try {
            String configPath = "src/test/resources/config.properties";
            FileInputStream file = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets a property value from config.properties.
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a property value with a default value if key is not found.
     *
     * @param key Property key
     * @param defaultValue Default value
     * @return Property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Gets base URL from config.
     *
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.google.com");
    }

    /**
     * Gets browser type from config.
     *
     * @return Browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }

    /**
     * Gets implicit wait timeout from config.
     *
     * @return Timeout in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    /**
     * Gets explicit wait timeout from config.
     *
     * @return Timeout in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "10"));
    }
}
