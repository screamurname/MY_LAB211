


public class CounterController {
    private CounterModel model;
    private CounterView view;

    public CounterController(CounterModel model, CounterView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        // 1. Lấy dữ liệu từ View (đã được validate)
        String content = view.getInput();
        
        // 2. Chuyển dữ liệu cho Model xử lý
        model.analyze(content);
        
        // 3. Lấy kết quả từ Model và gửi cho View hiển thị
        view.displayResult(model.getWordCounter(), model.getCharCounter());
    }
}