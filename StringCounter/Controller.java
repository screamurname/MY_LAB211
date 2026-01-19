
public class Controller {
    /**
 * Controller điều phối giữa Model và View
 */
    private final Model model;
    private final View view;
    
    /**
     * Constructor khởi tạo Model và View
     * @param model Đối tượng Model
     * @param view Đối tượng View
     */
    public Controller(Model model,View view) {
        this.model = model;
        this.view = view;
    }
    
    /**
     * Xử lý toàn bộ quy trình
     */
    public void process() {
        try {
            // Bước 1: Nhập dữ liệu
            String input = view.getInput();
            
            // Bước 2: Validate input
            if (!validateInput(input)) {
                view.displayResults(new Model.WordResult[0], 
                                   new Model.CharResult[0]);
                return;
            }
            
            // Bước 3: Xử lý dữ liệu trong Model
            model.setContent(input);
            
            // Bước 4: Phân tích từ và ký tự
            Model.WordResult[] wordResults = model.analyzeWords();
            Model.CharResult[] charResults = model.analyzeCharacters();
            
            // Bước 5: Hiển thị kết quả
            view.displayResults(wordResults, charResults);
            
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi input không hợp lệ
            view.displayError(e.getMessage());
            view.displayResults(new Model.WordResult[0], 
                              new Model.CharResult[0]);
            
        } catch (OutOfMemoryError e) {
            // Xử lý lỗi bộ nhớ (chuỗi quá dài)
            view.displayError("Input too large to process");
            view.displayResults(new Model.WordResult[0], 
                              new Model.CharResult[0]);
            
        } catch (Exception e) {
            // Xử lý các lỗi khác
            view.displayError("Unexpected error: " + e.getMessage());
            view.displayResults(new Model.WordResult[0], 
                              new Model.CharResult[0]);
        }
    }
    
    /**
     * Validate input theo yêu cầu
     * @param input Chuỗi đầu vào
     * @return true nếu hợp lệ, false nếu không
     */
    private boolean validateInput(String input) {
        // Kiểm tra null
        if (input == null) {
            return false;
        }
        
        // Kiểm tra rỗng (cho phép chuỗi rỗng như code gốc)
        // Code gốc vẫn chạy được với chuỗi rỗng, chỉ in ra {} {}
        return true;
    }
    
    /**
     * Chạy chương trình
     */
    public void run() {
        try {
            process();
        } finally {
            // Đảm bảo luôn đóng tài nguyên
            view.close();
        }
    }
}

