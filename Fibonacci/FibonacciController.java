/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fibonacci4;

/**
 *
 * @author admin
 */
class FibonacciController {

    private final FibonacciModel model;
    private final FibonacciView view;

    public FibonacciController(FibonacciModel model, FibonacciView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        //Validate
        int maxNumber = view.inputNumberOfFibonacci();
        //Display fibonacci number
        view.displayFibonacciSeries(maxNumber);
        
        if (maxNumber == 0) {
            return; // Không in gì cả
        } else if (maxNumber == 1) {
            view.displayBaseCases(1);
        } else if (maxNumber == 2) {
            view.displayBaseCases(2);
        } else {
            // In 2 số đầu tiên từ View
            view.displayBaseCases(2);
            System.out.print("  "); // Khoảng cách giữa base case và phần đệ quy

            // Gọi đệ quy để in từ số thứ 3 trở đi
            // n: số lượng số còn lại cần in (maxNumber - 2)
            // lower: số Fibonacci thứ 2 (là 1)
            // higher: số Fibonacci thứ 3 (là 0 + 1 = 1)
            model.FibonacciRecursive(maxNumber - 3, 1, 1);
        }
    }

}
