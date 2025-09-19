//import Library.Model.BookModel;
//import Library.Model.User_Controler;
//import Library.Services.LibrarianServices;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//enum LibServices {
//    login, addBook, viewBook;
//}
//
//public class Main {
//
//    // ✅ Utility method for safe integer input
//    public static int getIntInput(Scanner sc, String msg) {
//        int num;
//        while (true) {
//            try {
//                System.out.print(msg);
//                num = sc.nextInt();
//                sc.nextLine(); // clear buffer
//                return num;
//            } catch (InputMismatchException e) {
//                System.out.println("⚠ Invalid input! Please enter a number.");
//                sc.nextLine(); // clear invalid input
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        LibrarianServices lbs = new LibrarianServices();
//
//        int login = getIntInput(sc, "Enter 1 for login and 2 for signup: ");
//        User_Controler user=null;
//        if (login == 1) {
//            int loginTries = 3;
//
//            // ✅ retry login logic
//            while (user == null && loginTries > 0) {
//                System.out.println("Enter user email and password to login:");
//                user = lbs.loginServices(sc.nextLine(), sc.nextLine());
//
//                if (user == null) {
//                    loginTries--;
//                    if (loginTries > 0) {
//                        System.out.println(" Wrong info! You have " + loginTries + " chances left.");
//                    } else {
//                        System.out.println(" Too many failed attempts. Exiting...");
//                        System.exit(0);
//                    }
//                }
//            }
//            if (user.getRole().equals("librarian")) {
//                System.out.println("✅ You are librarian");
//
//                boolean mainMenu = true;
//                while (mainMenu) {
//                    int choice = getIntInput(sc,
//                            "\n---- Main Menu ----\n" +
//                                    "1. Manage Books 📚\n" +
/// /                                    "2. Manage Admins\n" +
//                                    "2. Manage Users\n" +
//                                    "3. Exit\n" +
//                                    "Enter choice: ");
//
//                    switch (choice) {
//                        case 1: // Manage Books
//                            boolean bookMenu = true;
//                            while (bookMenu) {
//                                int bookChoice = getIntInput(sc,
//                                        "\n---- Manage Books ----\n" +
//                                                "1. Add Book   ➕\n" +
//                                                "2. View Books 👁️\n" +
//                                                "3. Search Book🔍\n" +
//                                                "4. Delete Book⭕\n" +
//                                                "5. Update Book✏️\n" +
//                                                "6. Back to Main Menu\n" +
//                                                "Enter choice: ");
//
//                                switch (bookChoice) {
//                                    case 1: // Add Book
//                                        System.out.println("Enter book name, author, category:");
//                                        String title = sc.nextLine();
//                                        String author = sc.nextLine();
//                                        String category = sc.nextLine();
//                                        if (lbs.addNewBook(title, author, category)) {
//                                            System.out.println("✅ Book added successfully");
//                                        } else {
//                                            System.out.println("⚠ Problem in adding book");
//                                        }
//                                        break;
//
//                                    case 2: // View Books
//                                        ArrayList<BookModel> books = lbs.viewBooks();
//                                        for (BookModel b : books) {
//                                            System.out.println(b.toString());
//                                        }
//                                        break;
//
//                                    case 3: // Search Book
//                                        books = lbs.viewBooks();
//                                        System.out.print("Enter book name to search: ");
//                                        String skey = sc.nextLine().toLowerCase();
//                                        for (BookModel b : books) {
//                                            if (b.getTitle().toLowerCase().equals(skey)) {
//                                                System.out.println("✅ Found: " + b);
//                                            }
//                                        }
//                                        break;
//
//                                    case 4: // Delete Book
//                                        books = lbs.viewBooks();
//                                        System.out.print("Enter book name to delete: ");
//                                        String delKey = sc.nextLine().toLowerCase();
//                                        for (BookModel b : books) {
//                                            if (b.getTitle().toLowerCase().equals(delKey)) {
//                                                int confirm = getIntInput(sc,
//                                                        "Book found! Enter 1 to confirm delete: ");
//                                                if (confirm == 1) {
//                                                    if (lbs.deleteBookById(b.getBook_id())) {
//                                                        System.out.println("✅ Book deleted successfully");
//                                                    } else {
//                                                        System.out.println("⚠ Problem in deleting book");
//                                                    }
//                                                } else {
//                                                    System.out.println("❌ Book deletion cancelled");
//                                                }
//                                            }
//                                        }
//                                        break;
//                                    case 5:
//                                        books = lbs.viewBooks();
//                                        System.out.print("Enter book name to delete: ");
//                                        String updateKey = sc.nextLine().toLowerCase();
//                                        for (BookModel b : books) {
//                                            if (b.getTitle().toLowerCase().equals(updateKey)) {
//                                                System.out.println("✅ Book found successfully\n"+b);
//                                                int confirm = getIntInput(sc,
//                                                        "Book found! Enter 1 to confirm update: ");
//                                                if (confirm == 1) {
//                                                     title = sc.nextLine();
//                                                     author = sc.nextLine();
//                                                     category = sc.nextLine();
////                                                     int book_id=getIntInput(sc,"book_id");
//                                                    if (lbs.updateBook(title,author,category,b.getBook_id())) {
//                                                        System.out.println("✅ Book updated successfully\n"+b);
//                                                    } else {
//                                                        System.out.println("⚠ Problem in updating book");
//                                                    }
//                                                } else {
//                                                    System.out.println("❌ Book update cancelled");
//                                                }
//                                            }
//                                        }
//                                        break;
//                                    case 6: // Back
//                                        bookMenu = false;
//                                        break;
//
//                                    default:
//                                        System.out.println("⚠ Invalid choice! Try again.");
//                                }
//                            }
//                            break;
//
////                        case 2:
////                            System.out.println("⚠ Manage Admins not implemented yet.");
////                            break;
//
//                        case 2:
//                            ArrayList<User_Controler> userList;
//                           boolean userMainu=true;
//                           while (userMainu){
//                               int userMen=getIntInput(sc,"\"\\n---- Manage Users ----\\n\" +" +
//                                       "\ncase 1:Add User ➕\n" +
//                                       "case 2:View Users 👁️\n" +
//                                       "case 3:Delete User⭕\n" +
//                                       "case 4:Update USer✏️\n" +
//                                       "case 5:Back to Main menu 🔙");
//                               switch (userMen){
//                                   case 1:
//                                       System.out.println("enter name");
//                                       String user_name=sc.nextLine();
//                                       System.out.println("enter email");
//                                       String user_email=sc.nextLine();
//                                       System.out.println("enter password");
//                                       String user_password=sc.nextLine();
//                                        if(lbs.addUser(user_name,user_email,user_password)){
//                                            System.out.println("✅ user added successfully  ");
//                                        }else{
//                                            System.out.println("❌ not added");
//                                        }
//                                       break;
//                                   case 2:
//                                       userList=lbs.getUSer();
//                                       for(User_Controler u:userList){
//                                          if(u.getRole().equals("user")) System.out.println(u);
//                                       }
//                                       break;
//                                   case 3:
//                                       System.out.println("enter user name to search for delete");
//                                      String uname=sc.nextLine().toLowerCase();
////                                       System.out.println(uname);
//                                       userList=lbs.getUSer();
//                                       for (User_Controler u :userList){
////                                           System.out.println(u);
//
//                                           if(u.getUser_name().toLowerCase().equals(uname)){
//                                               System.out.println("user found\n"+u);
//                                               int conferm=getIntInput(sc,"are you shore to delete the enter 1 \n");
//                                               if(conferm==1){
//                                                   if(lbs.deleteUser(u.getUser_id())){
//                                                       System.out.println("user deleted");
//                                                   }else {
//                                                       System.out.println("some problem in deleting user");
//                                                   }
//                                               }
//                                           }
//                                   }
//                                       break;
//                                   case 4:
//                                       System.out.println("enter user name to search for update");
//                                        uname=sc.nextLine().toLowerCase();
////                                       System.out.println(uname);
//                                       userList=lbs.getUSer();
//                                       for (User_Controler u :userList){
////                                           System.out.println(u);
//
//                                           if(u.getUser_name().toLowerCase().equals(uname)){
//                                               System.out.println("user found\n"+u);
//                                               int conferm=getIntInput(sc,"are you shore to update  enter 1 \n");
//                                               if(conferm==1){
//                                                   System.out.println("enter name");
//                                                    user_name=sc.nextLine();
//                                                   System.out.println("enter email");
//                                                    user_email=sc.nextLine();
////                                                   System.out.println("enter password");
////                                                    user_password=sc.nextLine();
//                                                   if(lbs.updateUser(user_name,user_email,"asd",u.getUser_id())){
//                                                       System.out.println("✅ user updated successfully  ");
//                                                   }else{
//                                                       System.out.println("❌ not not");
//                                                   }
//                                               }
//                                           }
//                                       }
//                                       break;
//                                   case 5:
//                                       userMainu=false;
//                                       break;
//                                   default:
//                                       System.out.println("⚠ Invalid choice! Try again.");
//                                       break;
//
//                               }
//                           }
//                            break;
//
//                        case 3:
//                            System.out.println("👋 Exiting program...");
//                            mainMenu = false;
//                            break;
//
//                        default:
//                            System.out.println("⚠ Invalid choice! Try again.");
//                    }
//                }
//            } else if(user.getRole().equals("user")) {
//                System.out.println("✅ You are a normal user");
//                userMenue( user);
//            }
//
//        } else {
//            System.out.println(" Signup ");
//            System.out.println("enter name");
//            String user_name=sc.nextLine();
//            System.out.println("enter email");
//            String user_email=sc.nextLine();
//            System.out.println("enter password");
//            String user_password=sc.nextLine();
//            if(lbs.addUser(user_name,user_email,user_password)){
//                System.out.println("✅ user added successfully  ");
////                lbs.loginServices(user_email,user_password);
//            }else{
//                System.out.println("❌ not added");
//            }
//        }
//    }
//    public static void userMenue(User_Controler user){
//
//
//
//    }
//
//}

