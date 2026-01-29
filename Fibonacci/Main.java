/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fibonacci4;

/**
 *
 * @author admin
 */
public class Main {
    public static void main(String[] args) {
    FibonacciModel model = new FibonacciModel();
    FibonacciView view = new FibonacciView();
    
    FibonacciController controller = new FibonacciController(model, view);
    
    
    controller.run();

    }



}
