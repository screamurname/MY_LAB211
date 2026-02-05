/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

/**
 *
 * @author Dang Vu
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo các thành phần theo mô hình MVC
        View view = new View();
        Person person = new Person();
        
        // Kết nối qua Controller
        Controller controller = new Controller(view, person);
        
        // Thực thi
        controller.runProgram();
    }
}
