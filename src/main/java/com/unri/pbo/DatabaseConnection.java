package com.unri.pbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String HOST = "localhost:3306";
    private static final String DB   = "techstore_pos";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String URL  = "jdbc:mysql://" + HOST + "/" + DB;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}