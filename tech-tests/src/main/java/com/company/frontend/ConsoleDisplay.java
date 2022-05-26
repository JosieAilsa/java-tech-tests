package com.company.frontend;

import com.github.lalyos.jfiglet.FigletFont;
import java.io.Console;
import java.io.IOException;
import java.util.*;

public abstract class ConsoleDisplay {

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

    public static ArrayList<String> start() {
        try {
            ConsoleDisplay.displayFigletText("Welcome to the library");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> userInputs = new ArrayList<String>();
        int option = 0;
        while (option != 1 || option != 2) {
            ConsoleDisplay.welcomeMessage();
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (option == 1) {
                userInputs = ConsoleDisplay.getUserLogInDetails();
                break;

            }
            if (option == 2) {
                userInputs = ConsoleDisplay.createAccount();
                break;
            }
            System.out.println(Colour.red("Whoops, please press 1 or 2!"));
        }
        return userInputs;
    }



}
