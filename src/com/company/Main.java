package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        /*Main DB = new Main();
        Connection connection = DB.connect();
        if (connection != null) connection.close();*/
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(0, "Zero");
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        hashMap.put(4, "Four");
        hashMap.put(5, "Five");
        hashMap.put(6, "Six");
        hashMap.put(7, "Seven");
        hashMap.put(8, "Eight");
        hashMap.put(9, "Nine");
        hashMap.put(10, "Ten");
        hashMap.put(11, "Eleven");
        /*int i = 0;
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> integerList = list1.stream()
                .filter(x -> x > 3)
                .collect(Collectors.toList());*/
        List<String> list = hashMap.keySet().stream()
                .filter(x -> x > 5)
                .map(x -> hashMap.get(x))
                .collect(Collectors.toList());
        System.out.println(list);
        Stream<HashMap.Entry<Integer, String>> stream = hashMap.entrySet().stream();
        Optional<String> reduced = stream
                .filter(x -> x.getKey() % 3 == 0)
                .map(x -> x.getValue())
                .reduce(
                (value, combined) ->{
                    return value + ", " + combined;
                }
        );
        System.out.println(reduced.get());
        Stream<HashMap.Entry<Integer, String>> stream1 = hashMap.entrySet().stream();
        Optional<Integer> reduced1 = stream1
                .filter(x -> x.getValue().length() >= 5)
                .map(x -> x.getKey())
                .reduce(
                        (value, combined) ->{
                            return value * combined;
                        }
                );
        System.out.println(reduced1.get());
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