import Library.Model.BookModel;
import Library.Model.User_Controler;
import Library.Services.LibrarianServices;
import Library.Services.UserServices;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // ✅ Utility method for safe integer input
    public static int getIntInput(Scanner sc, String msg) {
        int num;
        while (true) {
            try {
                System.out.print(msg);
                num = sc.nextInt();
                sc.nextLine(); // clear buffer
                return num;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input! Please enter a number.");
                sc.nextLine(); // clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibrarianServices lbs = new LibrarianServices();

        int login = getIntInput(sc, "Enter 1 for login and 2 for signup: ");
        User_Controler user = null;

        if (login == 1) {
            int loginTries = 3;

            while (user == null && loginTries > 0) {
                System.out.println("Enter user email and password to login:");
                user = lbs.loginServices(sc.nextLine(), sc.nextLine());

                if (user == null) {
                    loginTries--;
                    if (loginTries > 0) {
                        System.out.println(" Wrong info! You have " + loginTries + " chances left.");
                    } else {
                        System.out.println(" Too many failed attempts. Exiting...");
                        System.exit(0);
                    }
                }
            }

            if (user != null) {
                if (user.getRole().equals("librarian")) {
                    System.out.println("✅ You are librarian");
                    librarianMenu(user, sc, lbs);
                } else if (user.getRole().equals("user")) {
                    System.out.println("✅ You are a normal user");
                    userMenu(user, sc, lbs);
                }
            }

        } else {
            System.out.println(" Signup ");
            System.out.println("enter name");
            String user_name = sc.nextLine();
            System.out.println("enter email");
            String user_email = sc.nextLine();
            System.out.println("enter password");
            String user_password = sc.nextLine();
            if (lbs.addUser(user_name, user_email, user_password)) {
                System.out.println("✅ user added successfully  ");
                user=lbs.loginServices(user_email,user_password);
                userMenu(user,sc,lbs);
            } else {
                System.out.println("❌ not added");
            }
        }
    }

    // 📚 Librarian Menu
    public static void librarianMenu(User_Controler user, Scanner sc, LibrarianServices lbs) {
        boolean mainMenu = true;
        while (mainMenu) {
            int choice = getIntInput(sc,
                    "\n---- Main Menu ----\n" +
                            "1. Manage Books 📚\n" +
                            "2. Manage Users 👥\n" +
                            "3. Exit\n" +
                            "Enter choice: ");

            switch (choice) {
                case 1: // Manage Books
                    boolean bookMenu = true;
                    while (bookMenu) {
                        int bookChoice = getIntInput(sc,
                                "\n---- Manage Books ----\n" +
                                        "1. Add Book   ➕\n" +
                                        "2. View Books 👁️\n" +
                                        "3. Search Book 🔍\n" +
                                        "4. Delete Book ⭕\n" +
                                        "5. Update Book ✏️\n" +
                                        "6. Back to Main Menu 🔙\n" +
                                        "Enter choice: ");

                        ArrayList<BookModel> books;
                        switch (bookChoice) {
                            case 1: // Add Book
                                System.out.println("Enter book name, author, category:");
                                String title = sc.nextLine();
                                String author = sc.nextLine();
                                String category = sc.nextLine();
                                if (lbs.addNewBook(title, author, category)) {
                                    System.out.println("✅ Book added successfully");
                                } else {
                                    System.out.println("⚠ Problem in adding book");
                                }
                                break;

                            case 2: // View Books
                                books = lbs.viewBooks();
                                for (BookModel b : books) {
                                    System.out.println(b.toString());
                                }
                                break;

                            case 3: // Search Book
                                books = lbs.viewBooks();
                                System.out.print("Enter book name to search: ");
                                String skey = sc.nextLine().toLowerCase();
                                for (BookModel b : books) {
                                    if (b.getTitle().toLowerCase().equals(skey)) {
                                        System.out.println("✅ Found: " + b);
                                    }
                                }
                                break;

                            case 4: // Delete Book
                                books = lbs.viewBooks();
                                System.out.print("Enter book name to delete: ");
                                String delKey = sc.nextLine().toLowerCase();
                                for (BookModel b : books) {
                                    if (b.getTitle().toLowerCase().equals(delKey)) {
                                        int confirm = getIntInput(sc,
                                                "Book found! Enter 1 to confirm delete: ");
                                        if (confirm == 1) {
                                            if (lbs.deleteBookById(b.getBook_id())) {
                                                System.out.println("✅ Book deleted successfully");
                                            } else {
                                                System.out.println("⚠ Problem in deleting book");
                                            }
                                        } else {
                                            System.out.println("❌ Book deletion cancelled");
                                        }
                                    }
                                }
                                break;

                            case 5: // Update Book
                                books = lbs.viewBooks();
                                System.out.print("Enter book name to update: ");
                                String updateKey = sc.nextLine().toLowerCase();
                                for (BookModel b : books) {
                                    if (b.getTitle().toLowerCase().equals(updateKey)) {
                                        System.out.println("✅ Book found successfully\n" + b);
                                        int confirm = getIntInput(sc,
                                                "Book found! Enter 1 to confirm update: ");
                                        if (confirm == 1) {
                                            System.out.println("Enter new title, author, category:");
                                            String newTitle = sc.nextLine();
                                            String newAuthor = sc.nextLine();
                                            String newCategory = sc.nextLine();
                                            if (lbs.updateBook(newTitle, newAuthor, newCategory, b.getBook_id())) {
                                                System.out.println("✅ Book updated successfully");
                                            } else {
                                                System.out.println("⚠ Problem in updating book");
                                            }
                                        } else {
                                            System.out.println("❌ Book update cancelled");
                                        }
                                    }
                                }
                                break;

                            case 6: // Back
                                bookMenu = false;
                                break;

                            default:
                                System.out.println("⚠ Invalid choice! Try again.");
                        }
                    }
                    break;

                case 2: // Manage Users
                    ArrayList<User_Controler> userList;
                    boolean userMenu = true;
                    while (userMenu) {
                        int userChoice = getIntInput(sc,
                                "\n---- Manage Users ----\n" +
                                        "1. Add User ➕\n" +
                                        "2. View Users 👁️\n" +
                                        "3. Delete User ⭕\n" +
                                        "4. Update User ✏️\n" +
                                        "5. Back to Main Menu 🔙\n" +
                                        "Enter choice: ");
                        switch (userChoice) {
                            case 1:
                                System.out.println("Enter name, email, password:");
                                String user_name = sc.nextLine();
                                String user_email = sc.nextLine();
                                String user_password = sc.nextLine();
                                if (lbs.addUser(user_name, user_email, user_password)) {
                                    System.out.println("✅ user added successfully");
                                } else {
                                    System.out.println("❌ user not added");
                                }
                                break;

                            case 2:
                                userList = lbs.getUSer();
                                for (User_Controler u : userList) {
                                    if (u.getRole().equals("user")) System.out.println(u);
                                }
                                break;

                            case 3:
                                System.out.print("Enter user name to delete: ");
                                String uname = sc.nextLine().toLowerCase();
                                userList = lbs.getUSer();
                                for (User_Controler u : userList) {
                                    if (u.getUser_name().toLowerCase().equals(uname)) {
                                        System.out.println("User found\n" + u);
                                        int confirm = getIntInput(sc, "Enter 1 to confirm delete: ");
                                        if (confirm == 1) {
                                            if (lbs.deleteUser(u.getUser_id())) {
                                                System.out.println("✅ User deleted");
                                            } else {
                                                System.out.println("⚠ Problem deleting user");
                                            }
                                        }
                                    }
                                }
                                break;

                            case 4:
                                System.out.print("Enter user name to update: ");
                                uname = sc.nextLine().toLowerCase();
                                userList = lbs.getUSer();
                                for (User_Controler u : userList) {
                                    if (u.getUser_name().toLowerCase().equals(uname)) {
                                        System.out.println("User found\n" + u);
                                        int confirm = getIntInput(sc, "Enter 1 to confirm update: ");
                                        if (confirm == 1) {
                                            System.out.println("Enter new name, email:");
                                            String newName = sc.nextLine();
                                            String newEmail = sc.nextLine();
                                            if (lbs.updateUser(newName, newEmail, "asd", u.getUser_id())) {
                                                System.out.println("✅ user updated successfully");
                                            } else {
                                                System.out.println("❌ user not updated");
                                            }
                                        }
                                    }
                                }
                                break;

                            case 5:
                                userMenu = false;
                                break;

                            default:
                                System.out.println("⚠ Invalid choice! Try again.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("👋 Exiting program...");
                    mainMenu = false;
                    break;

                default:
                    System.out.println("⚠ Invalid choice! Try again.");
            }
        }
    }

    // 👤 User Menu
    public static void userMenu(User_Controler user, Scanner sc, LibrarianServices lbs) {
        boolean userMenu = true;
        UserServices userServices = new UserServices();
        while (userMenu) {
            int choice = getIntInput(sc,
                    "\n---- User Menu ----\n" +
                            "1. View Available Books 👁️\n" +
                            "2. Search Book for borrow 🔍\n" +
                            "3. view Book you borrow 🔍\n" +
                            "4. Return Book you borrow 🔍\n" +
                            "5. Exit 🔙\n" +
                            "Enter choice: ");

            switch (choice) {
                case 1:
                    ArrayList<BookModel> books = lbs.viewBooks();
                    for (BookModel b : books) {
                        if(b.isAvalable()) System.out.println(b);
                    }
                    break;

                case 2:
                    System.out.print("Enter book name to search for borrow: ");
                    String skey = sc.nextLine().toLowerCase();
                    books = lbs.viewBooks();
                    for (BookModel b : books) {
                        if (b.getTitle().toLowerCase().equals(skey)) {
                            System.out.println("✅ Found: " + b);
                            if (b.isAvalable()) {
                                System.out.println("book is avalable ✅");
                                int conferm = getIntInput(sc, "are you shore to borrow book then enter 1\n");
                                if (conferm == 1) {
                                    System.out.println(b.getTitle());
                                    if (userServices.issueBook(
                                            user.getUser_id(),
                                            b.getBook_id(),
                                            b.getTitle(),
                                            b.getAuthor(),
                                            b.getCatagory(),
                                            true   // always true when issuing
                                    )) {
                                        System.out.println("\n\n book issued successfully 🎉🎉\n");
                                    } else {
                                        System.out.println("book not issued 😔 sorry ");
                                    }

                                }
                            } else System.out.println("book not available ❌");
                        }
                    }
                    break;
                case 3:
                    ArrayList<BookModel> borrowedBooks = userServices.getBooksYouIssued(user.getUser_id());
                    if (borrowedBooks != null) {

                        for (BookModel b : borrowedBooks) {
                            System.out.println(b);
                        }
                    } else {
                        System.out.println(" you dont borrowed any book");
                    }
                    break;
                case 4:
                     borrowedBooks = userServices.getBooksYouIssued(user.getUser_id());
                    System.out.print("Enter book name to search for borrow: ");
                     skey = sc.nextLine().toLowerCase();
                    books = lbs.viewBooks();
                    for (BookModel b : borrowedBooks) {
                        if (b.getTitle().toLowerCase().equals(skey)) {
                            System.out.println("✅ Found: " + b);
                            if (!b.isAvalable()) {
                                System.out.println("book is borrowed by you ✅");
                                int conferm = getIntInput(sc, "are you shore to return book then enter 1\n");
                                if (conferm == 1) {
                                    if (userServices.returenBook(
                                            user.getUser_id(),
                                            b.getBook_id(),
                                            b.getTitle(),
                                            b.getAuthor(),
                                            b.getCatagory(),
                                            false   // always false when returning
                                    )) {
                                        System.out.println("book returned successfully 🎉🎉");
                                    } else {
                                        System.out.println("book not returned ❌");
                                    }

                                }
                            } else System.out.println("book not available ❌");
                        }
                    }
                    break;
                case 5:
                    System.out.println("👋 Exiting user menu...");
                    userMenu = false;
                    break;

                default:
                    System.out.println("⚠ Invalid choice! Try again.");
            }
        }
    }
}

