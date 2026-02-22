# **BÁO CÁO PHÂN TÍCH ỨNG DỤNG ĐẾM TỪ VÀ KÝ TỰ (JAVA CONSOLE)**

## **1. TỔNG QUAN ỨNG DỤNG**

Đây là một chương trình Java đơn giản chạy trên giao diện dòng lệnh (console), cho phép người dùng nhập vào một đoạn văn bản bất kỳ. Sau đó, chương trình sẽ phân tích và hiển thị kết quả thống kê bao gồm:

- **Số lần xuất hiện của mỗi từ** trong đoạn văn bản
- **Số lần xuất hiện của mỗi ký tự** (không tính khoảng trắng)

Ví dụ: Người dùng nhập "**java is fun java**", kết quả trả về sẽ là:
- Từ: {java=2, is=1, fun=1}
- Ký tự: {j=2, a=2, v=2, i=1, s=1, f=1, u=1, n=1}

## **2. KIẾN TRÚC MVC - MÔ HÌNH TỔ CHỨC CODE CHUYÊN NGHIỆP**

Ứng dụng được xây dựng theo **Mô hình MVC (Model-View-Controller)**, một kiến trúc phần mềm kinh điển giúp phân tách rõ ràng các thành phần, tạo nên một hệ thống có tổ chức, dễ bảo trì và mở rộng.

### **2.1. Mô hình MVC là gì?**

Hãy hình dung một nhà hàng chuyên nghiệp:

| Thành phần | Vai trò trong nhà hàng | Vai trò trong ứng dụng |
|------------|------------------------|------------------------|
| **Model** | Đầu bếp - người chế biến món ăn | Xử lý dữ liệu, tính toán logic |
| **View** | Phục vụ - người tiếp nhận order và phục vụ khách | Giao tiếp với người dùng (input/output) |
| **Controller** | Quản lý - người điều phối mọi hoạt động | Điều khiển luồng dữ liệu giữa Model và View |

Sự phân tách này đảm bảo **mỗi thành phần chỉ làm đúng một nhiệm vụ** (Single Responsibility Principle), giúp code trong sáng, dễ hiểu và dễ sửa lỗi.

---

## **3. PHÂN TÍCH CHI TIẾT TỪNG THÀNH PHẦN**

### **3.1. CounterModel.java - "Bộ Não Xử Lý"**

#### **Vai trò:**
Đây là thành phần **Model**, chịu trách nhiệm xử lý dữ liệu và thực hiện các tính toán logic. Model không hề biết dữ liệu đến từ đâu (bàn phím, file, mạng) và cũng không biết kết quả sẽ được hiển thị như thế nào. Nó chỉ tập trung vào một việc duy nhất: **phân tích văn bản và đếm**.

#### **Cấu trúc dữ liệu - Map và HashMap:**

Trước khi đi vào code, cần hiểu về **Map** - một cấu trúc dữ liệu nền tảng:

**Map là gì?**
- Là một "cuốn sổ" đặc biệt, lưu trữ dữ liệu dưới dạng **cặp Khóa (Key) - Giá trị (Value)**
- Mỗi Key là duy nhất, không thể trùng lặp
- Ví dụ trong đời thực: **Sổ điểm danh** (Key: tên học sinh, Value: điểm số)

**HashMap là gì?**
- Là một **loại Map cụ thể**, được cài đặt phổ biến nhất trong Java
- Đặc điểm: **Truy xuất siêu nhanh** nhưng **không đảm bảo thứ tự** của dữ liệu
- Hoạt động dựa trên **mã băm (hash code)** - một "định danh" được tính toán từ Key

```java
// Code phân tích
public class CounterModel {
    // Khai báo hai "cuốn sổ" (Map) để lưu kết quả
    private Map<Character, Integer> charCounter = new HashMap<>();
    private Map<String, Integer> wordCounter = new HashMap<>();
    
    // Phương thức xử lý chính
    public void analyze(String content) {
        // Bước 1: Xóa dữ liệu cũ - chuẩn bị cho phân tích mới
        charCounter.clear();
        wordCounter.clear();
        
        // Bước 2: Đếm ký tự (bỏ qua khoảng trắng)
        for (char ch : content.toCharArray()) {
            if (Character.isSpaceChar(ch)) continue; // Bỏ qua khoảng trắng
            
            // Lấy số đếm hiện tại, nếu chưa có thì lấy 0, sau đó +1
            charCounter.put(ch, charCounter.getOrDefault(ch, 0) + 1);
        }
        
        // Bước 3: Đếm từ (tách bằng khoảng trắng)
        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken(); // Lấy từng từ
            // Tương tự: lấy số đếm hiện tại, +1 và lưu lại
            wordCounter.put(token, wordCounter.getOrDefault(token, 0) + 1);
        }
    }
    
    // Các phương thức getter để lấy kết quả
    public Map<Character, Integer> getCharCounter() {
        return charCounter;
    }
    
    public Map<String, Integer> getWordCounter() {
        return wordCounter;
    }
}
```

