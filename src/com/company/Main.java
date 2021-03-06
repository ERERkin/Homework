package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        Main DB = new Main();
        Connection connection = DB.connect();
        if (connection != null) connection.close();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
