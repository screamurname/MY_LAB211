
import java.util.Scanner;

public class View {

    /**
     * View xử lý giao diện người dùng
     */
    private final Scanner scanner;

    /**
     * Constructor khởi tạo Scanner
     */
    public View() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Nhập dữ liệu từ người dùng
     *
     * @return Chuỗi người dùng nhập
     */
    public String getInput() {
        System.out.print("Enter your content: ");
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    /**
     * Hiển thị kết quả theo đúng format yêu cầu
     *
     * @param wordResults Kết quả đếm từ
     * @param charResults Kết quả đếm ký tự
     */
    public void displayResults(Model.WordResult[] wordResults,
            Model.CharResult[] charResults) {

        // Hiển thị kết quả từ - giống format HashMap
        System.out.print("{");
        for (int i = 0; i < wordResults.length; i++) {
            System.out.print(wordResults[i].getWord() + "=" + wordResults[i].getCount());
            if (i < wordResults.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");

        // Hiển thị kết quả ký tự - giống format HashMap
        System.out.print("{");
        for (int i = 0; i < charResults.length; i++) {
            System.out.print(charResults[i].getCharacter() + "=" + charResults[i].getCount());
            if (i < charResults.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    /**
     * Hiển thị thông báo lỗi
     *
     * @param message Nội dung thông báo
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Hiển thị thông báo
     *
     * @param message Nội dung thông báo
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Đóng scanner
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
