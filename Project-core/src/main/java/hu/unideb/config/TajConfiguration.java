package hu.unideb.config;

import java.io.IOException;
import java.util.Properties;

public class TajConfiguration {

    private static Properties properties = new Properties();    //Maybe final



    static {
        try {
            properties.load(TajConfiguration.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static Properties getProperties() {
            return properties;
        }

        public static String getValues(String key){
            return properties.getProperty(key);
        }

}
