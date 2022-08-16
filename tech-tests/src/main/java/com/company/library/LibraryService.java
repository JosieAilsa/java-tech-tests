package com.company.library;
import com.company.Book;
import com.company.JSON.LibraryJSONRepo;
import com.company.frontend.Colour;
import com.company.utils.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class LibraryService {
    public ArrayList<Book> currentBookList = new ArrayList<Book>();

    public ArrayList<Book> getCurrentBookList() {
        return currentBookList;
    }

    public LibraryService() throws IOException {
        //See if there is a JSON file that has been created
        try{
            // If so read the current book list from JSON
            this.currentBookList = LibraryJSONRepo.readJSONLibrary(FileUtils.getRelativeFilePath() + "/src/data/library_output.json");
        } catch(Exception e) {
            //Else read from original CSV
            com.company.CsvRepository repo = new com.company.CsvRepository();
            this.currentBookList = repo.readFromCSV(FileUtils.getRelativeFilePath() +"/src/data/books_data.csv");
        }
    }

    void writeCurrentLibrary(){
        LibraryJSONRepo.writeJSONArrayOfBooks(this.currentBookList, FileUtils.getRelativeFilePath() + "/src/data/library_output.json");
    }
    //
    Book returnBook(String title){
        Book currentBook = findBook(title,this.currentBookList);
        if(currentBook != null) {
            updateBookList(currentBook,this.currentBookList,false);
            return currentBook;
        }
        return null;
    }
    Book loanBook(String title) {
        Book currentBook = findBook(title, this.currentBookList);
        if(currentBook.isLoaned()){
            System.out.println(Colour.red("That book is currently on loan."));
            return null;
        }
        if(currentBook != null){
            updateBookList(currentBook,this.currentBookList, true);
            return currentBook;
        }
        System.out.println(Colour.red("Sorry, we don't stock that book."));
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

    private void updateBookList( Book bookToUpdate, ArrayList<Book> currentBookList, boolean isOnLoan) {
        int bookIndex = currentBookList.indexOf(bookToUpdate);
        bookToUpdate.setLoaned(isOnLoan);
        currentBookList.set(bookIndex,bookToUpdate);
        this.currentBookList = currentBookList;
        writeCurrentLibrary();
    }

    private Book findBook(String requestedTitle, ArrayList<Book> currentBooks){
        for(Book book: currentBooks) {
            String currentTitle = book.getTitle().toLowerCase(Locale.ROOT);
            if(currentTitle.equals(requestedTitle.toLowerCase(Locale.ROOT))) {
                return book;
            }
        }
        return null;
    }

}
