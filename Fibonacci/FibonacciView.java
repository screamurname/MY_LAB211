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
            String input = scanner.nextLine().trim(); // Dùng trim() để xóa khoảng trắng thừa
            
            if(input.isEmpty()) {
                System.err.println("input cannot be empty");
                continue; // Quay lại đầu vòng lặp
            }

            int number = Integer.parseInt(input); // Nếu là chữ, sẽ nhảy xuống catch ngay
            
            if (number >= 0 && number <= 92) {
                 return number; // Chỉ thoát hàm khi dữ liệu hợp lệ
            } else if (number >= 93) {
                System.out.println("The system can't display because the numbers too large.");
            } else {
                System.err.println("Please enter a positive number.");
            }
        }
        catch (NumberFormatException e) {
            // Bắt đúng loại lỗi nhập chữ
            System.err.println("Invalid input! Please enter a number, not text.");
        }
    }
}
}

