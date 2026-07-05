Dưới đây là toàn bộ code mẫu được biên soạn theo dạng **mẫu cú pháp thuần túy (Syntax Template)** dựa chính xác theo từng danh mục và sơ đồ kiến thức trong các bức ảnh bạn đã gửi.

Tất cả các phần được trình bày dưới dạng khung xương cú pháp chuẩn để bạn dễ dàng sao chép, điền tham số và học tập.

---

## 1. JAVA BASIC & CORE (Ảnh 8, 9)

### Cú pháp cơ bản (Basic Java)

```java
// Kiểu dữ liệu (Data Types)
int myNum = 5;
float myFloatNum = 5.99f;
char myLetter = 'D';
boolean myBool = true;
String myText = "Hello";

// Ép kiểu (Casting)
double myDouble = 9.78d;
int myInt = (int) myDouble; 

String doubleText = "12.34";
double doubleValue = Double.parseDouble(doubleText);

String intText = "200";
int intValue = Integer.parseInt(intText);

// Chuỗi (String)
String txt = "Hello World";
int length = txt.length();
String upper = txt.toUpperCase();
String lower = txt.toLowerCase();
int pos = txt.indexOf("Loc");
String concat = txt1.concat(txt2);

// Toán học (Math)
int maxVal = Math.max(5, 10);
int minVal = Math.min(5, 10);
double sqrtVal = Math.sqrt(64);
int absVal = Math.abs(-10);
int randomNum = (int)(Math.random() * 101);

```

### Mảng (Arrays)

```java
// Khai báo mảng
String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
int[] myNum = {10, 20, 30, 40};

// Truy cập và thay đổi phần tử
String carName = cars[0];
cars[0] = "Opel";

// Độ dài mảng
int arrayLength = cars.length;

```

### Phương thức (Methods)

```java
// Khai báo phương thức không trả về (void) có tham số
static void myMethod(String fname, int age) {
    // Body code
}

// Khai báo phương thức có trả về giá trị
static int myMethod(int x, int y) {
    return x + y;
}

```

### Danh sách động (ArrayList)

```java
import java.util.ArrayList;

// 1. Khởi tạo ArrayList
ArrayList<String> cars = new ArrayList<String>();

// 2. Thêm phần tử
cars.add("Volvo");
cars.add("BMW");

// 3. Truy xuất phần tử
String getCar = cars.get(0);

// 4. Cập nhật phần tử
cars.set(0, "Opel");

// 5. Xóa phần tử
cars.remove(0); // Xóa theo vị trí
cars.clear();  // Xóa toàn bộ danh sách

// 6. Kích thước kích cỡ
int size = cars.size();

// 7. Duyệt ArrayList (vòng lặp for)
for (int i = 0; i < cars.size(); i++) {
    System.out.println(cars.get(i));
}

// Duyệt ArrayList (vòng lặp for-each)
for (String i : cars) {
    System.out.println(i);
}

```

---

## 2. HTTP METHODS & SERVLET SYSTEM (Ảnh 5, 6, 7)

### Các phương thức HTTP (HTTP Methods) trong Servlet

```java
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo-method")
public class MethodServlet extends HttpServlet {

    // GET – Lấy dữ liệu
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // POST – Gửi dữ liệu
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // PUT – Cập nhật toàn bộ dữ liệu
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // PATCH – Cập nhật một phần dữ liệu
    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // DELETE – Xóa dữ liệu
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // HEAD – Yêu cầu thông tin header
    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // OPTIONS – Kiểm tra các phương thức được hỗ trợ
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }

    // TRACE – Kiểm tra dữ liệu truyền đi (vòng lặp loopback)
    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }
}

```

### Các kiểu dữ liệu phản hồi (Response Types)

```java
// 1. In thẳng dữ liệu với PrintWriter
PrintWriter out = response.getWriter();
response.setContentType("text/html");
out.println("<h1>Hello World</h1>");

// 2. Chuyển tiếp với RequestDispatcher (forward)
request.getRequestDispatcher("/path/to/page.jsp").forward(request, response);

// 3. Chuyển hướng với sendRedirect
response.sendRedirect("target-url");

// 4. File Download Response
response.setContentType("application/octet-stream");
response.setHeader("Content-Disposition", "attachment; filename=\"filename.ext\"");

// 5. Image/Media Response
response.setContentType("image/jpeg");

// 6. Error Response
response.sendError(HttpServletResponse.SC_NOT_FOUND, "Error message");

```

### Phạm vi hoạt động (Scope) trong Java Web

```java
// Page Scope (Chỉ tồn tại trong 1 trang JSP cụ thể)
pageContext.setAttribute("key", "value");

// Request Scope (Tồn tại trong vòng đời của 1 request)
request.setAttribute("key", "value");
Object reqValue = request.getAttribute("key");

// Session Scope (Tồn tại trong suốt phiên làm việc của user)
HttpSession session = request.getSession();
session.setAttribute("key", "value");
Object sessionValue = session.getAttribute("key");

// Application Scope (Tồn tại trong suốt vòng đời của ứng dụng)
ServletContext application = getServletContext();
application.setAttribute("key", "value");
Object appValue = application.getAttribute("key");

```

---

## 3. HTML VIEW COMPONENT (Ảnh 2)

