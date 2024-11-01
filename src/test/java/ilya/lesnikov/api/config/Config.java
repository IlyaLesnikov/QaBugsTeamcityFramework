package ilya.lesnikov.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static String CONFIG_PROPERTIES = "config.properties";
    private static Config configInstance;
    private static Properties properties;

    private Config() {
        properties = new Properties();
        loadProperties();
    }

    private static Config getConfigInstance() {
        if (configInstance == null) {
            synchronized (Config.class) {
                if (configInstance == null)
                    configInstance = new Config();
            }
        }

        return configInstance;
    }

    private static void loadProperties() {

        try (InputStream stream = Config.class.getClassLoader().getResourceAsStream(Config.CONFIG_PROPERTIES)) {
            if (properties == null)
                System.err.println("File not found " + Config.CONFIG_PROPERTIES);
            properties.load(stream);

        } catch (IOException e) {
            System.err.println("Error during file reading " + Config.CONFIG_PROPERTIES);
        }
    }

    public static String getProperties(String key) {
        return getConfigInstance().properties.getProperty(key);
    }

}
