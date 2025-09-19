package Library.Services;

import Library.Model.BookModel;
import Library.Util.User_db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserServices implements UserServicesInter {

    // ✅ File where logs will be stored
    private final File transactionFile = new File("D:\\Library Management core java\\Files\\BookIssue.txt");

    private final User_db userDb = new User_db();

    @Override
    public boolean issueBook(int user_id, int book_id, String title, String author, String category, boolean status) {
        boolean success = userDb.issueBook(user_id, book_id);

        if (success) {
            // ✅ Log to file only if DB insert/update is successful
            if (addBookInFile(user_id, book_id, title, author, category, status)) {
                System.out.println("✅ Book issued and logged to file.");
            } else {
                System.out.println("⚠ Book issued but failed to log transaction to file.");
            }
        }
        return success;
    }

    @Override
    public ArrayList<BookModel> getBooksYouIssued(int user_id) {
        return userDb.getBooksYouIssued(user_id);
    }

    @Override
    public boolean returenBook(int user_id, int book_id, String title, String author, String category, boolean status) {
        boolean success = userDb.returnBook(user_id, book_id);

        if (success) {
            // ✅ Log return action (status = false since book returned)
            if (addBookInFile(user_id, book_id, title, author, category, false)) {
                System.out.println("✅ Book returned and logged to file.");
            } else {
                System.out.println("⚠ Book returned but failed to log transaction to file.");
            }
        }
        return success;
    }

    /**
     * Logs book transaction to file in CSV-like format.
     */
    public boolean addBookInFile(int user_id, int book_id, String title, String author, String category, boolean status) {
        System.out.println("📂 Logging book transaction to file...");
        System.out.println("Writing to: " + transactionFile.getAbsolutePath());

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // ✅ Include all values properly in the log
        String log = String.format("%s,%d,%d,%s,%s,%s,%b",
                timestamp, user_id, book_id, title, author, category, status);

        try (FileWriter fw = new FileWriter(transactionFile, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(log);
            pw.println("---------------------------------------------------------------------------");
            return true;

        } catch (IOException e) {
            System.err.println("❌ Error writing to log file: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
