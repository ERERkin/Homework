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
        System.out.println("Task 1");
        DB.printTrainersSalary();
        System.out.println("Task 2");
        DB.printStudentsCount("JAVA");
        DB.printGroupList("JAVA");
        System.out.println("Task 3");
        DB.findCitiesWhereCountryStartsOnK();
        DB.setLanguage("Russian", 1);
        DB.countriesPopulationSum();
        System.out.println("Task 4");
        DB.findMarkAverage("Me");
        DB.informationAboutStudent("Me");
        if (connection != null) connection.close();
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //Task 1
    public void printTrainersSalary() {
        String SQL = "select t.name, st.salary from trainers t " +
                "join sport_types st on t.sport_type = st.id";
        try(Connection conn = connect();
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
    //Task 2
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
    //Task 3
    public void findCitiesWhereCountryStartsOnK() {
        String SQL = "select c2.name, p.name, c3.name, p1.name, c3.code, c3.population from cities c2\n" +
                "join countries c3 on c2.country = c3.id \n" +
                "join people p on c2.mayor = p.id\n" +
                "join people p1 on c3.president = p1.id\n" +
                "where c3.name like 'K%';";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String K = rs.getString(1) + " " + rs.getString(2)
                        + " " + rs.getString(3)+ " " + rs.getString(4)
                        + " " + rs.getString(5)+ " " + rs.getString(6);
                System.out.println(K);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setLanguage(String n, int k) {
        String SQL = "update countries set language_country = ? where id = ?;";
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, n);
            stmt.setInt(2,k);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void countriesPopulationSum() {
        String SQL = "select sum(c2.population) from countries c2;";
        try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Task 4
    public void findMarkAverage(String n) {
        String SQL = "select avg(m.mark) from marks m\n" +
                "join students_4 s on m.student = s.id\n" +
                "join subjects s1 on m.subject = s1.id\n" +
                "where s.\"name\" = '" + n + "';";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void informationAboutStudent(String n) {
        String SQL = "select s.\"name\",g.\"name\",f.\"name\",u.\"name\" from students_4 s\n" +
                "join groups_4 g on s.group_4 = g.id\n" +
                "join faculties f on g.faculty = f.id\n" +
                "join universities u on f.university = u.id\n" +
                "where s.\"name\" = '" + n + "';";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String K = rs.getString(1) + " " + rs.getString(2)
                        + " " + rs.getString(3)+ " " + rs.getString(4);
                System.out.println(K);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
