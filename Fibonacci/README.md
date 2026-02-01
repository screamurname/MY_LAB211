
Hệ thống này được xây dựng để giải quyết bài toán hiển thị dãy số Fibonacci bằng cách kết hợp sức mạnh của cấu trúc lập trình hướng đối tượng (MVC) và thuật toán đệ quy tối ưu. Dưới đây là phân tích chi tiết cách vận hành của từng thành phần:

### 1. FibonacciView: Giao diện và Kiểm soát đầu vào

Lớp này đóng vai trò là chốt chặn đầu tiên, đảm bảo chương trình không bao giờ bị "vỡ" khi người dùng thao tác sai.

* **Xử lý ngoại lệ (Input Validation):** Sử dụng vòng lặp `while (true)` kết hợp với `try-catch` để xử lý lỗi nhập chữ. Khi người dùng nhập một ký tự không phải số, `Integer.parseInt` sẽ ném ra lỗi `NumberFormatException`, chương trình lập tức bắt lấy lỗi này và yêu cầu nhập lại thay vì bị dừng đột ngột.
* **Giới hạn phần cứng:** Hàm kiểm soát giá trị nhập trong khoảng từ 0 đến 92. Đây là con số tính toán kỹ lưỡng vì số Fibonacci thứ 93 sẽ vượt quá khả năng lưu trữ của kiểu dữ liệu `long` (64-bit), gây ra hiện tượng tràn số.
* **Hiển thị Base Cases:** Hàm `displayBaseCases` tách biệt việc in các số nền tảng (0 và 1) ra khỏi thuật toán đệ quy, giúp mã nguồn trở nên sáng sủa và dễ kiểm soát về mặt thẩm mỹ.

### 2. FibonacciModel: "Trái tim" Đệ quy đuôi

Đây là nơi chứa logic toán học cốt lõi với phương thức `FibonacciRecursive`.

* **Thuật toán Đệ quy đuôi (Tail Recursion):** Thay vì sử dụng đệ quy truyền thống (gọi hàm xong mới quay lại cộng), phương thức này thực hiện tính toán ngay trong tham số truyền đi: `FibonacciRecursive(n-1, higher, lower + higher)`.
* `higher`: Số hiện tại được in ra.
* `lower + higher`: Tính sẵn số tiếp theo để truyền cho lần gọi sau.


* **Hiệu suất:** Cách tiếp cận này có độ phức tạp thời gian là , tương đương với vòng lặp `for`. Nó giúp chương trình chạy cực nhanh và tránh được lỗi `StackOverflowError` thường gặp ở các hàm đệ quy sâu.
* **Điều kiện dừng:** Sử dụng `if(n < 1)` làm điểm chốt để thoát khỏi chuỗi đệ quy khi đã in đủ số lượng yêu cầu.

### 3. FibonacciController: Bộ não điều phối

Lớp này đóng vai trò "nhạc trưởng", điều khiển luồng dữ liệu giữa View và Model.

* **Phân luồng logic:** Trong hàm `run()`, bộ não này sẽ quyết định:
* Nếu `maxNumber` là 0, 1 hoặc 2: Gọi trực tiếp View để in nhanh kết quả vì đây là các trường hợp tĩnh.
* Nếu `maxNumber` lớn hơn 2: Nó sẽ ra lệnh cho View in trước "0 1", sau đó mới kích hoạt "động cơ" đệ quy ở Model để in phần còn lại.


* **Công thức kích hoạt:** Lệnh `model.FibonacciRecursive(maxNumber - 3, 1, 1)` là một sự tính toán chính xác:
* `maxNumber - 3`: Trừ đi 2 số đã in ở View và trừ thêm 1 do logic "in trước khi kiểm tra dừng" của Model.
* `1, 1`: Truyền vào số Fibonacci thứ 2 và thứ 3 để Model tiếp tục chuỗi logic từ đó.



### 4. Main: Khởi tạo hệ thống

Lớp này đơn giản là nơi lắp ráp các linh kiện lại với nhau: tạo đối tượng Model, View, truyền chúng vào Controller và bắt đầu chu trình chạy. Việc tách rời như thế này giúp bạn dễ dàng nâng cấp hoặc thay đổi giao diện (View) sau này mà không cần động vào thuật toán (Model).
