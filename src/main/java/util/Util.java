package util;

import java.util.Scanner;

public class Util {

    private Util() {
    }

    public static String readFromUser(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }

}
