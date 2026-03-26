package MariosPizzaBar.util;
import MariosPizzaBar.util.Color;

import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.io.IOException;


public class ErrorHandler {

    //metoder til at håndtere alle fejl, delt op i forskellige metoder alt efter hvilke errors der skal handles.

    public static void handleErrors(Exception e) {
        if (e instanceof IOException) {
            System.out.println(Color.RED + "Fejl ved input/output" + Color.RESET);

        } else {
            System.out.println(Color.RED + "Uventet fejl opstod" + Color.RESET);
        }
    }


    public static void handleInputErrors(Exception e) {
        if (e instanceof InputMismatchException) {
            System.out.println(Color.RED + "Fejl, indtast gyldig værdi" + Color.RESET);

        } else if (e instanceof NumberFormatException) {
            System.out.println(Color.RED + "Forkert input, prøv igen" + Color.RESET);

        } else if (e instanceof IllegalArgumentException) {
            System.out.println(Color.RED + "Forkert input" + Color.RESET);
        }
    }


    public static void handleNullException(Exception e) {
        if (e instanceof NullPointerException) {
            System.out.println(Color.RED + "Fejl, null værdi fundet" + Color.RESET);
        }
    }


    public static void handleArrayException(Exception e) {
        if (e instanceof ArrayIndexOutOfBoundsException) {
        }
    }

    //metode til at håndtere file errors
    public static void handlefileErrors(Exception e) {
        if (e instanceof FileReadException) {
            System.out.println(Color.RED + "Fejl ved læsning af fil" + Color.RESET);

        } else if (e instanceof FileWriteException) {
            System.out.println(Color.RED + "Fejl ved skrivning af fil" + Color.RESET);
        }
    }

    //vores custom exceptions til file reading og file writing
    public static class FileReadException extends Exception {
        public FileReadException(String message) {
            super(message);
        }
    }

    public static class FileWriteException extends Exception {
        public FileWriteException(String message) {
            super(message);
        }
    }
}
