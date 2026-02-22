## 📝 BÀI LÀM: REVIEW CODE BILL CALCULATOR

**Họ và tên:** [Tên của bạn]
**Lớp:** [Tên lớp]
**Môn:** Lập trình hướng đối tượng (OOP)

---

## I. GIỚI THIỆU BÀI TOÁN

Chương trình **Bill Calculator** là một ứng dụng console đơn giản giúp:
1. Nhập vào số lượng hóa đơn cần thanh toán
2. Nhập giá trị từng hóa đơn
3. Nhập số tiền hiện có trong ví
4. Tính tổng các hóa đơn và kiểm tra xem có đủ tiền thanh toán không
5. Hiển thị kết quả cho người dùng

---

## II. PHÂN TÍCH CẤU TRÚC MVC

Chương trình được tổ chức theo mô hình **MVC (Model-View-Controller)**:

### 🟢 **1. Model (Dữ liệu)**
- **Person.java**: Đại diện cho người dùng
- **Wallet.java**: Đại diện cho ví tiền

### 🔵 **2. View (Giao diện)**
- **View.java**: Xử lý nhập/xuất dữ liệu với người dùng

### 🟠 **3. Controller (Điều khiển)**
- **Controller.java**: Điều phối luồng hoạt động của chương trình

### ⚪ **4. Main (Khởi chạy)**
- **Main.java**: Điểm bắt đầu chương trình

---

## III. REVIEW CHI TIẾT TỪNG LỚP

### **LỚP 1: WALLET.java**

```java
package NewBillCalc;

public class Wallet {
    private int amount;  // Số tiền trong ví

    public Wallet(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
```

#### ✅ **Giải thích logic:**
- **Lớp Wallet** đại diện cho **cái ví** trong thực tế
- **Thuộc tính `amount`**: Lưu số tiền hiện có trong ví
- **Constructor `Wallet(int amount)`**: Dùng để tạo một cái ví mới với số tiền ban đầu
- **Getter `getAmount()`**: Lấy số tiền trong ví ra để xem
- **Setter `setAmount()`**: Thay đổi số tiền trong ví (nạp thêm/rút bớt)

#### 📌 **Vai trò trong OOP:**
- **Đóng gói**: Dùng `private` cho thuộc tính `amount`, không cho truy cập trực tiếp từ bên ngoài
- **Trừu tượng**: Chỉ cung cấp 2 phương thức cần thiết là get và set

---

### **LỚP 2: PERSON.java**

```java
package NewBillCalc;

public class Person {
    private Wallet wallet;  // Mỗi người có 1 cái ví

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Tính tổng các hóa đơn
     */
    public long calcTotal(int[] bills) {
        long total = 0;
        for (int bill : bills) {
            total += (long) bill;  // Chuyển sang long để tránh tràn số
        }
        return total;
    }

    /**
     * Kiểm tra xem có đủ tiền thanh toán không
     */
    public boolean payMoney(long total) {
        if (wallet == null) return false;  // Chưa có ví thì không trả được
        return wallet.getAmount() >= total;  // So sánh tiền trong ví với tổng hóa đơn
    }
}
```

#### ✅ **Giải thích logic:**
- **Lớp Person** đại diện cho **người dùng** trong thực tế
- **Thuộc tính `wallet`**: Người này có một cái ví (quan hệ HAS-A)
- **Method `calcTotal()`**: 
  - Đầu vào: mảng các hóa đơn
  - Xử lý: duyệt từng hóa đơn và cộng dồn
  - Đầu ra: tổng số tiền cần thanh toán (kiểu `long` để tránh tràn số)
- **Method `payMoney()`**:
  - Đầu vào: tổng tiền cần thanh toán
  - Xử lý: lấy số tiền trong ví ra so sánh
  - Đầu ra: `true` nếu đủ tiền, `false` nếu không đủ

#### 📌 **Vai trò trong OOP:**
- **Quan hệ HAS-A**: Person **có một** Wallet (chứ không phải Person là Wallet)
- **Ủy quyền (Delegation)**: Person nhờ Wallet kiểm tra số dư

---

### **LỚP 3: VIEW.java**

