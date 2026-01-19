/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dang Vu
 */
public class Main {
      /**
     * Phương thức main
     * @param args Tham số dòng lệnh
     */
    public static void main(String[] args) {
        // Khởi tạo các thành phần MVC
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        
        // Chạy chương trình
        controller.run();
    }
}
