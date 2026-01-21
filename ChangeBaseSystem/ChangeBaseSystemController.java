package package2;

class ChangeBaseSystemController {
    private final ChangeBaseSystemModel model;
    private final ChangeBaseSystemView view;

    public ChangeBaseSystemController(ChangeBaseSystemModel model, ChangeBaseSystemView view) {
        this.model = model;
        this.view = view;
    }
    
    public void run() {
        System.out.println("=== BASE CONVERSION SYSTEM ===");
        
        // Nhập base đầu vào (1: binary, 2: decimal, 3: hexadecimal)
        int baseInput = view.getInput();
        
        // Nhập base đầu ra (1: binary, 2: decimal, 3: hexadecimal)
        int baseOutput = view.getOutput();
        
        // Nhập giá trị đầu vào
        String value = view.getValue(baseInput);
        
        try {
            // Chuyển đổi cơ số
            String finalResult = model.ConvertBase(baseInput, baseOutput, value);
            
            // Hiển thị kết quả
            view.displayResult(value, baseInput, baseOutput, finalResult);
        } catch (Exception e) {
            System.err.println("Error during conversion: " + e.getMessage());
        }
    }
}