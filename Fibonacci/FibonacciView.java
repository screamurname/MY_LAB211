package Fibonacci4;

import java.util.Scanner;

/**
 *
 * @author admin
 */
class FibonacciView {

    void displayFibonacciSeries(int maxNumber) {
        System.out.print("Fibonacci Series of " + maxNumber + " numbers: ");
    }

    void displayBaseCases(int maxNumber) {
        if (maxNumber == 1) {
            System.out.print("0");
        } else if (maxNumber >= 2) {
            System.out.print("0  1");
        }
    }

    int inputNumberOfFibonacci() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the number of Fibonacci: ");
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    System.err.println("Input cannot be empty");
                } else {
                    int number = Integer.parseInt(input);
                    //vượt quá giá trị mà kiểu Long có thể lưu trữ
                    if (number >= 0 && number <= 92) {
                        return number;
                    }
                    if (number >= 93) {
                        System.out.println("The system can't display because the numbers too large. Please enter smaller numbers");
                    }

                }

            } catch (Exception e) {
                System.err.println("input must be a positive number and not too large");
            }
        }
    }

}
