package com.borlok.repository.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Utils {
    private static String DATABASE_URL;

    private static String USER;
    private static String PASSWORD;


    private static void prepareToConnectToDbAndRegisterDriver() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("liquibase/liquibase");
        String JDBC_DRIVER = resourceBundle.getString("driver");
        DATABASE_URL = resourceBundle.getString("url");
        USER = resourceBundle.getString("username");
        PASSWORD = resourceBundle.getString("password");

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось загрузить драйвер. " + e);
        }

    }

    public static Connection getConnection () throws SQLException {
        prepareToConnectToDbAndRegisterDriver();
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static ResourceBundle getResourceForSqlCommands() {
        return ResourceBundle.getBundle("JdbcSqlCommands");
    }
}
