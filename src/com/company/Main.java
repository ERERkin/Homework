package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        /*Main DB = new Main();
        Connection connection = DB.connect();
        if (connection != null) connection.close();*/
        Scanner sc = new Scanner(System.in);
        Factorial factorial = (s) ->{
          long ans = 1;
          for(int i = 2;i <= s; i++){
              ans *= i;
          }
          return ans;
        };
        /*System.out.println(factorial.fact(5));*/
        Power power = (x, y) ->{
            int ans = 0;
            for(int i = 0; i <= y; i++){
                if(Math.pow(i,x) == y){
                    ans = i;
                    break;
                }
            }
            return ans;
        };
        int k = power.superPower(3,8);
        System.out.println(k);
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
