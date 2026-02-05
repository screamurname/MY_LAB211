/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

/**
 *
 * @author Dang Vu
 */
public class Person {
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Tính tổng hóa đơn. 
     * Dùng kiểu long để tránh tràn số khi cộng nhiều số int lớn.
     */
    public long calcTotal(int[] bills) {
        long total = 0;
        for (int bill : bills) {
            total += (long) bill;
        }
        return total;
    }

    /**
     * Kiểm tra khả năng chi trả.
     */
    public boolean payMoney(long total) {
        if (wallet == null) return false;
        return wallet.getAmount() >= total;
    }
}
