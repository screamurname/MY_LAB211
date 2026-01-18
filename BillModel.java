/**
 * Model chứa dữ liệu và các hàm tính toán từ code gốc
 */
public class BillModel {
    private int[] bills;
    private int wallet;
    
    /**
     * Constructor mặc định
     */
    public BillModel() {
        this.bills = new int[0];
        this.wallet = 0;
    }
    
    /**
     * Lấy mảng bills từ code gốc
     * @return bills
     */
    public int[] getBills() {
        return bills;
    }
    
    /**
     * Thiết lập bills từ code gốc
     * @param bills
     */
    public void setBills(int[] bills) {
        this.bills = bills;
    }
    
    /**
     * Lấy wallet từ code gốc
     * @return wallet
     */
    public int getWallet() {
        return wallet;
    }
    
    /**
     * Thiết lập wallet từ code gốc
     * @param wallet
     */
    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
    
    /**
     * Calculate the total amount of the bills - GIỮ NGUYÊN TỪ CODE GỐC
     */
    public int calcTotal() {
        int total = 0;
        for (int i = 0; i < bills.length; i++) {
            total += bills[i];
        }
        return total;
    }
    
    /**
     * Check whether the amount in the wallet is enough to pay - GIỮ NGUYÊN TỪ CODE GỐC
     */
    public boolean payMoney(int total, int wallet) {
        if (total > wallet) {
            return false;
        } else {
            return true;
        }
    }
}