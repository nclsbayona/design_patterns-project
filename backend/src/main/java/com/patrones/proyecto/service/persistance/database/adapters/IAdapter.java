package com.patrones.proyecto.service.persistance.database.adapters;

import java.sql.Connection;

public interface IAdapter {
    public Connection getConnection();
}
