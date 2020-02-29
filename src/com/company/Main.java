package com.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static java.time.ZonedDateTime.now;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        Main DB = new Main();/*
        Connection connection = DB.connect();
        if (connection != null) connection.close();*/
        Random rnd = new Random();
        String[] days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
        /*String[] month = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};*/
        LinkedHashMap<WeeksDay, Integer> map = new LinkedHashMap<>();
        ArrayList<Day> hundredDays = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            map.put(new WeeksDay(days[i]), 0);
        }
        for(int i = 0; i < 100; i++){
            hundredDays.add(new Day(rnd.nextInt(12),rnd.nextInt(30) + 1));
        }
        for(int i = 0; i < hundredDays.size(); i++){
            int thisDay = hundredDays.get(i).getMonth() * 30 + hundredDays.get(i).getDayNumber();
            WeeksDay thisWeeksDay = new WeeksDay(days[thisDay % 7]);
            int k = map.getOrDefault(thisWeeksDay, 0);
            map.put(thisWeeksDay, k + 1);
        }
        System.out.println(map);
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

class WeeksDay{
    private String name;

    public WeeksDay(String name) {
        this.name = name;
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
        WeeksDay weeksDay = (WeeksDay) o;
        return Objects.equals(name, weeksDay.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Day{
    private int month;
    private int dayNumber;

    public Day(int month, int dayNumber) {
        this.month = month;
        this.dayNumber = dayNumber;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }
}