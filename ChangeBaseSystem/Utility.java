package package2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Utility {
    public static final String REGEX_CODE = "[a-zA-Z]+";
    public static final String REGEX_NAME = "[a-zA-Z ]+";
    
    public static String checkInputString(String message, String regex, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message + ": ");
            String input = scanner.nextLine().trim();
            if(input.isEmpty()) {
                System.out.println("Input cannot be empty");
                continue;
            }
            if (input.matches(regex)) {
                return input;
            }
            else {
                System.err.println(errorMessage);
            }
        }
    }
    
    public static int checkInputInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message + ": ");
                String input = scanner.nextLine().trim();
                if(input.isEmpty()) {
                    System.out.println("Input cannot be empty");
                    continue;
                }
                int intNumber = Integer.parseInt(input);
                if(intNumber >= 1 && intNumber <= 3) {
                    return intNumber;
                }
                else {
                    System.err.println("Invalid choice. Please enter 1, 2, or 3");
                }
            }
            catch (Exception e) {
                System.err.println("Input must be a valid number");
            }
        }
    }
}