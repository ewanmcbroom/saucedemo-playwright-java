package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private final Properties properties;

    public ConfigManager() {

        properties = new Properties();

        try {
            properties.load(
                    new FileInputStream(
                            "src/test/java/tests/resources/config.properties"
                    )
            );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }
}