
public class CalculatorController {
    private final CalculatorView view = new CalculatorView();
    private final CalculatorModel model = new CalculatorModel();

    public void run() {
        while (true) {
            int choice = view.menu();
            switch (choice) {
                case 1:
                    normalCalculator();
                    break;
                case 2:
                    bmiCalculator();
                    break;
                case 3:
                    return;
            }
        }
    }

    private void normalCalculator() {
        System.out.print("Enter number: ");
        double memory = view.checkInputDouble();
        while (true) {
            System.out.print("Enter operator: ");
            String operator = view.checkInputOperator();
            
            if (operator.equals("=")) {
                System.out.println("Result: " + memory);
                return;
            }

            System.out.print("Enter number: ");
            double number = view.checkInputDouble();

            // Chặn chia cho 0
            if (operator.equals("/") && number == 0) {
                System.err.println("Cannot divide by zero!");
                continue;
            }

            switch (operator) {
                case "+": memory += number; break;
                case "-": memory -= number; break;
                case "*": memory *= number; break;
                case "/": memory /= number; break;
                case "^": memory = Math.pow(memory, number); break;
            }
            System.out.println("Memory: " + memory);
        }
    }

    private void bmiCalculator() {
        // Validate cân nặng chiều cao phải > 0
        double weight = view.checkInputBMI("Weight(kg)");
        double height = view.checkInputBMI("Height(cm)");
        
        double bmi = model.calculateBMI(weight, height);
        System.out.printf("BMI number: %.2f\n", bmi);
        System.out.println("BMI Status: " + model.BMIStatus(bmi));
    }
}