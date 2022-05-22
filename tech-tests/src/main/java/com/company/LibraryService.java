package com.company;
import com.company.JSON.LibraryJSONRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryService {
    ArrayList<Book> currentBookList = new ArrayList<Book>();

    public LibraryService() throws IOException {
        File file = null;
        //See if there is a JSON file that has been created
        try{
            // If so read the current book list from JSON
            this.currentBookList = LibraryJSONRepo.readJSONLibrary("/Users/Josie/java-tech-tests/tech-tests/data/library_output.json");
        } catch(Exception e) {
            //Else read from original CSV
            com.company.CsvRepository repo = new com.company.CsvRepository();
            this.currentBookList = repo.readFromCSV("/Users/Josie/java-tech-tests/tech-tests/data/books_data.csv");
        }
    }
    public void writeCurrentLibrary(){
        LibraryJSONRepo.createJSONArrayOfBooks(currentBookList);
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
}
