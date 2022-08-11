package com.company;
import com.company.JSON.LibraryJSONRepo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class LibraryService {
    public ArrayList<Book> currentBookList = new ArrayList<Book>();

    public ArrayList<Book> getCurrentBookList() {
        return currentBookList;
    }

    public void setCurrentBookList(ArrayList<Book> currentBookList) {
        this.currentBookList = currentBookList;
    }

    public LibraryService() throws IOException {
        //See if there is a JSON file that has been created
        try{
            // If so read the current book list from JSON
            this.currentBookList = LibraryJSONRepo.readJSONLibrary("/Users/Josie/java-tech-tests/tech-tests/data/library_output.json");
        } catch(Exception e) {
            //Else read from original CSV
            System.out.println("Caught!");
            com.company.CsvRepository repo = new com.company.CsvRepository();
            this.currentBookList = repo.readFromCSV("tech-tests/data/books_data.csv");
            throw new IOException();
        }
    }
    public void writeCurrentLibrary(){
        LibraryJSONRepo.writeJSONArrayOfBooks(currentBookList);
    }
//
     Book returnBook(String title){
         Book currentBook = findBook(title, currentBookList);
         if(currentBook != null && currentBook.isLoaned()){
             currentBook.setLoaned(false);
             return currentBook;
         }
         return null;
     }


     Book loanBook(String title, ArrayList<Book> currentBookList) {
        Book currentBook = findBook(title, currentBookList);
        int index = currentBookList.indexOf(currentBook);
        if(currentBook != null && !currentBook.isLoaned()){
            currentBook.setLoaned(true);
            currentBookList.set(index,currentBook);
            this.currentBookList = currentBookList;
            writeCurrentLibrary();
            return currentBook;
        }
        return null;
     }

     public Book findBook(String requestedTitle, ArrayList<Book> currentBooks){
         for(Book book: currentBooks) {
             String currentTitle = book.getTitle().toLowerCase(Locale.ROOT);
             if(currentTitle.equals(requestedTitle.toLowerCase(Locale.ROOT))) {
                 return book;
             }
         }
         return null;
     }
    public Book findBook(Integer id){
        for(Book book: currentBookList) {
            Integer currentId = book.getId();
            if(currentId.equals(id)) {
                return book;
            }
        }
        return null;
    }
}
