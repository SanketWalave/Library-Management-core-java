package Library.Model;

public class BookModel {
    private int book_id;
    private String  title;
    private String  author;
    private String  catagory;
    private boolean  avalable;

    public BookModel(int book_id, String title, String author, String catagory, boolean avalable) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.catagory = catagory;
        this.avalable = avalable;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public boolean isAvalable() {
        return avalable;
    }

    public void setAvalable(boolean avalable) {
        this.avalable = avalable;
    }

    @Override
    public String toString() {
        return String.format(
                "[Book ID: %d]  Title: %-20s  Author: %-15s  Category: %-10s  Available: %s",
                book_id, title, author, catagory, (avalable ? "✅ Yes" : "❌ No")
        );
    }

}
