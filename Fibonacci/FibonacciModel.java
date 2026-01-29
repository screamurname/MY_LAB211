/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fibonacci4;

/**
 *
 * @author admin
 */
class FibonacciModel {
/**
 * Hàm đệ quy
 * @param n - số lần đệ quy còn lại, số số Fibo còn lại cần in
 * @param lower - số fibo trước số hiện tại
 * @param higher -  số fibo hiện tại - số được in ra
 * @return 
 */
    long FibonacciRecursive(long n, long lower, long higher) {
        System.out.print(higher + "  "); // in ra số hiện tại
        //là điều kiện để dừng hàm đệ quy
        if(n < 1) { // n < 1 tức là hết lần đệ quy
            return higher; // trả về giá trị để thoát khỏi hàm đệ quy
        }
        return FibonacciRecursive(n-1, higher, lower + higher);
//        hàm n - 1 để giảm số vòng lặp, số hiện tại sẽ trở thành số đứng trước, cho lần gọi sau, 
//         tổng của số đứng trước và số hiện tại(lower + higher) sẽ trở thành số hiện tại mới cho lần gọi sau
    }

}
