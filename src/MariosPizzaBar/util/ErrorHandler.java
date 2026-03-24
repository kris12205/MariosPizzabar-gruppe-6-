package MariosPizzaBar.util;

import java.util.InputMismatchException;
import java.io.IOException;


public class ErrorHandler {

    //metode til at håndtere disse fejl
    public static void handleErrors(Exception e) {
        if (e instanceof IOException) {
            System.out.println("Fejl ved input/output");

        } else if (e instanceof NumberFormatException) {
            System.out.println("Forkert input, prøv igen");

        } else {
            System.out.println("Uventet fejl opstod");
        }
    }


    public static void handeInputErrors(Exception e) {
        if (e instanceof InputMismatchException) {
            System.out.println("Fejl, indtast gyldig værdi");
        }
    }


    public static void handleNullException(Exception e) {
        if (e instanceof NullPointerException) {
            System.out.println("Fejl, null værdi fundet");
        }
    }


    public static void handleArrayException(Exception e) {
        if (e instanceof ArrayIndexOutOfBoundsException) {
        }
    }

    //metode til at håndtere file errors
    public static void handlefileErrors(Exception e) {
        if (e instanceof FileReadException) {
            System.out.println("Fejl ved læsning af fil");

        } else if (e instanceof FileWriteException) {
            System.out.println("Fejl ved skrivning af fil");
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
