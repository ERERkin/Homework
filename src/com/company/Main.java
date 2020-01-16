package com.company;

import java.sql.*;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        Main DB = new Main();
        Connection connection = DB.connect();
        User user = new User("Aza","Aza@za","aza123");
        User user1 = new User("EA","EA@za","EA123");
        //DB.register(user);
        DB.authorize("Aza","aza123");
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
    public void register(User user){
        String SQL = "insert into users (login, email, password, date_of_registration) values (?,?,?,now())";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String decryptPassword(String password){
        return User.reversPassword(password).substring(1);
    }

    public void addToUserLogs(int login, Status status){
        String SQL = "insert into userLogs (user_id, time_log, status) values (?,now(),?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, login);
            stmt.setString(2, status.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void authorize(String login, String password){
        password = User.encrypt(password);
        String SQL = "select count(*) from users where login = ? and password = ?;";
        int a = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                a = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        SQL = "select * from users where login = ?";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setString(1, login);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(a == 1) {
            addToUserLogs(k,Status.OK);
        }else
            addToUserLogs(k,Status.FAIL);
    }
}

class User{
    private int id;
    private String login;
    private String email;
    private String password;
    private Date dateOfRegistration;

    public User(String login, String email, String password) {
        //this.id = id;
        this.login = login;
        this.email = email;
        this.password = encrypt(password);
        //this.dateOfRegistration = dateOfRegistration;
    }

    static String encrypt(String p){
        String rev = reversPassword(p);
        return rev + rev.charAt(p.length() - 1);
    }

    static String reversPassword(String p){
        String ans = "";
        for(int i = p.length() - 1; i >= 0; i--){
            ans += p.charAt(i);
        }
        return ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encrypt(password);
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }
}

class UserLogs{
    private int id;
    private int user_id;
    private Timestamp timeLog;
    private Status status;

    public UserLogs(int id, int user_id, Status status) {
        this.id = id;
        this.user_id = user_id;
        //this.timeLog = timeLog;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTimeLog() {
        return timeLog;
    }


    public Status getStatus() {
        return status;
    }

}

enum Status{
    FAIL,
    OK
}