package Fibonacci4;

class FibonacciModel {

    // Hàm đệ quy đuôi: Tính đến đâu in đến đó, không cần quay ngược lại
    long FibonacciRecursive(long n, long lower, long higher) {
        // LUÔN in số 'higher' (số hiện tại) ra trước
        System.out.print(higher + "  "); 

        // ĐIỀU KIỆN DỪNG: Khi bộ đếm n giảm xuống dưới 1
        if(n < 1) { 
            return higher; // Thoát khỏi tầng đệ quy cuối cùng
        }
        
        // GỌI ĐỆ QUY: 
        // n-1: Đếm lùi số lần cần in
        // higher: Số hiện tại trở thành số đứng trước (lower) cho lần sau
        // lower + higher: Tổng mới trở thành số hiện tại (higher) cho lần sau
        return FibonacciRecursive(n-1, higher, lower + higher);
    }
}
