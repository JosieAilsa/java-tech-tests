package com.company;
import com.company.JSON.LibraryJSONRepo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

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
    public Book returnBook(String title){
         Book currentBook = findBook(title, currentBookList);
         if(currentBook != null && currentBook.isLoaned()){
             currentBook.setLoaned(false);
             return currentBook;
         }
         return null;
     }
     public Book loanBook(String title) {
        Book currentBook = findBook(title, currentBookList);
        if(currentBook != null && !currentBook.isLoaned()){
            currentBook.setLoaned(true);
            writeCurrentLibrary();
            return currentBook;
        }
        return null;
     }

     public Book findBook(String requestedTitle, ArrayList<Book> currentBooks){
         for(Book book: currentBooks) {
             String currentTitle = book.getTitle().toLowerCase(Locale.ROOT);
             if(currentTitle.equals(requestedTitle)) {
                 writeCurrentLibrary();
                 return book;
             }
         }
         return null;
     }
}
