package ilya.lesnikov.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private final static String CONFIG_PROPERTIES = "config.properties";
    private static Configuration configInstance;
    private static Properties properties;

    private Configuration() {
        properties = new Properties();
        loadProperties();
    }

    private static Configuration getConfigInstance() {
        if (configInstance == null) {
            synchronized (Configuration.class) {
                if (configInstance == null)
                    configInstance = new Configuration();
            }
        }

        return configInstance;
    }

    private static void loadProperties() {

        try (InputStream stream = Configuration.class.getClassLoader().getResourceAsStream(Configuration.CONFIG_PROPERTIES)) {
            if (properties == null)
                System.err.println("File not found " + Configuration.CONFIG_PROPERTIES);
            properties.load(stream);

        } catch (IOException e) {
            System.err.println("Error during file reading " + Configuration.CONFIG_PROPERTIES);
        }
    }

    public static String getProperties(String key) {
        return getConfigInstance().properties.getProperty(key);
    }

}
