package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader reads properties from the classpath (src/resources/properties/config.properties).
 * Throws RuntimeException if the file is missing or unreadable.
 */
public class ConfigReader {

    private final Properties properties;

    /**
     * Constructor loads properties from classpath.
     */
    public ConfigReader() {
        properties = new Properties();
        // Load properties file from classpath
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("properties/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Config file 'properties/config.properties' not found in classpath.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file. Error: " + e.getMessage());
        }
    }

    /**
     * Get the value of a property key
     * @param key property name
     * @return property value as String
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config file.");
        }
        return value;
    }
}