#### **Giải thích logic từng dòng:**
1. **`charCounter.getOrDefault(ch, 0)`**: Tra cứu trong sổ xem ký tự `ch` đã được ghi nhận chưa. Nếu có, lấy số đếm hiện tại; nếu chưa, coi như số đếm là 0.
2. **`+ 1`**: Tăng số đếm lên 1 đơn vị (vì vừa phát hiện thêm một lần xuất hiện)
3. **`charCounter.put(...)`**: Ghi lại số đếm mới vào sổ, gắn với ký tự tương ứng

**StringTokenizer** là công cụ chuyên dụng để tách chuỗi thành các từ dựa trên khoảng trắng. Nó hoạt động như một "máy cắt" tự động.

---

### **3.2. CounterView.java - "Giao Diện Người Dùng"**

#### **Vai trò:**
Đây là thành phần **View**, đảm nhận mọi giao tiếp với người dùng. View nhận dữ liệu đầu vào và hiển thị kết quả đầu ra, nhưng **không xử lý bất kỳ logic nghiệp vụ nào**. Nó chỉ là cầu nối giữa người dùng và hệ thống.

```java
public class CounterView {
    // Scanner: công cụ đọc dữ liệu từ bàn phím (giống như tai và mắt)
    private final Scanner scanner = new Scanner(System.in);
    
    // Phương thức lấy dữ liệu từ người dùng (có kiểm tra tính hợp lệ)
    public String getInput() {
        while (true) { // Lặp vô hạn cho đến khi nhận được dữ liệu hợp lệ
            System.out.println("Enter your content: ");
            String input = scanner.nextLine().trim(); // Đọc và loại bỏ khoảng trắng đầu/cuối
            
            if (input.isEmpty()) { // Kiểm tra rỗng
                System.err.println("Content cannot be empty. Please try again!");
                continue; // Quay lại đầu vòng lặp, yêu cầu nhập lại
            }
            return input; // Trả về dữ liệu hợp lệ
        }
    }
    
    // Phương thức hiển thị kết quả
    public void displayResult(Map<String, Integer> wordMap, Map<Character, Integer> charMap) {
        System.out.println("Word count: " + wordMap);
        System.out.println("Character count: " + charMap);
    }
}
```

#### **Điểm đáng chú ý:**
- **`scanner.nextLine().trim()`**: `trim()` là phương thức cắt bỏ khoảng trắng thừa ở đầu và cuối chuỗi, giúp chuẩn hóa dữ liệu trước khi xử lý
- **`System.err.println()`**: In ra luồng lỗi (error stream), thường được dùng để thông báo lỗi, phân biệt với thông báo thường (`System.out`)
- **Vòng lặp `while(true)`**: Tạo cơ chế "bắt nhập lại đến khi đúng", rất thân thiện với người dùng

---

### **3.3. CounterController.java - "Bộ Điều Khiển Trung Tâm"**

#### **Vai trò:**
Đây là thành phần **Controller**, đóng vai trò điều phối toàn bộ hoạt động của ứng dụng. Controller nhận dữ liệu từ View, gửi cho Model xử lý, sau đó lấy kết quả từ Model và trả về cho View hiển thị. Nó là **chất keo kết nối** các thành phần lại với nhau.

```java
public class CounterController {
    private CounterModel model; // Tham chiếu đến Model (đầu bếp)
    private CounterView view;   // Tham chiếu đến View (phục vụ)
    
    // Constructor - "tiếp nhận nhân sự" khi controller được tạo
    public CounterController(CounterModel model, CounterView view) {
        this.model = model;
        this.view = view;
    }
    
    // Phương thức chính - "quy trình làm việc" chuẩn
    public void run() {
        // Bước 1: Lấy dữ liệu từ người dùng thông qua View
        String content = view.getInput();
        
        // Bước 2: Chuyển dữ liệu cho Model xử lý
        model.analyze(content);
        
        // Bước 3: Lấy kết quả từ Model và gửi cho View hiển thị
        view.displayResult(model.getWordCounter(), model.getCharCounter());
    }
}
```

#### **Ba bước bất biến của Controller:**
1. **INPUT** (Lấy dữ liệu từ View)
2. **PROCESS** (Gửi cho Model xử lý)
3. **OUTPUT** (Nhận kết quả và hiển thị qua View)

Đây là mô hình xử lý chuẩn trong hầu hết các ứng dụng.

---

### **3.4. Main.java - "Điểm Khởi Đầu"**

