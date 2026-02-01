/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

import java.util.Scanner;

public class WalletView {
    private final Scanner in = new Scanner(System.in);

    // Tối ưu Validation: Chặn số âm, chữ và số quá lớn (Integer.MAX_VALUE)
    public int checkInputInt(String msg) {
        while (true) {
            System.out.print(msg);
            String input = in.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("Không được để trống!");
                continue;
            }
            try {
                int result = Integer.parseInt(input);
                if (result < 0) {
                    System.err.println("Vui lòng nhập số dương!");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                // Kiểm tra xem là do nhập chữ hay do số quá lớn
                try {
                    new java.math.BigInteger(input);
                    System.err.println("Số quá lớn! Vui lòng nhập số nhỏ hơn " + Integer.MAX_VALUE);
                } catch (Exception ex) {
                    System.err.println("Sai định dạng! Vui lòng chỉ nhập số nguyên.");
                }
            }
        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public void displayResult(long total, boolean canBuy) {
        System.out.println("--------- Kết quả ---------");
        System.out.println("Tổng hóa đơn: " + total);
        if (canBuy) {
            System.out.println("=> Bạn có thể mua được!");
        } else {
            System.err.println("=> Bạn không đủ tiền để mua.");
        }
    }
}