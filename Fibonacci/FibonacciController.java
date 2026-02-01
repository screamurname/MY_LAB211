package Fibonacci4;

class FibonacciController {

    private final FibonacciModel model;
    private final FibonacciView view;

    public FibonacciController(FibonacciModel model, FibonacciView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        // Bước 1: Gọi View để lấy số lượng người dùng muốn
        int maxNumber = view.inputNumberOfFibonacci();
        
        // Bước 2: In tiêu đề
        view.displayFibonacciSeries(maxNumber);
        
        // Bước 3: Kiểm tra các trường hợp đặc biệt (Điều hướng)
        if (maxNumber == 0) {
            return; // Nếu nhập 0 thì kết thúc luôn, không làm gì cả
        } else if (maxNumber == 1) {
            view.displayBaseCases(1); // Gọi View in số "0"
        } else if (maxNumber == 2) {
            view.displayBaseCases(2); // Gọi View in "0  1"
        } else {
            // Bước 4: Trường hợp > 2 số, kết hợp View và Model
            view.displayBaseCases(2); // In trước "0  1" từ View
            System.out.print("  "); 

            // Kích hoạt đệ quy để in các số từ số thứ 3 trở đi
            // maxNumber - 3: Vì đã in 2 số ở View và Model sẽ in thêm 1 số ở n=0
            // lower=1, higher=1: Số thứ 2 (1) và số thứ 3 (1) của dãy
            model.FibonacciRecursive(maxNumber - 3, 1, 1);
        }
    }
}
