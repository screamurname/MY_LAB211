
import java.util.Scanner;

public class View {
   private Scanner scanner;

    public View() { //KHOI TAO SCANNER
        scanner = new Scanner(System.in);
    }
    public int InputSize(int min, int max){ // nhap va xac thuc kich thuoc cua mang 
        String input;
        int output;
        while(true){
            System.out.println("Enter the number of array: ");
            input = scanner.nextLine();
            if(input.isEmpty()){
                System.out.println("Input cannot be empty! ");
                continue;
            }
            try {
                output = Integer.parseInt(input);
                if(output <= 0){
                    System.out.println("Input must be greater than 0! ");
                    continue;
                }
                if(output < min || output > max){
                    System.out.println("Input is out of range! ");
                    continue;
                }
                return output;
            } catch (NumberFormatException e) {
                System.out.println("Input must be valid interger! ");
            }
        }
    }
   public void DisplayArray(int[] array, String message){ //hien thi mang ra man hinh 
       System.out.print(message + "[");
       if (array.length == 0 ){
           System.out.println("]");
           return;
       }
       for (int i = 0; i < array.length; i++) {
           System.out.print(array[i]);
           if (i < array.length -1){
               System.out.print(",");
           }
           
       }
       System.out.println("]");
   }
   public void DisplayError(String errorMessage){ // hien thi thong bao loi
       System.out.println("Error: " + errorMessage);
   }
   public void DisplayMessage(String message){ // hien thi thong bao thuong
       System.out.println(message);
   }
   public void close(){ // dong tai nguyen
       scanner.close();
   }
}// end class
