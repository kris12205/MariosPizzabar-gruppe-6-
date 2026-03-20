package MariosPizzaBar.util;

import java.util.InputMismatchException;
import java.io.IOException;


public class ErrorHandler {

    //metode til at håndtere disse fejl
    public static void normalErrors(Exception e) {
        if (e instanceof InputMismatchException) {
            System.out.println("Fejl, indtast gyldig værdi");
            System.out.println(e.getMessage());

        } else if (e instanceof IOException) {
            System.out.println("Fejl ved input/output");
            System.out.println(e.getMessage());

        } else if (e instanceof NullPointerException) {
            System.out.println("Fejl, null værdi fundet");
            System.out.println(e.getMessage());

        } else {
            System.out.println("Uventet fejl opstod");
            System.out.println(e.getMessage());
        }
    }

    //metode til at håndtere file errors
    public static void fileErrors(Exception e) {
        if (e instanceof FileReadException) {
            System.out.println("Fejl ved læsning af fil");
            System.out.println(e.getMessage());
        } else if (e instanceof FileWriteException) {
            System.out.println("Fejl ved skrivning af fil");
            System.out.println(e.getMessage());
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
