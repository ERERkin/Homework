package com.company;

import netscape.javascript.JSUtil;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        Main DB = new Main();
        Connection connection = DB.connect();
        DB.printTrainersSalary();
        DB.printStudentsCount("JAVA");
        DB.printGroupList("JAVA");
        if (connection != null) connection.close();
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void printTrainersSalary() {
        String SQL = "select t.name, st.salary from trainers t " +
                "join sport_types st on t.sport_type = st.id";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            int sum = 0;
            while (rs.next()) {
                String K = rs.getString("name") + " ";
                if(K.length() > 4)
                    K += "Молодец ";
                K += rs.getString("salary");
                System.out.println(K);
                sum += rs.getInt("salary");
            }
            System.out.println("Итоговая сумма " + sum);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printStudentsCount(String n) {
        String SQL = "select count(*) from students_3 s2 join groups_3 ig on s2.group_s = ig.id where ig.\"name\" = " +
                "'" + n + "';";
        try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            int count = rs.getInt(1);
            System.out.println("В группе " + n + " " + count + " студент(а,ов)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printGroupList(String n) {
        String SQL = "select s2.name, ig.name from students_3 s2 join groups_3 ig on s2.group_s = ig.id where ig.\"name\" = " +
                "'" + n + "';";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String K = rs.getString(1) + " " + rs.getString(2);
                System.out.println(K);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
