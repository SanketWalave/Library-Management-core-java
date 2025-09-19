package Library.Services;

import Library.Model.BookModel;

import java.util.ArrayList;

public interface UserServicesInter {
    boolean issueBook(int user_id,int book_id,String title,String author,String  catagory,boolean status);
    ArrayList<BookModel> getBooksYouIssued(int user_id);

    boolean returenBook(int user_id,int book_id,String title,String author,String  catagory,boolean status);

}
