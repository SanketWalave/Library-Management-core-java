package Library.Services;

import Library.Model.BookModel;
import Library.Model.User_Controler;
import Library.Util.Librarian_dbModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibrarianServices implements LibrarianInterFace{
    private Librarian_dbModel librarian = new Librarian_dbModel();
    File bookFile=new File("D:\\Library Management core java\\Files\\BookLog.txt");
    File userFile=new File("D:\\Library Management core java\\Files\\UserLog.txt");

    @Override
    public boolean addNewBook(String title, String author ,String catagory) {
        boolean sucsess= librarian.addBook(title, author, catagory);
            if(sucsess){
                if(addBookInFile(title, author, catagory)){
                    System.out.println("book added in file");
                }else {
                    System.out.println("book not added in file");
                }
            }
        return sucsess;
    }

    @Override
    public User_Controler loginServices(String user_email, String user_password) {
        User_Controler user[]=new User_Controler[1];
        user[0]=librarian.loginServices(user_email,user_password);

        return user[0];
    }

    @Override
    public ArrayList<BookModel> viewBooks() {
        return librarian.viewBooks();
    }

    @Override
    public boolean deleteBookById(int book_id) {
        return librarian.deleteBookById(book_id);
    }

    @Override
    public boolean updateBook(String title, String author, String catagory, int book_id) {
        return librarian.updateBook(title,author,catagory,book_id);
    }

    @Override
    public boolean addUser(String user_name, String user_email, String user_password) {
        boolean sucsess=librarian.addUser(user_name,user_email,user_password);
        if(sucsess){
            if(addUserInFile(user_name,user_email,"user")) System.out.println(" user added in file ");
            else System.out.println("user not added in file");
        }
        return sucsess;

    }

    @Override
    public ArrayList<User_Controler> getUSer() {
        return librarian.getUser();
    }

    @Override
    public boolean deleteUser(int user_id) {
        return librarian.deleteUser( user_id);
    }

    @Override
    public boolean updateUser(String user_name, String user_email, String user_password, int user_id) {
        return  librarian.updateUser(user_name,user_email,user_password,user_id);
    }




    public boolean addBookInFile(String title,String auther,String catagory){
        System.out.println("int file \t"+title);
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String log = timestamp + " | Title: " + title + " | Author: " + auther + " | Catagory: " + catagory ;

        try (FileWriter fw = new FileWriter(bookFile, true)) { // true -> append mode
            fw.write(log + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
    public boolean addUserInFile(String user_name,String user_email,String role){
//        System.out.println("int file \t"+title);
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String log = timestamp + " | Name: " + user_name + " | email: " + user_email + " | Role: " + role ;

        try (FileWriter fw = new FileWriter(userFile, true)) { // true -> append mode
            fw.write(log + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
