package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        Main DB = new Main();
        /*Connection connection = DB.connect();
        if (connection != null) connection.close();*/
        Random rnd = new Random();
        char c[] = {'A','B','C','D','E'};
        HashSet<City> hashSet = new HashSet<>();
        TreeSet<City> treeSet = new TreeSet<>();
        for(int i = 0; i < 10; i++){
            String name = "";
            for(int j = 0; j < rnd.nextInt(10) + 1; j++){
                name += c[rnd.nextInt(5)];
            }
            City city = new City(i, name);
            if(i % 2 == 0){
                hashSet.add(city);
            }else{
                treeSet.add(city);
            }
        }
        System.out.println(treeSet);
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

class City implements Comparable{
    private int id;
    private String name;
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof City)) return 0;
        City city = (City)o;
        if(this.name.compareTo(city.name) > 0) return -1;
        else if(this.name.compareTo(city.name) < 0) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}