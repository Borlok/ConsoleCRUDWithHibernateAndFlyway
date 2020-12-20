package com.borlok.repository.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentNavigableMap;

public class Utils {
    private static String DATABASE_URL;
    private static Connection connection;
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
        if (connection == null) {
            System.out.println("Создано новое подключение");
            prepareToConnectToDbAndRegisterDriver();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        }
        return connection;
    }

    public static ResourceBundle getResourceForSqlCommands() {
        return ResourceBundle.getBundle("JdbcSqlCommands");
    }
}
