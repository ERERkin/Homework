package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        ArrayList<City> cities1 = new ArrayList<>();
        ArrayList<City> cities2 = new ArrayList<>();
        cities1.add(new City(1,"Bishkek", 1200000, "Нз кто"));
        cities1.add(new City(3,"Almaty", 1800000, "Нз кто"));
        cities1.add(new City(4,"Kara Kol", 75000, "Нз кто"));
        cities1.add(new City(6,"Osh", 500000, "Нз кто"));
        cities1.add(new City(10,"Balykchy", 50000, "Нз кто"));
        cities2.add(new City(2,"Moscow", 12000000, "Нз кто"));
        cities2.add(new City(3,"Minsk", 7000000, "Нз кто"));
        cities2.add(new City(4,"Almaty", 1700000, "Нз кто"));
        cities2.add(new City(7,"Cholpon Ata", 70000, "Нз кто"));
        cities2.add(new City(8,"New York", 8000000, "Нз кто"));
        cities2.add(new City(10,"Astana", 2000000, "Нз кто"));
        Main DB = new Main();
        Connection connection = DB.connect();
        DB.addListToTable(cities1);
        DB.addListToTable(cities2);
        if (connection != null) connection.close();
    }

    public void addListToTable(ArrayList<City> list){
        for(City c: list){
            if(check(c.getId())){
                upDate(c.getId(),c);
            }else{
                insertInto(c);
            }
        }
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

    public boolean check(int id) {
        int a = 0;
        String SQL = "select count(*) from cities_2 where id = ?;";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(a == 1){
            return true;
        }
        return false;
    }

    public void insertInto(City city) {
        int a = 0;
        String SQL = "insert into cities_2(id, name, population, mayor) values (?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1,city.getId());
            stmt.setString(2,city.getName());
            stmt.setInt(3,city.getPopulation());
            stmt.setString(4,city.getMayor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void upDate(int id, City city) {
        int a = 0;
        String SQL = "update cities_2 set name = ?, population = ?, mayor = ? " +
                "where id = ?;";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1,city.getName());
            stmt.setInt(2,city.getPopulation());
            stmt.setString(3,city.getMayor());
            stmt.setInt(4,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

class City{
    private int id;
    private String name;
    private int population;
    private String mayor;

    public City(int id, String name, int population, String mayor) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.mayor = mayor;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }
}