#### **Vai trò:**
Đây là lớp chứa phương thức `main()` - **cửa ngõ vào của ứng dụng**. Nó khởi tạo tất cả các thành phần và bắt đầu quy trình.

```java
public class Main {
    public static void main(String[] args) {
        // Bước 1: Khởi tạo các đối tượng
        CounterModel model = new CounterModel();   // Tạo đầu bếp
        CounterView view = new CounterView();       // Tạo phục vụ
        CounterController controller = new CounterController(model, view); // Tạo quản lý, giới thiệu đầu bếp và phục vụ
        
        // Bước 2: Bắt đầu chạy
        controller.run(); // Quản lý bắt đầu điều hành công việc
    }
}
```

Phương thức `main()` được Java Virtual Machine (JVM) gọi đầu tiên khi chương trình chạy. Nó giống như "công tắc khởi động" của toàn bộ hệ thống.

---

## **4. LUỒNG HOẠT ĐỘNG TỔNG THỂ**

Hãy theo dõi một phiên làm việc điển hình:

**Bước 1 - Khởi động:** JVM gọi `main()` trong `Main.java`
- Tạo `model` (đầu bếp)
- Tạo `view` (phục vụ)
- Tạo `controller` (quản lý) và kết nối mọi người

**Bước 2 - Controller điều phối:** `controller.run()` được gọi

**Bước 3 - Lấy dữ liệu:** Controller gọi `view.getInput()`
- View hiển thị "Enter your content: "
- Người dùng gõ "hello world"
- View trả về chuỗi "hello world" cho Controller

**Bước 4 - Xử lý:** Controller gọi `model.analyze("hello world")`
- Model xóa dữ liệu cũ
- Đếm ký tự: h-1, e-1, l-3, o-2, w-1, r-1, d-1
- Đếm từ: hello-1, world-1
- Lưu vào hai Map bên trong

**Bước 5 - Lấy kết quả:** Controller gọi `model.getWordCounter()` và `model.getCharCounter()`

**Bước 6 - Hiển thị:** Controller gọi `view.displayResult(wordMap, charMap)`
- View in ra: "Word count: {hello=1, world=1}"
- View in ra: "Character count: {r=1, d=1, e=1, w=1, h=1, l=3, o=2}"

**Bước 7 - Kết thúc:** Chương trình kết thúc, JVM giải phóng bộ nhớ

---

## **5. ĐÁNH GIÁ VÀ NHẬN XÉT**

### **Điểm mạnh:**

1. **Tuân thủ MVC nghiêm ngặt** - Phân tách rõ ràng, mỗi lớp chỉ làm một nhiệm vụ
2. **Khả năng tái sử dụng cao** - Có thể dễ dàng thay đổi View (ví dụ: chuyển sang giao diện đồ họa) mà không ảnh hưởng đến Model
3. **Dễ kiểm thử** - Có thể test từng thành phần độc lập
4. **Xử lý lỗi tốt** - View có validate dữ liệu đầu vào, không cho phép chuỗi rỗng
5. **Mã nguồn sạch sẽ** - Dễ đọc, dễ bảo trì

### **Hạn chế:**

1. **HashMap không đảm bảo thứ tự** - Kết quả hiển thị có thể thay đổi thứ tự giữa các lần chạy
2. **Không phân biệt chữ hoa/thường** - "Hello" và "hello" được tính là hai từ khác nhau
3. **Không xử lý dấu câu** - "hello," và "hello" là hai từ khác nhau do dấu phẩy
4. **Thiếu exception handling** - Không bắt các ngoại lệ có thể xảy ra

### **Gợi ý cải tiến:**

1. Sử dụng `TreeMap` thay `HashMap` nếu muốn kết quả được sắp xếp
2. Thêm xử lý chuyển về chữ thường: `content.toLowerCase()`
3. Loại bỏ dấu câu bằng regex: `content.replaceAll("[^a-zA-Z0-9\\s]", "")`
4. Thêm try-catch để xử lý các trường hợp ngoại lệ

---

## **6. KẾT LUẬN**

Đây là một ứng dụng Java console được thiết kế tốt, minh họa thành công mô hình MVC và các khái niệm cơ bản trong lập trình hướng đối tượng. Nó thể hiện sự hiểu biết về:

- **Cấu trúc dữ liệu** (Map, HashMap)
- **Xử lý chuỗi** (StringTokenizer, char array)
- **Tương tác người dùng** (Scanner)
- **Tổ chức code theo mô hình** (MVC)

Ứng dụng tuy đơn giản nhưng là nền tảng vững chắc để phát triển thành các ứng dụng phức tạp hơn như xử lý văn bản, phân tích nội dung, hay các hệ thống có giao diện đồ họa.

---

*Báo cáo phân tích kỹ thuật - Dành cho người mới bắt đầu*
*Ngày phân tích: 22/02/2026*
