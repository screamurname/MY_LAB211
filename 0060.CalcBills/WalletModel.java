/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

public class WalletModel {
    private int[] bills;
    private int wallet;

    public WalletModel() {}

    public void setBills(int[] bills) {
        this.bills = bills;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    // Tính tổng hóa đơn với kiểu long để tránh tràn số (Overflow)
    public long calcTotal() {
        long total = 0;
        for (int bill : bills) {
            total += bill;
        }
        return total;
    }

    public boolean canPay(long total) {
        return wallet >= total;
    }
}
