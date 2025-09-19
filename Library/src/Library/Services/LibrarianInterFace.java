package Library.Services;

import Library.Model.BookModel;
import Library.Model.User_Controler;

import java.util.ArrayList;
import java.util.List;

public interface LibrarianInterFace {
    boolean addNewBook(String title, String author, String catagory);
    User_Controler loginServices(String user_email, String user_password);
    List<BookModel> viewBooks();
    boolean deleteBookById(int book_id);
    boolean updateBook(String title, String author, String catagory,int book_id);
    boolean addUser(String user_name, String user_email, String user_password);
    ArrayList<User_Controler> getUSer();
    boolean deleteUser(int user_id);
    boolean updateUser(String user_name, String user_email, String user_password,int user_idUSer);

}
