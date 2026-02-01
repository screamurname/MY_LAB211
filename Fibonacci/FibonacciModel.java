package Fibonacci4;

class FibonacciModel {
    // Đây là hàm đệ quy đuôi (Tail Recursion)
    long FibonacciRecursive(long n, long lower, long higher) {
        // 1. Lệnh in: Luôn in số hiện tại (higher) ra màn hình trước
        System.out.print(higher + "  ");

        // 2. Điều kiện dừng (Base case của đệ quy): 
        // Nếu số lượt cần in (n) nhỏ hơn 1, dừng lại và trả về giá trị cuối cùng
        if (n < 1) {
            return higher;
        }

        // 3. Bước đệ quy: Tự gọi lại chính nó với bộ thông số mới
        // n-1: Giảm số lượt cần in
        // lower mới = higher cũ
        // higher mới = lower + higher (Tính số tiếp theo)
        // position+1: Tăng vị trí đếm
        return FibonacciRecursive(n - 1, higher, lower + higher);
    }
}
