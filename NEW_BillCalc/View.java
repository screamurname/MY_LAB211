/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewBillCalc;

import java.util.Scanner;
import java.math.BigInteger;

/**
 *
 * @author Dang Vu
 */
public class View {

    private final Scanner in = new Scanner(System.in);

    // Xử lý nhập số nguyên dương, chặn chữ, ký tự đặc biệt và số quá lớn
    public int checkInputInt(String msg) {
        while (true) {
            System.out.print(msg);
            String input = in.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Dữ liệu không được để trống!");
                continue;
            }

            try {
                int result = Integer.parseInt(input);
                if (result < 0) {
                    System.err.println("Vui lòng nhập số dương.");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                // Kiểm tra xem lỗi do nhập chữ hay do số vượt ngưỡng Integer (2^31 - 1)
                try {
                    new BigInteger(input);
                    System.err.println("Số quá lớn! Giới hạn tối đa là " + Integer.MAX_VALUE);
                } catch (Exception ex) {
                    System.err.println("Lỗi định dạng! Vui lòng chỉ nhập số nguyên.");
                }
            }
        }
    }

    public void displayResult(long total, boolean canPay) {
        System.out.println("Tổng hóa đơn: " + total);
        if (canPay) {
            System.out.println("Bạn có thể mua được sản phẩm này.");
        } else {
            System.err.println("Số dư không đủ. Bạn không thể mua!");
        }
    }

}
