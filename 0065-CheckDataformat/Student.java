public class Student {
    private String studentName;
    private String className;
    private double math;
    private double physical;
    private double chemistry;

    public Student(String studentName, String className, double math, double physical, double chemistry) {
        this.studentName = studentName;
        this.className = className;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }

    public double getAverage() {
        return (math + physical + chemistry) / 3;
    }

    public char getType() {
        double avg = getAverage();
        if (avg > 7.5) return 'A';
        if (avg >= 6) return 'B';
        if (avg >= 4) return 'C';
        return 'D';
    }

    // Getters và Setters
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }
}