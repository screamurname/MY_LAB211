package Fibonacci4;

import java.util.Scanner;

class FibonacciView {

    // In dòng tiêu đề thông báo số lượng số Fibo sẽ in
    void displayFibonacciSeries(int maxNumber) {
        System.out.print("Fibonacci Series of " + maxNumber + " numbers: ");
    }

    // Hàm in các số đầu tiên (Base Cases) để làm nền cho đệ quy
    void displayBaseCases(int maxNumber) {
        if (maxNumber == 1) {
            System.out.print("0"); // Nếu chỉ cần 1 số, in số đầu tiên là 0
        } else if (maxNumber >= 2) {
            System.out.print("0  1"); // Nếu từ 2 số trở lên, in "0  1"
        }
    }

    // Hàm nhập liệu "vĩnh cửu" - chỉ thoát khi nhập đúng số hợp lệ
    int inputNumberOfFibonacci() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the number of Fibonacci: ");
                // Đọc cả dòng, xóa khoảng trắng thừa để tránh lỗi nhập liệu
                String input = scanner.nextLine().trim(); 
                
                if(input.isEmpty()) {
                    System.err.println("input cannot be empty");
                    continue; // Quay lại yêu cầu nhập nếu để trống
                }

                int number = Integer.parseInt(input); // Thử chuyển chuỗi sang số
                
                // Giới hạn 92 vì kiểu long không thể chứa số Fibo thứ 93 trở đi
                if (number >= 0 && number <= 92) {
                     return number; // Nhập đúng thì thoát hàm và trả về giá trị
                } else if (number >= 93) {
                    System.out.println("The system can't display because the numbers too large.");
                } else {
                    System.err.println("Please enter a positive number.");
                }
            }
            catch (NumberFormatException e) {
                // Nếu Integer.parseInt thất bại (do nhập chữ), nhảy vào đây
                System.err.println("Invalid input! Please enter a number, not text.");
            }
        }
    }
}
