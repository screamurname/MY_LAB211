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
        WalletView view = new WalletView();
        WalletModel model = new WalletModel();
        WalletController controller = new WalletController(view, model);

        controller.processPayment();
    }
}
