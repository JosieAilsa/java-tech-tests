package com.company;

import java.io.*;
import java.util.ArrayList;

public class CsvRepository {


   public ArrayList<Book> readFromCSV(String filePath)  {
       try {
           ArrayList<Book> currentBooks = new ArrayList<Book>();
           File file = new File (filePath);
           BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
           if (bufferedReader.readLine() == null) {
               System.out.println("Nothing here");
               throw new IOException();
           }
           String eachLine;
           while((eachLine = bufferedReader.readLine()) != null){
               String[] values = eachLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
               try {
                   int iD =  Integer.parseInt(values[0]);
                   String removeQuotes =  values[1].replaceAll("\"", "");
                   Book newBook = new Book(iD,removeQuotes,values[2],values[3],values[4], values[5]);
                   currentBooks.add(newBook);
               } catch(NumberFormatException exception){
                   continue;
               }
           }
           return currentBooks;
       } catch(IOException e){
           e.printStackTrace();
           System.out.println("there's been a problem! ");
       }
       return null;
       }

}
