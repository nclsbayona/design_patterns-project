package com.patrones.taller1.service.persistance.database.adapters;

import java.sql.Connection;

public interface IAdapter {
    public Connection getConnection();
}
