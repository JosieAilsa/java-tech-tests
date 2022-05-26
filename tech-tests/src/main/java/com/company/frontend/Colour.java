package com.company.frontend;

public class Colour {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001b[31;1m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREY = "\u001B[37m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED_BACKGROUND = "\u001b[41;1m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001b[44;1m";
    private static boolean useColour = true;
    public static boolean isUseColour() {
        return useColour;
    }
    public static void setUseColour(boolean useColour) {
        Colour.useColour = useColour;
    }

    public static String blue(Object o) {
        return print(ANSI_BLUE, o);
    }
    public static String cyan(Object o) {
        return print(ANSI_CYAN, o);
    }
    public static String green(Object o) {
        return print(ANSI_GREEN, o);
    }
    public static String grey(Object o) {
        return print(ANSI_GREY, o);
    }
    public static String purple(Object o) {
        return print(ANSI_PURPLE, o);
    }
    public static String red(Object o) {
        return print(ANSI_RED, o);
    }
    public static String white(Object o) {
        return print(ANSI_WHITE, o);
    }
    public static String yellow(Object o) {
        return print(ANSI_YELLOW, o);
    }

    public static String redBg(Object o) {
        return print(ANSI_RED_BACKGROUND, o);
    }
    public static String blueBg(Object o) {
        return print(ANSI_BLUE_BACKGROUND, o);
    }

    private static String print(String colour, Object o) {
        if( o == null ) {
            return "";
        }
        if( (o instanceof String) && ((String)o).equals("") ) {
            return "";
        }
        if( !useColour ) {
            return o.toString();
        }
        return String.format("%s%s%s", colour, o.toString(), ANSI_RESET);
    }

}