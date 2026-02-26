import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentController {
    private List<Student> students;
    private StudentView view;

    public StudentController() {
        this.students = new ArrayList<>();
        this.view = new StudentView();
    }

    public void start() {
        // Xử lý logic nhập qua View
        while (true) {
            String name = view.inputName();
            String className = view.inputClassName();
            double math = view.inputMark("Maths");
            double physics = view.inputMark("Physics");
            double chemistry = view.inputMark("Chemistry");

            students.add(new Student(name, className, math, physics, chemistry));

            if (!view.checkYN()) break;
        }

        // Hiển thị danh sách
        view.displayStudents(students);

        // Xử lý logic thống kê và hiển thị qua View
        view.displayClassification(calculatePercent());
    }

    private Map<String, Double> calculatePercent() {
    Map<String, Double> stats = new HashMap<>();
    double a = 0, b = 0, c = 0, d = 0;
    int total = students.size();

    // Nếu không có sinh viên nào, trả về map rỗng để tránh lỗi chia cho 0
    if (total == 0) {
        stats.put("A", 0.0);
        stats.put("B", 0.0);
        stats.put("C", 0.0);
        stats.put("D", 0.0);
        return stats;
    }

    // Sử dụng for-each và switch-case truyền thống
    for (Student s : students) {
        char type = s.getType();
        switch (type) {
            case 'A':
                a++;
                break;
            case 'B':
                b++;
                break;
            case 'C':
                c++;
                break;
            case 'D':
                d++;
                break;
        }
    }

    // Tính toán phần trăm
    stats.put("A", (a / total) * 100);
    stats.put("B", (b / total) * 100);
    stats.put("C", (c / total) * 100);
    stats.put("D", (d / total) * 100);

    return stats;
}
}