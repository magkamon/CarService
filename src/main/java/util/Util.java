package util;

import java.util.Scanner;

public class Util {

    private static final String USER_CONFIRMATION = "Potwierdź: T/N";

    private Util() {
    }

    public static String readFromUser(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }

    public static boolean readUserConfirmation(String message) {
        System.out.println(message);
        String userConfirmation = Util.readFromUser(USER_CONFIRMATION);
        return userConfirmation.equalsIgnoreCase("T");
    }

    public static boolean isNumber(String number){
        return number.matches("[0-9]+");
    }
}
