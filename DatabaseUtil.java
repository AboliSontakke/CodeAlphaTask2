package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("Unable to find db.properties");
            }
            props.load(input);
        }
        return props;
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties props = loadProperties();
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, username, password);
    }
}
