package ru.job4j.parser;

/**
 * Settings.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private Properties properties;

    /**
     * Constructor.
     */
    public Settings(String propertiesFile) {
        this.properties = new Properties();
        this.load(propertiesFile);
    }

    /**
     * Load settings from file.
     */
    private void load(String propertiesFile) {
        ClassLoader loader = this.getClass().getClassLoader();
        try (InputStream is = loader.getResourceAsStream(propertiesFile)) {
            this.properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value by key.
     *
     * @param key key.
     * @return value.
     */
    public String getValue(final String key) {
        return this.properties.getProperty(key);
    }
}