```java
package NewBillCalc;

import java.util.Scanner;

public class View {
    private final Scanner in = new Scanner(System.in);

    // Hàm nhập số nguyên dương có kiểm tra lỗi
    public int checkInputInt(String msg) {
        while (true) {
            System.out.print(msg);
            String input = in.nextLine().trim();

            // Kiểm tra rỗng
            if (input.isEmpty()) {
                System.err.println("Input cannot be empty! ");
                continue;
            }

            try {
                int result = Integer.parseInt(input);
                // Kiểm tra số dương
                if (result < 0) {
                    System.err.println("Input must be positive integer!");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                // Lỗi khi nhập chữ hoặc số quá lớn
                System.err.println("Please input a valid integer and input not too large! ");
            }
        }
    }

    // Hiển thị kết quả
    public void displayResult(long total, boolean canPay) {
        System.out.println("Total: " + total);
        if (canPay) {
            System.out.println("You can buy it!");
        } else {
            System.err.println("You cannot buy it!");
        }
    }
}
```

#### ✅ **Giải thích logic:**
- **Lớp View** chịu trách nhiệm giao tiếp với người dùng
- **Method `checkInputInt()`**: 
  - Hiển thị câu hỏi (msg)
  - Nhập dữ liệu từ bàn phím
  - Kiểm tra lỗi: rỗng, không phải số, số âm, số quá lớn
  - Nếu lỗi → yêu cầu nhập lại (vòng lặp while)
  - Nếu đúng → trả về số nguyên
- **Method `displayResult()`**: Hiển thị kết quả tính toán

#### 📌 **Vai trò trong OOP:**
- **Tách biệt giao diện**: View không biết gì về Person hay Wallet
- **Xử lý lỗi tập trung**: Tất cả validation đều ở View

---

### **LỚP 4: CONTROLLER.java**

```java
package NewBillCalc;

public class Controller {
    private View view;      // Tham chiếu đến View
    private Person person;  // Tham chiếu đến Model (Person)

    public Controller(View view, Person person) {
        this.view = view;
        this.person = person;
    }

    public void runProgram() {
        // BƯỚC 1: Nhập danh sách hóa đơn
        int size = view.checkInputInt("Input numbers of bill:  ");
        int[] bills = new int[size];
        for (int i = 0; i < size; i++) {
            bills[i] = view.checkInputInt("Value of bill " + (i + 1) + ": ");
        }

        // BƯỚC 2: Nhập số tiền trong ví
        int amount = view.checkInputInt("Input value of wallet ");
        person.setWallet(new Wallet(amount));  // Tạo ví mới và gán cho Person

        // BƯỚC 3: Xử lý tính toán
        long total = person.calcTotal(bills);      // Tính tổng hóa đơn
        boolean canPay = person.payMoney(total);   // Kiểm tra khả năng thanh toán

        // BƯỚC 4: Hiển thị kết quả
        view.displayResult(total, canPay);
    }
}
```

#### ✅ **Giải thích logic:**
- **Lớp Controller** là cầu nối giữa View và Model
- **Constructor**: Nhận vào View và Person (dependency injection)
- **Method `runProgram()`**: Điều phối toàn bộ luồng chương trình theo 4 bước:
  1. **Nhập dữ liệu** qua View
  2. **Tạo đối tượng** Wallet và gán cho Person
  3. **Xử lý nghiệp vụ** qua Person
  4. **Hiển thị kết quả** qua View

#### 📌 **Vai trò trong OOP:**
- **Điều phối (Coordination)**: Controller không làm việc cụ thể, chỉ gọi các phương thức của View và Model
- **Tách biệt (Separation)**: Model không biết View, View không biết Model

---

### **LỚP 5: MAIN.java**

```java
package NewBillCalc;

public class Main {
    public static void main(String[] args) {
        // Tạo các đối tượng
        View view = new View();
        Person person = new Person();
        
        // Kết nối Controller
        Controller controller = new Controller(view, person);
        
        // Chạy chương trình
        controller.runProgram();
    }
}
```

