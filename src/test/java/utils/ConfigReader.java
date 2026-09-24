package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        return (sys != null && !sys.isEmpty()) ? sys : prop.getProperty(key);
    }
}
