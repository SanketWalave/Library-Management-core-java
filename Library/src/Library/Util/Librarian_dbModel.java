package Library.Util;

import Library.Model.BookModel;
import Library.Model.User_Controler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;


public class Librarian_dbModel {
    private static final String url = "jdbc:mysql://localhost:3306/Library_management";
    private static final String user = "root";
    private static final String password = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Test connection once when class loads
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                if (conn != null && !conn.isClosed()) {
                    System.out.println("Database connected successfully!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("Failed to connect to DB: " + e.getMessage());
        }
    }

    // Get DB connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    // Method to add a book
    public boolean addBook(String title, String author, String catagory) {
//        System.out.println(title);
//        System.out.println(author);
//        System.out.println(catagory);
//        return true;
        String sql = "INSERT INTO books (title, author, category) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, catagory);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // true if insert successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // false if any error
        }
    }


    public User_Controler loginServices(String user_email, String password) {
        String sql = "SELECT * FROM user WHERE user_email = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user_email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // get hashed password from DB
                    String storedHash = rs.getString("user_password");

                    // check entered password against hash
                    if (BCrypt.checkpw(password, storedHash)) {
                        // password matches → return user object
                        return new User_Controler(
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("user_email"),
                                storedHash,   // you may skip returning password for security
                                rs.getString("role")
                        );
                    } else {
                        return null; // wrong password
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // login failed (no user or wrong password)
    }

    public ArrayList<BookModel> viewBooks() {
        ArrayList<BookModel> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookModel book = new BookModel(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("available")
                );
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public boolean deleteBookById(int book_id) {
        String sql = "DELETE FROM books WHERE book_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, book_id);

            int rowsAffected = ps.executeUpdate(); // 🔹 actually executes the delete

            return rowsAffected > 0; // 🔹 true if at least one row was deleted

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateBook(String title, String author, String catagory, int book_id) {
        String sql = "update books set title=?,author=?,category=? where book_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, catagory);
            ps.setInt(4,book_id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // true if insert successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // false if any error
        }
    }


    public boolean addUser(String user_name, String user_email, String user_password) {
        String sql = "INSERT INTO user (user_name, user_email, user_password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // hash the password before storing
            String hashedPassword = BCrypt.hashpw(user_password, BCrypt.gensalt(12));

            ps.setString(1, user_name);
            ps.setString(2, user_email);
            ps.setString(3, hashedPassword);
            ps.setString(4, "user");

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<User_Controler> getUser(){
        String sql="select *from user";
        ArrayList<User_Controler> userList=new ArrayList<>();
        try(Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery()){
            while (rs.next()){
//    public User_Controler(int user_id, String user_name, String user_email, String user_password, String role) {
                User_Controler userControler=new User_Controler(
                      rs.getInt("user_id"),
                      rs.getString("user_name"),
                      rs.getString("user_email"),
                      rs.getString("user_password"),
                      rs.getString("role"));
                userList.add(userControler);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return userList;
    }

    public boolean deleteUser(int user_id){
        String sql="delete from user where user_id=?";
        try(Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,user_id);

           return ps.executeUpdate()>0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean updateUser(String user_name, String user_email, String user_password, int user_id) {
        String sql="update user set user_name=?,user_email=? where user_id=?";
        try(Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setString(1,user_name);
            ps.setString(2,user_email);
            ps.setInt(3,user_id);

            return ps.executeUpdate()>0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
