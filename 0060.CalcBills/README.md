Chào bạn, việc hiểu rõ "tại sao" chúng ta viết code như vậy quan trọng hơn việc chỉ viết cho nó chạy. Dưới đây là bảng phân tích chi tiết cấu trúc mã nguồn theo mô hình MVC mà tôi đã thiết kế cho bạn.

---

## 1. Phân tích Mô hình MVC (Kiến trúc)

Mô hình này chia chương trình thành 3 phần biệt lập:

* **Model (Dữ liệu):** Giống như cái kho và máy tính bỏ túi. Nó giữ dữ liệu và tính toán, không quan tâm giao diện trông thế nào.
* **View (Giao diện):** Giống như cái màn hình và bàn phím. Nó chỉ làm nhiệm vụ nhập/xuất dữ liệu với người dùng.
* **Controller (Điều khiển):** Giống như người quản lý. Nó lấy dữ liệu từ View, đưa cho Model xử lý, rồi bảo View hiển thị kết quả.

---

## 2. Giải thích chi tiết các thành phần

### A. Lớp `WalletModel` (Trái tim của logic)

* **`private int[] bills` & `private int wallet**`: Sử dụng `private` để đảm bảo tính **đóng gói (Encapsulation)**. Dữ liệu không bị sửa đổi tùy tiện từ bên ngoài.
* **`long calcTotal()`**:
* **Lý do dùng `long**`: Đây là điểm tối ưu quan trọng. Một số `int` trong Java có giới hạn tối đa là . Nếu bạn có nhiều hóa đơn lớn, tổng của chúng dễ dàng vượt qua con số này (gây hiện tượng **Overflow - tràn số**). Dùng `long` giúp chứa được con số khổng lồ lên đến .


* **`canPay(long total)`**: Một hàm logic đơn giản trả về `boolean` (true/false). Việc tách riêng hàm này giúp Model có thể được tái sử dụng cho các mục đích khác (ví dụ: kiểm tra điều kiện vay vốn).

### B. Lớp `WalletView` (Tối ưu hóa trải nghiệm người dùng)

Đây là nơi xử lý các yêu cầu khắt khe của bạn về "chữ, ký tự, số quá lớn".

* **`String input = in.nextLine().trim()`**:
* `trim()`: Loại bỏ các dấu cách thừa ở đầu và cuối (ví dụ: `"  100  "` thành `"100"`). Giúp code chạy ổn định hơn.


* **Cơ chế Try-Catch lồng nhau**:
* **Lớp 1 (`Integer.parseInt`)**: Kiểm tra xem chuỗi nhập vào có phải số nguyên hợp lệ không.
* **Lớp 2 (`new BigInteger(input)`)**: Nếu không phải `int`, ta dùng `BigInteger` để kiểm tra. Nếu `BigInteger` nhận diện được nhưng `parseInt` thất bại, chứng tỏ người dùng nhập **số quá lớn**. Nếu cả hai đều thất bại, chứng tỏ người dùng nhập **chữ hoặc ký tự đặc biệt**.


* **`System.err.println`**: In ra dòng chữ màu đỏ (trên hầu hết các IDE) để cảnh báo lỗi, giúp người dùng dễ phân biệt với thông báo thông thường.

### C. Lớp `WalletController` (Sợi dây liên kết)

* **Constructor `WalletController(view, model)**`: Sử dụng cơ chế **Dependency Injection**. Thay vì khởi tạo Model/View bên trong Controller, ta "tiêm" chúng từ ngoài vào. Điều này giúp code linh hoạt, dễ viết Unit Test sau này.
* **Hàm `processPayment()**`: Đóng vai trò như một "kịch bản". Nó điều phối luồng chạy từ lúc bắt đầu cho đến khi kết thúc.

---

## 3. Các kiểu dữ liệu được sử dụng

| Kiểu dữ liệu | Tại sao chọn? |
| --- | --- |
| `int` | Dùng cho giá trị từng hóa đơn lẻ và số lượng hóa đơn vì chúng thường không quá  tỷ. |
| `long` | Dùng cho **Tổng (Total)** để tránh lỗi tràn số khi cộng dồn nhiều hóa đơn. |
| `boolean` | Dùng cho kết quả kiểm tra (Đủ tiền hay Không đủ tiền). |
| `int[]` (Array) | Dùng để lưu danh sách hóa đơn vì số lượng hóa đơn thường được xác định ngay từ đầu. |
| `String` | Dùng để nhận dữ liệu thô từ bàn phím trước khi kiểm tra (Validation). |

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Chào bạn, để bạn nắm vững bản chất, mình sẽ "phẫu thuật" từng hàm một theo khía cạnh kỹ thuật: **Mục đích**, **Logic xử lý** và **Tại sao lại viết như vậy**.

