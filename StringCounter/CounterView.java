

import java.util.Map;
import java.util.Scanner;

public class CounterView {
    private final Scanner scanner = new Scanner(System.in);

    // Hàm lấy input và validate
    public String getInput() {
        while (true) {
            System.out.println("Enter your content: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("Content cannot be empty. Please try again!");
                continue;
            }
            return input;
        }
    }

    public void displayResult(Map<String, Integer> wordMap, Map<Character, Integer> charMap) {
        System.out.println("Word count: " + wordMap);
        System.out.println("Character count: " + charMap);
    }
}