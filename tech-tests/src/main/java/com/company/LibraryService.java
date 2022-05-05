package com.company;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryService {
    com.company.CsvRepository repo = new com.company.CsvRepository();
    ArrayList<Book> currentBookList = new ArrayList<Book>();

    public LibraryService() throws IOException {
        this.currentBookList = repo.readFromCSV("/Users/Josie/java-tech-tests/tech-tests/data/books_data.csv");
    }
    public void writeCurrentLibrary(){
        ArrayToJson.getJsonLibrary(currentBookList);
    }
//
    public boolean returnBook(String title){
         Book currentBook = findBook(title);
         if(currentBook != null && currentBook.isLoaned()){
             currentBook.setLoaned(false);
             return true;
         }
         return false;
     }
     public boolean loanBook(String title) {
        Book currentBook = findBook(title);
        if(currentBook != null && !currentBook.isLoaned()){
            currentBook.setLoaned(true);
            return true;
        }
        return false;
     }
     private Book findBook(String title){
         for(Book book: currentBookList) {
             if(book.getTitle().equals(title)) {
                 return book;
             }
         }
         return null;
     }
//
//    private Book findBook(){
//        Book book = new Book();
//        return book;
//    }
}
