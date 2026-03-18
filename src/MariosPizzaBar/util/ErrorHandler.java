package MariosPizzaBar.util;

import java.util.InputMismatchException;
import java.io.IOException;


public class ErrorHandler {

    //metode til at håndtere fejl alle vores fejl der kunne forekomme
    public static void errorHandle(Exception e) {
        if (e instanceof InputMismatchException) {
            System.out.println("Fejl, indtast gyldig værdi");
            System.out.println(e.getMessage());
        } else if (e instanceof IOException) {
            System.out.println("Fejl ved filen");
            System.out.println(e.getMessage());
        } else if (e instanceof NullPointerException) {
            System.out.println("Fejl, null værdi fundet");
            System.out.println(e.getMessage());
        } else if (e instanceof FileReadException) {
            System.out.println("Fejl ved læsning af fil");
            System.out.println(e.getMessage());
        } else if (e instanceof FileWriteException) {
            System.out.println("Fejl ved skrivning af filen");
            System.out.println(e.getMessage());
        } else {
            System.out.println("Unexpected error occurred");
            System.out.println(e.getMessage());
        }
    }
     //her vores custom exceptions til file reading og file writing
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
