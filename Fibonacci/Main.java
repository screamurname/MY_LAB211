package Fibonacci4;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo các thành phần
        FibonacciModel model = new FibonacciModel();
        FibonacciView view = new FibonacciView();
        
        // Kết nối chúng qua Controller
        FibonacciController controller = new FibonacciController(model, view);
        
        // Chạy chương trình
        controller.run();
    }
}
