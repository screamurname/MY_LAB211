/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package package2;

/**
 *
 * @author admin
 */
public class Main {
    public static void main(String[] args) {
    ChangeBaseSystemModel model = new ChangeBaseSystemModel();
    ChangeBaseSystemView view = new ChangeBaseSystemView();
    
    ChangeBaseSystemController controller = new ChangeBaseSystemController(model, view); 
    
    controller.run();
    }
    
}
