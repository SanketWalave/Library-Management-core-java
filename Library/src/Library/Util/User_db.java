package Library.Util;

import Library.Model.BookModel;

import java.sql.*;
import java.util.ArrayList;

public class User_db {
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

    public boolean issueBook(int user_id, int book_id) {
        String insertTransactionSql =
                "INSERT INTO transactions (book_id, user_id, borrow_date, status) VALUES (?, ?, CURDATE(), 'borrowed')";
        String updateBookSql =
                "UPDATE books SET available = 0 WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement insertStmt = con.prepareStatement(insertTransactionSql);
             PreparedStatement updateStmt = con.prepareStatement(updateBookSql)) {

            // 1️⃣ Insert into transactions
            insertStmt.setInt(1, book_id);
            insertStmt.setInt(2, user_id);
            int rowsInserted = insertStmt.executeUpdate();

            // 2️⃣ Update book availability
            updateStmt.setInt(1, book_id);
            int rowsUpdated = updateStmt.executeUpdate();

            return rowsInserted > 0 && rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<BookModel> getBooksYouIssued(int user_id) {
        ArrayList<BookModel> issuedBooks = new ArrayList<>();

        // ✅ Corrected SQL (alias "u" added + select proper columns)
        String sql = "SELECT b.book_id, b.title, b.author, b.category, b.available " +
                "FROM books b " +
                "JOIN transactions t ON t.book_id = b.book_id " +
                "JOIN user u ON u.user_id = t.user_id " +
                "WHERE u.user_id = ? AND t.status = 'borrowed'";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int book_id = rs.getInt("book_id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String category = rs.getString("category");
                    boolean available = rs.getBoolean("available");

                    BookModel book = new BookModel(book_id, title, author, category, available);
                    issuedBooks.add(book);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return issuedBooks;
    }
    public boolean returnBook(int user_id, int book_id) {
        String updateTransactionSql =
                "UPDATE transactions SET return_date = CURDATE(), status = 'returned' " +
                        "WHERE book_id = ? AND user_id = ? AND status = 'borrowed'";
        String updateBookSql =
                "UPDATE books SET available = 1 WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement updateTransactionStmt = con.prepareStatement(updateTransactionSql);
             PreparedStatement updateBookStmt = con.prepareStatement(updateBookSql)) {

            // 1️⃣ Update transaction
            updateTransactionStmt.setInt(1, book_id);
            updateTransactionStmt.setInt(2, user_id);
            int rowsUpdatedTx = updateTransactionStmt.executeUpdate();

            // 2️⃣ Update book availability
            updateBookStmt.setInt(1, book_id);
            int rowsUpdatedBook = updateBookStmt.executeUpdate();

            return rowsUpdatedTx > 0 && rowsUpdatedBook > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
