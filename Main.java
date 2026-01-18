/**
 * Lớp chính khởi chạy chương trình
 * @author thaycacac
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo MVC
        BillModel model = new BillModel();
        BillView view = new BillView();
        BillController controller = new BillController(model, view);
        
        // Chạy chương trình
        controller.run();
    }
}