```html
<!DOCTYPE html>
<html>
<head>
    <title>Page Title</title>
</head>
<body>
    <h1>This is a Heading</h1>
    <p>This is a paragraph.</p>
    <a href="url">This is a link</a>
    <img src="img.jpg" alt="description" width="100" height="100">
</body>
</html>

<p style="color:red; font-size:20px; text-align:center;">Styled Paragraph</p>

<b>Bold text</b>
<strong>Important text</strong>
<i>Italic text</i>
<mark>Highlighted text</mark>
<small>Smaller text</small>
<del>Deleted text</del>
<ins>Inserted text</ins>
<sub>Subscript text</sub>
<sup>Superscript text</sup>

<form action="/action_page.php" method="POST">
    <label for="fname">First name:</label>
    <input type="text" id="fname" name="fname"><br><br>
    <input type="submit" value="Submit">
</form>

<table border="1">
    <tr>
        <th>Header 1</th>
        <th>Header 2</th>
    </tr>
    <tr>
        <td>Data 1</td>
        <td>Data 2</td>
    </tr>
</table>

```

---

## 4. JSP COMPONENTS & SYNTAX (Ảnh 1)

### Các thẻ JSP cơ bản (Scripting Elements)

```jsp
<%-- Comment trong JSP --%>

<%! 
    // Declaration (Tuyên bố biến hoặc phương thức toàn cục)
    int sharedCounter = 0; 
    public int square(int n) { return n * n; }
%>

<% 
    // Scriptlet (Viết mã mã Java trực tiếp)
    int localVariable = 10;
    out.println("Giá trị: " + localVariable);
%>

<p>Kết quả tính toán: <%= square(5) %></p>

```

### Thỉ thị JSP (Directive)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.util.ArrayList" %>

<%@ include file="/includes/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

```

### Thẻ hành động (Action)

```jsp
<jsp:include page="/includes/footer.jsp" />

```

### Các đối tượng ngầm định (Implicit Objects)

```jsp
<%
    // request
    String param = request.getParameter("paramName");

    // response
    // response.sendRedirect("page.jsp");

    // out
    out.print("Đầu ra chuỗi");
%>

```

### Điều kiện và Vòng lặp (Conditions & Loops trong Scriptlet)

```jsp
<%-- Cấu trúc điều kiện if...else --%>
<% if (sharedCounter > 0) { %>
    <p>Lớn hơn 0</p>
<% } else { %>
    <p>Nhỏ hơn hoặc bằng 0</p>
<% } %>

<%-- Cấu trúc điều kiện switch...case --%>
<% 
    int role = 1;
    switch(role) {
        case 1:
%>
            <p>Admin</p>
<%
            break;
        default:
%>
            <p>User</p>
<%
            break;
    }
%>

<%-- Vòng lặp for --%>
<% for (int i = 0; i < 5; i++) { %>
    <p>Thứ tự: <%= i %></p>
<% } %>

<%-- Vòng lặp while --%>
<% int j = 0; while (j < 3) { %>
    <p>Giá trị j: <%= j %></p>
<% j++; } %>

```

---

## 5. JSTL - JAVA STANDARD TAG LIBRARY (Ảnh 1, 3)

### Cấu hình Taglib

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

```

### Các thẻ Core Tag cơ bản

```jsp
<%-- c:set - Gán giá trị vào biến (và có thể chỉ định scope) --%>
<c:set var="userRole" value="admin" scope="session" />
<c:set var="salary" value="${2000 * 2}" />

<%-- c:out - Xuất dữ liệu tương đương <%= %> bảo mật chống XSS --%>
<c:out value="${userRole}" default="Guest" />

<%-- c:if - Câu điều kiện đơn giản --%>
<c:if test="${salary > 3000}">
    <p>Lương cao</p>
</c:if>

<%-- c:choose, c:when, c:otherwise - Cấu trúc If...else/Switch nâng cao --%>
<c:choose>
    <c:when test="${userRole == 'admin'}">
        <p>Quyền quản trị viên</p>
    </c:when>
    <c:when test="${userRole == 'manager'}">
        <p>Quyền điều hành</p>
    </c:when>
    <c:otherwise>
        <p>Quyền người dùng thông thường</p>
    </c:otherwise>
</c:choose>

<%-- c:catch - Bắt ngoại lệ và lỗi xử lý --%>
<c:catch var="myException">
    <% int result = 10 / 0; %>
</c:catch>
<c:if test="${myException != null}">
    <p>Có lỗi xảy ra: ${myException.message}</p>
</c:if>

<%-- c:forEach - Duyệt mảng, danh sách hoặc vòng lặp đếm --%>
<c:forEach var="i" begin="1" end="5" step="1">
    <p>Lặp JSTL: ${i}</p>
</c:forEach>

<c:forEach var="item" items="${cars}" varStatus="status">
    <p>Chỉ mục ${status.index}: ${item}</p>
</c:forEach>

<%-- c:forTokens - Duyệt chuỗi được phân tách bằng dấu phân cách --%>
<c:forTokens items="Học,Java,Web,Servlet,JSP" delims="," var="token">
    <p>Phần tử tách: ${token}</p>
</c:forTokens>

```

---

## 6. EL - EXPRESSION LANGUAGE (Ảnh 4)

```jsp
<%-- Truy cập thuộc tính (Property Access) --%>
${user.name}
${user["name"]}
${productList[0].price}

<%-- Biểu thức điều kiện (Toán tử 3 ngôi) --%>
${salary > 5000 ? "Cao" : "Thấp"}

<%-- Toán tử logic (Logic Operators) --%>
${isValid && isExist}   <%-- and --%>
${role == 'admin' || role == 'editor'}   <%-- or --%>
${!isLoggedIn}   <%-- not --%>
${empty productList}   <%-- Kiểm tra null hoặc rỗng --%>

<%-- Làm việc với các Scope (Phạm vi) tường minh --%>
${pageScope.myVar}
${requestScope.myVar}
${sessionScope.myVar}
${applicationScope.myVar}

<%-- Sử dụng trong vòng lặp (kết hợp JSTL) --%>
<c:forEach var="product" items="${requestScope.productList}">
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
    </tr>
</c:forEach>

```
