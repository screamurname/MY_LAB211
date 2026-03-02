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

Đoạn code này nằm trong file `CounterModel.java` và có nhiệm vụ **đếm số lần xuất hiện của từng ký tự** trong chuỗi văn bản mà bạn nhập vào.

Dưới đây là giải thích chi tiết từng dòng:

### 1. Vòng lặp `for (char ch : content.toCharArray())`

* **`content.toCharArray()`**: Chuyển đổi chuỗi văn bản (`content`) thành một mảng các ký tự. Ví dụ: Nếu bạn nhập "Hello", nó sẽ biến thành `['H', 'e', 'l', 'l', 'o']`.
* **`for (char ch : ...)`**: Đây là vòng lặp "for-each". Nó sẽ duyệt qua từng ký tự một trong mảng đó và gán vào biến `ch`.

### 2. Câu lệnh `if (Character.isSpaceChar(ch)) continue;`

* **`Character.isSpaceChar(ch)`**: Kiểm tra xem ký tự `ch` hiện tại có phải là khoảng trắng hay không.
* **`continue`**: Nếu đúng là khoảng trắng, chương trình sẽ **bỏ qua** các dòng lệnh phía dưới và nhảy ngay sang ký tự tiếp theo.
* **Mục đích**: Để chương trình chỉ đếm các chữ cái, con số, ký hiệu... mà không đếm các dấu cách.

### 3. Dòng xử lý chính: `charCounter.put(ch, charCounter.getOrDefault(ch, 0) + 1);`

Dòng này thực hiện cập nhật số lượng ký tự vào bảng mã (`Map`):

* **`charCounter.getOrDefault(ch, 0)`**:
* Máy sẽ kiểm tra xem ký tự `ch` đã có trong danh sách đếm (`charCounter`) chưa.
* Nếu **đã có**, nó lấy giá trị (số lần xuất hiện) hiện tại.
* Nếu **chưa có**, nó trả về giá trị mặc định là `0`.


* **`+ 1`**: Tăng số lượng đếm được thêm 1 đơn vị.
* **`charCounter.put(ch, ...)`**: Lưu (hoặc cập nhật) ký tự `ch` cùng với số lượng mới vào Map.

---

### Ví dụ minh họa

Giả sử bạn nhập chữ: **"AA B"**

1. **Ký tự 'A' đầu tiên**: Chưa có trong Map -> Lấy 0 + 1 = 1. Map: `{A=1}`.
2. **Ký tự 'A' thứ hai**: Đã có 'A' (là 1) -> Lấy 1 + 1 = 2. Map: `{A=2}`.
3. **Ký tự khoảng trắng**: Gặp lệnh `continue` -> Bỏ qua.
4. **Ký tự 'B'**: Chưa có trong Map -> Lấy 0 + 1 = 1. Map: `{A=2, B=1}`.

**Kết quả cuối cùng:** Bạn sẽ có một bảng thống kê chi tiết mỗi ký tự xuất hiện bao nhiêu lần.

Được, giải thích theo cách **người trưởng thành, logic, đời thường**, không ví von trẻ con.

**Array (mảng)** là một vùng bộ nhớ liên tiếp, mỗi phần tử nằm ở **vị trí cố định** và được truy cập bằng **chỉ số**. Vì vị trí đã xác định sẵn nên truy xuất rất nhanh, nhưng nhược điểm là **kích thước không thay đổi được** và khi cần tìm một phần tử theo **giá trị** (không biết chỉ số) thì buộc phải **duyệt tuần tự** toàn bộ mảng.

**List** (phổ biến nhất là ArrayList) là phiên bản **linh hoạt hơn của Array**. Nó vẫn lưu dữ liệu theo thứ tự, nhưng cho phép **tăng giảm kích thước**. Việc truy xuất theo chỉ số vẫn nhanh, tuy nhiên việc **tìm kiếm theo giá trị** hay **xóa/chèn ở giữa** vẫn tốn thời gian vì phải dịch chuyển các phần tử còn lại.

