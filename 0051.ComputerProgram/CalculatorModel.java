
public class CalculatorModel {

    public String BMIStatus(double bmi) {
        int status;
        // Xác định nhóm trạng thái
        if (bmi < 19) {
            status = 1;
        } else if (bmi >= 19 && bmi <= 25) {
            status = 2;
        } else if (bmi > 25 && bmi <= 30) {
            status = 3;
        } else if (bmi > 30 && bmi <= 40) {
            status = 4;
        } else {
            status = 5;
        }

        // Sử dụng switch case thường (không dùng lambda ->)
        switch (status) {
            case 1:
                return "Under-standard.";
            case 2:
                return "Standard.";
            case 3:
                return "Overweight.";
            case 4:
                return "Fat - should lose weight";
            case 5:
                return "Very fat - should lose weight immediately";
            default:
                return "Invalid";
        }
    }

    public double calculateBMI(double weight, double height) {
        // Giữ nguyên công thức từ file gốc của bạn
        return weight * 10000 / (height * height);
    }
}