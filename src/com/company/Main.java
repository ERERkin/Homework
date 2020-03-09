package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.SecondGeneric.getSum;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        /*Main DB = new Main();
        Connection connection = DB.connect();
        if (connection != null) connection.close();*/
        FirstGeneric<Integer> firstGeneric = new FirstGeneric<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer a[] = {1,2,3};
        Number number= SecondGeneric.getSum(list);
        System.out.println(number);
        number = SecondGeneric.getAvg(a);
        System.out.println(number);
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