**HashMap** là cấu trúc lưu dữ liệu theo **cặp khóa–giá trị (key–value)**. Thay vì truy cập bằng vị trí, nó truy cập bằng **khóa**. Khi đưa một khóa vào, HashMap sử dụng **hàm băm** để tính ra vị trí lưu trữ trong bộ nhớ, nhờ đó việc thêm và lấy dữ liệu diễn ra **gần như tức thời**, không phụ thuộc vào số lượng phần tử. Đổi lại, HashMap **không đảm bảo thứ tự**, tốn bộ nhớ hơn và yêu cầu khóa phải được thiết kế đúng (`hashCode` và `equals`).

**Tóm lại**:

* Dùng **Array** khi kích thước cố định và truy cập bằng vị trí.
* Dùng **List** khi cần thứ tự và khả năng thêm bớt phần tử.
* Dùng **HashMap** khi cần tra cứu nhanh theo khóa và không quan tâm đến thứ tự.
Đoạn mã này nằm trong phương thức `analyze` của lớp `CounterModel`, có nhiệm vụ **tách chuỗi văn bản thành các từ riêng biệt và đếm số lần xuất hiện của mỗi từ**.

Dưới đây là giải thích chi tiết từng thành phần:

### 1. Khởi tạo StringTokenizer

`StringTokenizer tokenizer = new StringTokenizer(content);`

* **`StringTokenizer`**: Đây là một lớp tiện ích trong Java dùng để chia một chuỗi lớn thành các phần nhỏ hơn (gọi là các **tokens**).
* **Cơ chế mặc định**: Theo mặc định, nó sẽ coi các khoảng trắng (dấu cách, tab, xuống dòng) là ký tự phân cách để tách từ.

### 2. Vòng lặp `while (tokenizer.hasMoreTokens())`

* **`hasMoreTokens()`**: Phương thức này kiểm tra xem trong chuỗi còn từ nào chưa được lấy ra hay không.
* Vòng lặp sẽ tiếp tục chạy cho đến khi toàn bộ văn bản đã được tách hết thành các từ.

### 3. Lấy từ tiếp theo

`String token = tokenizer.nextToken();`

* **`nextToken()`**: Lấy từ (token) hiện tại ra khỏi hàng đợi và gán vào biến `token` kiểu String.
* Ví dụ: Nếu chuỗi là "Học Java", lần đầu nó lấy "Học", lần sau nó lấy "Java".

### 4. Đếm và lưu vào Map

`wordCounter.put(token, wordCounter.getOrDefault(token, 0) + 1);`
Dòng này hoạt động tương tự như phần đếm ký tự mà bạn đã hỏi, nhưng áp dụng cho từ:

* **`wordCounter`**: Là một `HashMap` lưu trữ theo cặp `Key` (Từ) và `Value` (Số lần xuất hiện).
* **`getOrDefault(token, 0)`**: Kiểm tra xem từ này đã có trong danh sách đếm chưa. Nếu có rồi thì lấy số lượng hiện tại, nếu chưa có thì bắt đầu từ `0`.
* **`+ 1`**: Tăng biến đếm lên 1 đơn vị.
* **`put(...)`**: Cập nhật lại giá trị mới vào Map.

---

### Ví dụ luồng chạy

Nếu nội dung nhập vào là: `"apple banana apple"`

1. **Lần 1**: `nextToken()` lấy được `"apple"`. `wordCounter` chưa có "apple", nên nó lưu: `{apple: 1}`.
2. **Lần 2**: `nextToken()` lấy được `"banana"`. `wordCounter` chưa có "banana", nên nó lưu thêm: `{apple: 1, banana: 1}`.
3. **Lần 3**: `nextToken()` lấy được `"apple"`. `wordCounter` **đã có** "apple" với giá trị là 1, nó cộng thêm 1: `{apple: 2, banana: 1}`.

Cuối cùng, phương thức `displayResult` trong `CounterView` sẽ in kết quả này ra màn hình cho người dùng thấy.

Bạn có muốn mình hướng dẫn cách thay thế `StringTokenizer` bằng phương thức `split()` hiện đại hơn không?
