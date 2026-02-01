/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

/**
 *
 * @author Dang Vu
 */
public class WalletController {
    private final WalletView view;
    private final WalletModel model;

    public WalletController(WalletView view, WalletModel model) {
        this.view = view;
        this.model = model;
    }

    public void processPayment() {
        view.displayMessage("===== Quản lý chi tiêu Wallet =====");
        
        // Nhập số lượng hóa đơn
        int size = view.checkInputInt("Nhập số lượng hóa đơn: ");
        int[] bills = new int[size];

        // Nhập giá trị từng hóa đơn
        for (int i = 0; i < size; i++) {
            bills[i] = view.checkInputInt("Giá trị hóa đơn " + (i + 1) + ": ");
        }
        model.setBills(bills);

        // Nhập số tiền trong ví
        int wallet = view.checkInputInt("Nhập số tiền bạn có trong ví: ");
        model.setWallet(wallet);

        // Tính toán và hiển thị
        long total = model.calcTotal();
        boolean canPay = model.canPay(total);
        
        view.displayResult(total, canPay);
    }
}
