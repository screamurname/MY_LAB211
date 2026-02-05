import java.util.ArrayList;
import java.util.Scanner;

public class Validate {
    private final static Scanner in = new Scanner(System.in);
    public static final String REGEX_CODE = "[a-zA-Z]+";
    public static final String REGEX_NAME = "[a-zA-Z ]+";

    //check user input number limit
    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    
    //check user input string
     public static String checkInputString( String regex, String errorMessage) {
        while (true) {          
            String input = in.nextLine().trim(); // DÙNG Scanner in CHUNG
            if(input.isEmpty()) {
                System.out.println("Input cannot be empty");
                continue;
            }
            if (input.matches(regex)) {
                return input;
            }
            else {
                System.err.println(errorMessage);
            }
        }
    }
    
    public static String checkInputName() {      
        while (true) {
            String input = in.nextLine(); // DÙNG Scanner in CHUNG
            
            // 1. Check empty (sau khi trim)
            String trimmedInput = input.trim();
            if (trimmedInput.isEmpty()) {
                System.err.println("Name cannot be empty. Please enter again:");
                continue;
            }
            
            // 2. Chuẩn hóa: thay thế nhiều khoảng trắng liên tiếp bằng 1 khoảng trắng
            String normalizedInput = trimmedInput.replaceAll("\\s+", " ");
            
            // 3. Check length trên input đã chuẩn hóa: 2-50 ký tự
            if (normalizedInput.length() < 2) {
                System.err.println("Name must be at least 2 characters. Please enter again:");
                continue;
            }
            
            if (normalizedInput.length() > 50) {
                System.err.println("Name cannot exceed 50 characters. Please enter again:");
                continue;
            }
            
            // 4. Check chỉ chứa chữ cái và khoảng trắng
            // Regex: chỉ chữ cái (A-Za-z) và khoảng trắng
            String nameRegex = "^[A-Za-z][A-Za-z ]*[A-Za-z]$";
            if (!normalizedInput.matches(nameRegex)) {
                System.err.println("Name can only contain letters and spaces. Please enter again:");
                continue;
            }
            
            // 5. Chuẩn hóa viết hoa chữ cái đầu mỗi từ
            String finalName = capitalizeWords(normalizedInput);
            
            return finalName;
        }
    }
    
    private static String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String[] words = str.split("\\s");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                // Viết hoa chữ cái đầu, viết thường các chữ cái sau
                String capitalizedWord = word.substring(0, 1).toUpperCase() + 
                                        word.substring(1).toLowerCase();
                result.append(capitalizedWord).append(" ");
            }
        }
        
        // Loại bỏ khoảng trắng cuối cùng
        return result.toString().trim();
    }
     
    public static String checkInputID() {       
    while (true) {
        String input = in.nextLine().trim();
        
        if (input.isEmpty()) {
            System.err.println("ID cannot be empty. Please enter again:");
            continue;
        }
        
        if (input.contains(" ")) {
            System.err.println("ID cannot contain spaces. Please enter again:");
            continue;
        }

        String idRegex = "^[A-Za-z0-9]+$";
        if (!input.matches(idRegex)) {
            System.err.println("ID can only contain letters and numbers. Please enter again:");
            continue;
        }

        if (input.length() < 3) {
            System.err.println("ID must be at least 3 characters. Please enter again:");
            continue;
        }
        
        if (input.length() > 20) {
            System.err.println("ID cannot exceed 20 characters. Please enter again:");
            continue;
        }

        return input; // THÊM DÒNG NÀY để trả về giá trị và thoát hàm
    }
}

    // check id must be existed in DB.
    public static boolean checkIdExist(ArrayList<Worker> lw, String id) {
        //check from first to last list id worker exist or not
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return true;
            }
        }
        return false;
    }

    //check salary must be greater than 0
    public static int checkInputSalary() {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Salary must be greater than 0");
                System.out.print("Enter again: ");
            }
        }
    }

    //check worker duplicate
    public static boolean checkWorkerExist(ArrayList<Worker> lw, String id,
            String name, int age, int salary, String workLocation) {
        //check from first to last list worker  worker exist or not
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())
                    && name.equalsIgnoreCase(worker.getName())
                    && age == worker.getAge()
                    && salary == worker.getSalary()
                    && workLocation.equalsIgnoreCase(worker.getWorkLocation())) {
                return false;
            }
        }
        return true;
    }
}