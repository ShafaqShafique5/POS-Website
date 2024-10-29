package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;

    // Load properties based on the environment
    public ConfigReader(String environment) throws IOException {
        properties = new Properties();
        String configFilePath = "src/test/resources/properties/config-" + environment + ".properties";

        // Load properties file, IOException will propagate
        FileInputStream input = new FileInputStream(configFilePath);
        properties.load(input);
        input.close();
    }

    // Getter for properties
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
