import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    private final Scanner in = new Scanner(System.in);

    public String inputName() {
    while (true) {
        System.out.print("Name: ");
        String result = in.nextLine().trim();
        
        // Kiểm tra định dạng: chỉ chứa chữ cái và khoảng trắng
        if (result.matches("^[a-zA-Z\\s]+$")) {
            return formatName(result); // Trả về tên đã được chuẩn hóa
        }
        System.err.println("Name must contain only letters.");
    }
}

// Hàm phụ trợ để chuẩn hóa định dạng tên
private String formatName(String name) {
    // 1. Loại bỏ khoảng trắng thừa giữa các từ
    name = name.replaceAll("\\s+", " ").toLowerCase();
    
    // 2. Chia chuỗi thành các từ
    String[] words = name.split(" ");
    StringBuilder sb = new StringBuilder();
    
    for (String word : words) {
        if (!word.isEmpty()) {
            // Viết hoa chữ cái đầu, viết thường các chữ còn lại
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1))
              .append(" ");
        }
    }
    return sb.toString().trim();
}

    public String inputClassName() {
        while (true) {
            System.out.print("Classes: ");
            String result = in.nextLine().trim();
            if (result.matches("^[a-zA-Z0-9\\s]+$")) return result;
            System.err.println("Class must contain letters and numbers.");
        }
    }

    public double inputMark(String subject) {
        while (true) {
            try {
                System.out.print(subject + ": ");
                double result = Double.parseDouble(in.nextLine());
                if (result >= 0 && result <= 10) return result;
                System.err.println(subject + " must be from 0 to 10.");
            } catch (NumberFormatException e) {
                System.err.println(subject + " must be a digit.");
            }
        }
    }

    public boolean checkYN() {
        while (true) {
            System.out.print("Do you want to enter more student information?(Y/N): ");
            String result = in.nextLine().trim();
            if (result.equalsIgnoreCase("Y")) return true;
            if (result.equalsIgnoreCase("N")) return false;
            System.err.println("Please input Y or N.");
        }
    }

    public void displayStudents(List<Student> ls) {
        int i = 0;
        for (Student s : ls) {
            System.out.println("Student " + (++i) + " info");
            System.out.println("Name: " + s.getStudentName());
            System.out.println("Classes: " + s.getClassName());
            System.out.println("AVG: " + String.format("%.1f", s.getAverage()));
            System.out.println("Type: " + s.getType());
        }
    }

    public void displayClassification(Map<String, Double> stats) {
        System.out.println("-------- Classification Info --------");
        stats.forEach((key, value) -> System.out.println(key + " :" + value + "%"));
    }
}
