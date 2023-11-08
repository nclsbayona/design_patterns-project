package com.patrones.proyecto.service;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesAccess {
    public static Properties loadProperty(String propertiesURL){
        try {
            Properties properties = new Properties();
            InputStream inputStream = PropertiesAccess.class.getClassLoader().getResourceAsStream(propertiesURL);
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
