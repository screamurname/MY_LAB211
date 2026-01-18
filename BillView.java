import java.util.Scanner;

/**
 * View xử lý giao diện người dùng - Giữ nguyên các hàm từ code gốc
 */
public class BillView {
    private Scanner in = new Scanner(System.in);
    
    /**
     * Check user input a number type int - GIỮ NGUYÊN TỪ CODE GỐC
     * ĐÃ CÓ vòng lặp while(true) để nhập lại khi sai
     */
    public int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a number.");
                System.out.print("Enter again: ");
            }
        }
    }
    
    /**
     * Get array bills that user input - GIỮ NGUYÊN TỪ CODE GỐC
     * SỬA LẠI: Thêm xử lý số lượng bill âm
     */
    public int[] inputBills() {
        int size;
        while (true) {
            System.out.print("Input number of bill: ");
            size = checkInputInt();
            if (size >= 0) {
                break;
            } else {
                System.err.println("Number of bills cannot be negative.");
                System.out.print("Enter again: ");
            }
        }
        
        int[] bills = new int[size];
        //allow user input bills
        for (int i = 0; i < bills.length; i++) {
            while (true) {
                System.out.print("Input value of bill " + (i + 1) + ": ");
                int value = checkInputInt();
                if (value >= 0) {
                    bills[i] = value;
                    break;
                } else {
                    System.err.println("Bill value cannot be negative.");
                    System.out.print("Enter again: ");
                }
            }
        }
        return bills;
    }
    
    /**
     * Get amount that user input - GIỮ NGUYÊN TỪ CODE GỐC
     * SỬA LẠI: Thêm xử lý số tiền âm
     */
    public int inputWallet() {
        int wallet;
        while (true) {
            System.out.print("Input value of wallet: ");
            wallet = checkInputInt();
            if (wallet >= 0) {
                break;
            } else {
                System.err.println("Wallet amount cannot be negative.");
                System.out.print("Enter again: ");
            }
        }
        return wallet;
    }
    
    /**
     * Print total of bill and result - GIỮ NGUYÊN TỪ CODE GỐC
     */
    public void printTotalAndResult(int total, boolean canPay) {
        System.out.println("This is total of bill: " + total);
        if (canPay) {
            System.err.println("You can buy it");
        } else {
            System.err.println("You can't buy it.");
        }
    }
    
    /**
     * Đóng scanner
     */
    public void close() {
        if (in != null) {
            in.close();
        }
    }
}