package com.patrones.taller1.service.persistance.database.adapters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.patrones.taller1.service.PropertiesAccess;

public class PostgresqlAdapter implements IAdapter {

    private static final String DB_PROPERTIES = "pg.properties";
    private static final String DB_DATABASE_PROP = "database";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP = "password";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";
    
    static {
        //Bloque para registrar el Driver de Oracle
        try {
             Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Connection getConnection() {
        try {
            Properties prop = PropertiesAccess.loadProperty(DB_PROPERTIES);
            String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);
            String connectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(connectionString,user,password);
            System.out.println("Connection class ==> " + connection.getClass().getName());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    private String createConnectionString() {
        Properties prop = PropertiesAccess.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String database = prop.getProperty(DB_DATABASE_PROP);

        String connectionString = "jdbc:postgresql://"+host+":"+port+"/"+database;
        System.out.println("ConnectionString ==> " + connectionString);
        return connectionString;
    }
    
    
}