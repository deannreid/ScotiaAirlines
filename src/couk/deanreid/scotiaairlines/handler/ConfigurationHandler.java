/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.6
 Code Version: 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.handler;

import couk.deanreid.scotiaairlines.utils.Reference;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;

public final class ConfigurationHandler {

    private Properties properties = null;
    private static ConfigurationHandler instance = null;

    /**
     * Private constructor
     */
    private ConfigurationHandler() {
        this.properties = new Properties();
        try {
            properties.putAll(properties);
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(Reference.CONFIG_FILE_LOCATION +"/"+Reference.CONFIG_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates the instance is synchronised to avoid multithreads problems
     */
    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ConfigurationHandler();
        }
    }

    /**
     * Get the properties instance. Uses singleton pattern
     * @return 
     */
    public static ConfigurationHandler getInstance() {
        // Uses singleton pattern to guarantee the creation of only one instance
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    /**
     * Get a property of the property file
     * @param key
     * @return 
     */
    public String getProperty(String key) {
        String result = null;
        if (key != null && !key.trim().isEmpty()) {
            result = this.properties.getProperty(key);
        }
        return result;
    }
       public static void load(Class<Reference> configClass, String file) {
        try {
            if (Reference.DEBUG_MODE) {
                System.out.println(Reference.TextPaint.GREEN + "Configuration Loading" + Reference.TextPaint.RESET);
            }
            Properties props = new Properties();
            try (FileInputStream propStream = new FileInputStream(file)) {
                props.putIfAbsent(configClass, file);
                props.load(propStream);
            }
            for (Field field : configClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    field.set(null, getValue(props, field.getName(), field.getType()));
                
                }
            }
        } catch (IOException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException("Error loading configuration: " + e, e);
        }
    }

    private static Object getValue(Properties props, String name, Class<?> type) {
        String value = props.getProperty(name);
        if (value == null) {
            throw new IllegalArgumentException("Missing configuration value: " + name);
        }
        if (type == String.class) {
            return value;
        }
        if (type == boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (type == int.class) {
            return Integer.parseInt(value);
        }
        if (type == float.class) {
            return Float.parseFloat(value);
        }
        throw new IllegalArgumentException("Unknown configuration value type: " + type.getName());
    }

    /**
     * Override the clone method to ensure the "unique instance" requirement of
     * this class
     * @return 
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
