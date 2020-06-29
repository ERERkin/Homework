package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        /*Main DB = new Main();
        Connection connection = DB.connect();
        if (connection != null) connection.close()*/;
        ThreadD.a = 100;
        Thread t1 = new Thread(new ThreadD(1));
        Thread t2 = new Thread(new ThreadD(2));
        Thread t3 = new Thread(new ThreadD(3));
        Thread t4 = new Thread(new ThreadD(4));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
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
