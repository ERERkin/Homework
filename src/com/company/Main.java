package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static void main(String[] args) throws SQLException {
        //Logs
        Main DB = new Main();
        Connection connection = connect();
        User user = new User("Aza","Aza@za","aza123");
        User user1 = new User("EA","EA@za","EA123");
        //DB.register(user);
        //DB.authorize("Aza","aza12");
        //DB.deleteBlockedUser("Aza");
        for (UserLog ug: userLogs()) {
            System.out.println(ug);
        }
        for (UserLog2 ug: userLogs2()) {
            System.out.println(ug);
        }
        if (connection != null) connection.close();
    }

    public static ArrayList<UserLog2> userLogs2(){
        String SQL = "select us.login, u.time_log, u.status  from userlogs u join users us on u.user_id = us.id";
        ArrayList<UserLog2> ans = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    ans.add(new UserLog2(rs.getString("login"), rs.getTimestamp("time_log"),
                            rs.getString("status")));
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }

    public static ArrayList<UserLog> userLogs(){
        String SQL = "select * from userlogs";
        ArrayList<UserLog> ans = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    ans.add(new UserLog(rs.getInt("id"), rs.getInt("user_id"),
                            rs.getTimestamp("time_log"), rs.getString("status"),
                            rs.getInt("count")));
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ans;
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

    public void addToUserLogs(int login, Status status, int count){
        String SQL = "insert into userLogs (user_id, time_log, status, count) values (?,now(),?,?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, login);
            stmt.setString(2, status.toString());
            stmt.setInt(3, count);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void authorize(String login, String password){
        if(checkIs(login)) return;
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
            addToUserLogs(k,Status.OK,0);
            deleteFromUserLogs(k);
        }else{
            addToUserLogs(k,Status.FAIL,1);
            if(CheckUserLogs(k) >= 3){
                addToBlockedUsers(k);
            }
        }
    }

    public int CheckUserLogs(int g){
        String SQL = "select count (*) from userlogs where user_id = ? and count = 1";
        int k = 0;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, g);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public void addToBlockedUsers(int id){
        String SQL = "insert into blockedusers (user_id) values (?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        deleteFromUserLogs(id);
    }

    public void deleteFromUserLogs(int id){
        String SQL = "update userlogs set count = 0 where user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkIs(String login){
        String SQL = "select * from users where login = ?";
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
        SQL = "select count (*) from blockedusers where user_id = ?;";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, k);
            try(ResultSet rs = stmt.executeQuery()){
                rs.next();
                k = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(k == 1) return true;
        return false;
    }

    public void deleteBlockedUser(String login){
        String SQL = "select * from users where login = ?";
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
        SQL = "delete from blockedusers where user_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL);) {
            stmt.setInt(1, k);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

class UserLog{
    private int id;
    private int user_id;
    private Timestamp timeLog;
    private String status;
    private int count;

    public UserLog(int id, int user_id,Timestamp timeLog, String status, int count) {
        this.id = id;
        this.user_id = user_id;
        this.timeLog = timeLog;
        this.status = status;
        this.count = count;
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


    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", timeLog=" + timeLog +
                ", status='" + status + '\'' +
                ", count=" + count +
                '}';
    }
}

class UserLog2{
    private String login;
    private Timestamp timeLog;
    private String status;

    public UserLog2(String login, Timestamp timeLog, String status) {
        this.login = login;
        this.timeLog = timeLog;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Timestamp getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(Timestamp timeLog) {
        this.timeLog = timeLog;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserLog2{" +
                "login='" + login + '\'' +
                ", timeLog=" + timeLog +
                ", status='" + status + '\'' +
                '}';
    }
}



enum Status{
    FAIL,
    OK
}
