package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private ConfigProperties(){}

    private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
    private static final String PATH_TO_RESOURCES = "src/main/resources/";
    private static Properties props = new Properties();
    public static final String YAHOO_BASE_URL;
    public static final String NASCAR_BASE_URL;

    static {
        props = readProperties();
        YAHOO_BASE_URL = props.getProperty("YAHOO.BASE.URL");
        NASCAR_BASE_URL = props.getProperty("NASCAR.BASE.URL");
        logger.info("Yahoo url is {}", YAHOO_BASE_URL);
        logger.info("Nascar url is {}", NASCAR_BASE_URL);
    }

    private static Properties readProperties() throws AssertionError {
        Properties properties = new Properties();
        String configFile = PATH_TO_RESOURCES + "application.properties";
        try {
            logger.info(String.format("Reading configuration data from '%s' file", configFile));
            properties.load(new FileReader(configFile));
        } catch (IOException e) {
            throw new AssertionError(String.format("An exception occurs during loading of '%s' config file", configFile), e);
        }
        return properties;
    }

    private static String getProperty(String propertyName, String defaultValue) {
        return System.getProperty(propertyName.toLowerCase(), props.getProperty(propertyName, defaultValue));
    }

    private static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    private static boolean getBooleanProperty(String propertyName) {
        return Boolean.parseBoolean(getProperty(propertyName));
    }

    private static int getIntegerProperty(String propertyName) {
        return Integer.parseInt(getProperty(propertyName));
    }

    private static int getIntegerProperty(String propertyName, int defaultValue) {
        return  Integer.parseInt(System.getProperty(propertyName.toLowerCase(), props.getProperty(propertyName, defaultValue + "")));
    }
}
