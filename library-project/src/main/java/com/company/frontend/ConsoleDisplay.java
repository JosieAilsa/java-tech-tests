package com.company.frontend;

import com.company.Book;
import com.company.utils.ObjectUtils;
import com.github.lalyos.jfiglet.FigletFont;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public abstract class ConsoleDisplay {

    public static ArrayList<String> start() {
        displayWelcome();
        return getUserDetails();
    }
    public static int getMainMeuOption(){
        int option = 0;
        do {
            System.out.println(
                    Colour.yellow("What would you like to do: " +
                            newLine) +
                            Colour.blue("1) Loan a book" +
                                    newLine + "2) Return a book" +
                                    newLine + "3) See all your loaned books" +
                                    newLine + "4) Log out & exit"
                            )
            );
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()){
                option = scanner.nextInt();
            }
            if((option != 1) && (option != 2) && (option != 3) && (option != 4)){
                errorMessaage("Please select a valid option");
                continue;
            }
        }while(option < 1 && option > 4);
        return option;
    }

    public static int getAdminMainMenuOptions(){
        int option = 0;
        do {
            System.out.println(
                    Colour.yellow("What would you like to do: " +
                            newLine) +
                            Colour.blue("1) Loan a book" +
                                    newLine + "2) Return a book" +
                                    newLine + "3) See all your loaned books" +
                                    newLine + "4) See all the books currently on loan" +
                                    newLine + "5) Print library CSV report" +
                                    newLine + "6) Log out & exit"
                            )
            );
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()){
                option = scanner.nextInt();
            }
            if((option != 1) && (option != 2) && (option != 3) && (option != 4) && (option != 5) && (option != 6)){
                errorMessaage("Please select a valid option");
                continue;
            }
        }while(option < 1 && option > 7);
        return option;
    }

    public static String getInputFromMessage(String question){
        System.out.println(Colour.blue(question));
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        return string;
    }
    public static String requestBookTitle(String string){
        String bookTitle = ConsoleDisplay.getInputFromMessage(string);
        return bookTitle.toLowerCase(Locale.ROOT).trim();
    }
    public static boolean checkIsAdmin (){
        while(true) {
            System.out.println(Colour.blue("Are you an admin? Type Y or N"));
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().replaceAll("\\s+", "").toLowerCase(Locale.ROOT);
            if (input.equals("y")) {
                return true;
            }
            if (input.equals("n")) {
                return false;
            }
        }
    }

    public static Formatter createTwoColumnBookTable(ArrayList<Book> listOfBooks){
        Formatter fmt = new Formatter();
        fmt.format("%-25s %-25s", "Book", "#Times Loaned");
        for( Book currentBook : listOfBooks){
            String numberOfLoans = Integer.toString(currentBook.getLoanCount());
            fmt.format("%-25s %-25s %-25s\n",currentBook.getTitle() , numberOfLoans);
        }
        return fmt;
    }

    public static Formatter createFiveColumnBookTable(ArrayList<Book> listOfBooks ){
        Formatter fmt = new Formatter();
        fmt.format("%-5s %-65s %-35s %-15s %-25s %-15s %-15s %-15s\n", "Id", "Book", "Author", "Genre","#Sub Genre","Publisher","# Times Loaned", "Is On Loan");
        for( Book currentBook : listOfBooks){
            Map<String, String> bookMap = ObjectUtils.beanProperties(currentBook);
//            fmt.format("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s\n",properties.get(0),properties.get(1), properties.get(2), properties.get(3), properties.get(4), properties.get(5), properties.get(6),properties.get(7));
          fmt.format("%-5s %-65s %-35s %-15s %-25s %-15s %-15s %-15s\n",bookMap.get("id"),bookMap.get("title"),bookMap.get("author"), bookMap.get("genre"), bookMap.get("subGenre"), bookMap.get("publisher"), bookMap.get("loanCount"),bookMap.get("isLoaned"));

        }
        return fmt;
    }


    private static String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.

    private static void displayFigletText(String string) throws IOException {
        String nameFiglet = FigletFont.convertOneLine(string);
        System.out.println(Colour.blue(nameFiglet));
    }

    private static void welcomeMessage() {
        System.out.println(Colour.white(
                "Would you like to:") + newLine +
                Colour.blue("1) Log in") + newLine +
                Colour.blue("2) Create an account") + newLine +
                Colour.white(
                        "Hit 1 or 2 to select"));
    }

    private static ArrayList<String> getUserLogInDetails(){
        String userName = getUserInputString("Enter your username");
        String password = getUserInputString("Enter your password");
        return new ArrayList<String>(Arrays.asList(userName,password));

    }

    private static String getUserInputString(String phrase){
        System.out.println(phrase);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().replaceAll("\\s+","");
    }

    private static ArrayList<String> createAccount(){
        String newFirstName = getUserInputString("What's your first name?");
        String newSecondName = getUserInputString("What's your second name? ");
        String newUserName = getUserInputString("Create a new username");
        String newPassword = getUserInputString("Create a new password");
        return new ArrayList<String>(Arrays.asList(newFirstName, newSecondName, newUserName, newPassword));
    }


    public static void welcomeMessage(String firstname){
        System.out.println(Colour.green(" Hi, " +  firstname + ". Welcome back!"));
    }

    public static void errorMessaage(String error){
        System.out.println(Colour.red(error));
    }

//    private static ArrayList<String> getAllProperties(Book currentBook){
//        H <Object> allProperties = ObjectUtils.getAllGetters(currentBook);
//        ArrayList<String> stringProperties = new ArrayList<>();
//        for(int i = allProperties.size()-1; i >= 0; i--) {
//            Object value = allProperties.get(i);
//            stringProperties.add(value.toString());
//        }
//        return stringProperties;
//    }

    private static void displayWelcome(){
        try {
            ConsoleDisplay.displayFigletText("Nology Library");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getUserDetails(){
        ArrayList<String> userInputs = new ArrayList<String>();
        int option = 0;
        while (option != 1 || option != 2) {
            ConsoleDisplay.welcomeMessage();
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()){
               option = scanner.nextInt();
            } else {
                errorMessaage("Please enter either 1 or 2");
            };
            if (option == 1) {
                userInputs = ConsoleDisplay.getUserLogInDetails();
                break;

            }
            if (option == 2) {
                userInputs = ConsoleDisplay.createAccount();
                break;
            }
            errorMessaage("Please enter either 1 or 2");
        }
        return userInputs;
    }


}