#### ✅ **Giải thích logic:**
- **Lớp Main** là điểm khởi đầu của chương trình
- **Method `main()`**: 
  1. Khởi tạo View và Person
  2. Tạo Controller và kết nối View với Person
  3. Gọi `runProgram()` để bắt đầu

#### 📌 **Vai trò trong OOP:**
- **Khởi tạo (Initialization)**: Tạo các đối tượng và thiết lập mối quan hệ

---

## IV. TẠI SAO LẠI TÁCH PERSON VÀ WALLET?

### **Lý do 1: Phản ánh đúng thực tế**

Trong cuộc sống:
- **Tôi** là một người (Person)
- **Tôi có** một cái ví (Wallet)
- **Tôi và ví là 2 thứ khác nhau**

Code phải phản ánh đúng điều này:
```java
Person toi = new Person();           // Tôi
Wallet viCuaToi = new Wallet(500);   // Ví của tôi có 500k
toi.setWallet(viCuaToi);              // Tôi bỏ ví vào túi
```

### **Lý do 2: Mỗi đối tượng có trách nhiệm riêng (Single Responsibility)**

- **Wallet** chỉ lo **giữ tiền**: 
  ```java
  wallet.getAmount();  // Lấy tiền ra xem
  wallet.setAmount(1000);  // Bỏ thêm tiền vào
  ```

- **Person** lo **tính toán và thanh toán**:
  ```java
  person.calcTotal(bills);   // Tính tổng hóa đơn
  person.payMoney(total);    // Kiểm tra đủ tiền không
  ```

### **Lý do 3: Dễ dàng mở rộng sau này**

Sau này có thể thêm:
- **Ví điện tử** (Momo, Zalopay)
- **Ví ngoại tệ** (USD, EUR)
- **Thẻ tín dụng** (Credit Card)

```java
// Không cần sửa Person, chỉ cần tạo thêm các lớp mới
public class MomoWallet extends Wallet {
    private String phoneNumber;
    // ... code riêng của Momo
}

public class USDTWallet extends Wallet {
    private String blockchain;
    // ... code riêng của USDT
}

// Person vẫn dùng được tất cả!
Person person = new Person();
person.setWallet(new MomoWallet("0123456789"));
person.setWallet(new USDTWallet("0x123..."));
```

### **Lý do 4: Dễ bảo trì và kiểm tra**

- **Sửa Wallet** không ảnh hưởng Person
- **Test riêng** từng lớp dễ dàng
- **Tìm lỗi** nhanh hơn

---

## V. ĐÁNH GIÁ TỔNG QUAN

### ✅ **Điểm mạnh**

1. **Cấu trúc MVC rõ ràng**: Tách biệt Model - View - Controller
2. **Đóng gói tốt**: Các thuộc đề private, chỉ truy cập qua getter/setter
3. **Xử lý lỗi chặt chẽ**: Kiểm tra đầu vào kỹ lưỡng
4. **Mở rộng được**: Có thể thêm nhiều loại ví mới
5. **Dễ đọc, dễ hiểu**: Tên biến, tên hàm rõ ràng, có comment

### ⚠️ **Điểm cần cải thiện**

1. **Thêm validation**: Nên kiểm tra số tiền không âm trong setter của Wallet
2. **Xử lý ngoại lệ**: Bắt các exception cụ thể hơn
3. **Thêm tính năng**: Ví dụ trả lại tiền thừa sau khi thanh toán

---

## VI. KẾT LUẬN

Bài code đã **áp dụng tốt các nguyên lý OOP**:
- **Đóng gói**: Dữ liệu được bảo vệ
- **Trừu tượng**: Chỉ expose những gì cần thiết
- **Kế thừa**: Có thể mở rộng sau này
- **Đa hình**: Person có thể dùng nhiều loại ví khác nhau

Đặc biệt, việc **tách Person và Wallet thành 2 class riêng** là một quyết định thiết kế đúng đắn, giúp code:
- Phản ánh đúng thực tế
- Dễ bảo trì
- Dễ mở rộng
- Tuân thủ nguyên lý "đơn trách nhiệm" (Single Responsibility Principle)

---

**📅 Ngày làm bài:** [Ngày hôm nay]
**👨‍🎓 Học viên:** [Tên của bạn]
