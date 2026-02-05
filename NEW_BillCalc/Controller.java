/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

/**
 *
 * @author Dang Vu
 */
public class Controller {
    private View view;
    private Person person;

    public Controller(View view, Person person) {
        this.view = view;
        this.person = person;
    }

    public void runProgram() {
        System.out.println("======= CHƯƠNG TRÌNH QUẢN LÝ CHI TIÊU =======");
        
        // Bước 1: Nhập hóa đơn qua View
        int size = view.checkInputInt("Nhập số lượng hóa đơn: ");
        int[] bills = new int[size];
        for (int i = 0; i < size; i++) {
            bills[i] = view.checkInputInt("Giá trị hóa đơn " + (i + 1) + ": ");
        }

        // Bước 2: Nhập ví qua View và gán vào Model Person
        int amount = view.checkInputInt("Nhập số tiền hiện có trong ví: ");
        person.setWallet(new Wallet(amount));

        // Bước 3: Model xử lý tính toán
        long total = person.calcTotal(bills);
        boolean canPay = person.payMoney(total);

        // Bước 4: View hiển thị kết quả
        view.displayResult(total, canPay);
    }
}