---

### 1. Lớp `WalletView` (Xử lý tương tác)

#### Hàm `checkInputInt(String msg)`

* **Mục đích**: Đảm bảo dữ liệu nhập vào luôn là số nguyên dương hợp lệ.
* **Giải thích logic**:
* Sử dụng vòng lặp `while(true)`: Tạo một vòng lặp vô tận, chỉ thoát ra (`return`) khi dữ liệu hoàn toàn sạch.
* `in.nextLine().trim()`: Đọc cả dòng dưới dạng chuỗi và cắt bỏ khoảng trắng ở hai đầu. Việc dùng `nextLine()` thay vì `nextInt()` giúp tránh lỗi "trôi lệnh" (nuốt dòng) thường gặp trong Java.
* **Phân tích lỗi (Exception Handling)**:
* `Integer.parseInt(input)`: Cố gắng ép kiểu chuỗi sang số. Nếu thất bại, nó sẽ ném ra `NumberFormatException`.
* Cơ chế kiểm tra `BigInteger`: Khi xảy ra lỗi ép kiểu, hàm sẽ kiểm tra xem chuỗi đó có phải là một số quá lớn (vượt giới hạn 2 tỷ) hay không. Nếu đúng là số nhưng quá lớn, báo lỗi "Số quá lớn". Nếu không phải số, báo lỗi "Sai định dạng".





---

### 2. Lớp `WalletModel` (Xử lý nghiệp vụ)

#### Hàm `calcTotal()`

* **Mục đích**: Tính tổng tiền của tất cả hóa đơn có trong mảng.
* **Giải thích logic**:
* Sử dụng vòng lặp **For-each** (`for (int bill : bills)`): Đây là cách viết hiện đại, giúp code ngắn gọn và tránh lỗi chỉ số mảng (Index Out Of Bounds).
* Biến `total` kiểu `long`: Như đã nói, dùng `long` là để "phòng thủ". Nếu bạn có 10 hóa đơn, mỗi cái 500 triệu, tổng sẽ là 5 tỷ (vượt mức `int`). Nếu dùng `int`, kết quả sẽ bị âm hoặc sai lệch hoàn toàn do tràn bit.



#### Hàm `canPay(long total)`

* **Mục đích**: Trả lời câu hỏi "Ví có đủ tiền không?".
* **Giải thích logic**:
* Hàm trả về kiểu `boolean` (đúng hoặc sai).
* Việc tách riêng hàm này giúp code có tính **Declarative** (tính tuyên bố). Thay vì viết `if (wallet >= total)` ở khắp nơi, bạn chỉ cần gọi `model.canPay()`, giúp code giống như ngôn ngữ tự nhiên.



---

### 3. Lớp `WalletController` (Điều phối)

#### Hàm `processPayment()`

* **Mục đích**: Đây là hàm "đạo diễn", quy định trình tự các bước của chương trình.
* **Giải thích logic**:
1. Gọi View để hiển thị lời chào.
2. Gọi View để lấy số lượng hóa đơn, sau đó khởi tạo mảng `int[] bills`.
3. Dùng vòng lặp gọi View liên tục để lấy giá trị từng hóa đơn.
4. Đẩy dữ liệu vào Model qua các hàm `set`.
5. Yêu cầu Model tính toán, sau đó lấy kết quả đó đưa lại cho View hiển thị.



---

### 4. Tại sao các biến được đặt tên như vậy?

* **`private final Scanner in`**:
* `private`: Không cho lớp khác can thiệp vào bộ đọc của View.
* `final`: Đảm bảo biến `in` chỉ được khởi tạo một lần duy nhất, tránh lãng phí bộ nhớ.


* **`size` vs `bills.length**`:
* `size` là biến tạm để khởi tạo mảng ban đầu. Sau đó, ta dùng `bills.length` để duyệt mảng nhằm đảm bảo luôn duyệt đúng số lượng phần tử thực tế của mảng đó.


* **`System.err` thay vì `System.out**`:
* Trong lập trình Java, `System.err` (Standard Error) được dùng riêng cho thông báo lỗi. Nó thường xuất hiện với màu đỏ trong console, giúp người dùng nhận diện ngay lập tức họ đã nhập sai ở đâu.



---

### Tóm tắt luồng dữ liệu (Data Flow)

1. **View** (Nhận chuỗi)  **Validation** (Kiểm tra số/chữ)  **Controller**.
2. **Controller**  **Model** (Lưu trữ và cộng tổng).
3. **Model**  **Controller** (Kết quả True/False).
4. **Controller**  **View** (In ra màn hình cho người dùng).

Bạn có muốn mình hướng dẫn cách viết hàm **Unit Test** để kiểm tra tự động xem hàm `calcTotal` có tính đúng khi gặp số cực lớn không?
