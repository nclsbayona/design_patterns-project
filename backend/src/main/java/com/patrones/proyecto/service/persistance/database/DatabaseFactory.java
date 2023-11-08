package com.patrones.proyecto.service.persistance.database;

import java.util.Properties;

import com.patrones.proyecto.service.PropertiesAccess;
import com.patrones.proyecto.service.persistance.database.adapters.PostgresqlAdapter;
import com.patrones.proyecto.service.persistance.database.adapters.IAdapter;

public class DatabaseFactory {
    private static final String DB_FACTORY_PROPERTY_URL = "database.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";

    public static IAdapter getDBadapter(DBTypes dbType) {
        switch (dbType) {
            case Postgresql:
                return new PostgresqlAdapter();
            default:
                throw new IllegalArgumentException("Not supported");
        }
    }

    public static IAdapter getDefaultDBAdapter() {
        try {
            Properties prop = PropertiesAccess.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultDBClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
            System.out.println("DefaultDBClass ==> " + defaultDBClass);
            return (IAdapter) Class.forName(defaultDBClass).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
