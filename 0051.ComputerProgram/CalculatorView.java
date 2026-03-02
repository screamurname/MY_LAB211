
import java.util.Scanner;

public class CalculatorView {

    private final Scanner in = new Scanner(System.in);

    public int menu() {
        System.out.println("1. Normal Calculator");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return checkInputIntLimit(1, 3);
    }

    public int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public double checkInputDouble() {
        while (true) {
            try {
                return Double.parseDouble(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Input cannot be empty and must be input double");
                System.out.print("Enter again: ");
            }
        }
    }

    // Hàm này dùng riêng cho BMI để chặn số âm và số 0
    public double checkInputBMI(String msg) {
        while (true) {
            System.out.print("Enter " + msg + ": ");
            try {
                double result = Double.parseDouble(in.nextLine().trim());
                if (result <= 0) {
                    System.err.println(msg + " must be greater than 0.");
                } else if (result > 200) {
                    System.err.println(msg + "is to large!");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println("Input cannot be empty and must be input double");
            }
            System.out.print("Enter again: ");
        }
    }

    public String checkInputOperator() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.matches("[+\\-*/^=]")) {
                return result;
            }
            System.err.println("Please input (+, -, *, /, ^, =)");
            System.out.print("Enter again: ");
        }
    }
}
