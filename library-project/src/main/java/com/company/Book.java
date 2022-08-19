package com.company;

public class Book {
    private String title;
    private int id;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;
    private boolean isLoaned;
    private int loanCount;

    public Book(int id,String title, String author, String genre, String subGenre, String publisher) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.isLoaned = false;
        this.loanCount = 0;
    }

    public Book(int id, String title, String author, String genre, String subGenre, String publisher, boolean loaned, int loanCount) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.isLoaned = loaned;
        this.loanCount = loanCount;
    }

    public boolean getIsLoaned() {
        return isLoaned;
    }


    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", subGenre='" + subGenre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isLoaned=" + isLoaned +
                '}';
    }
}
