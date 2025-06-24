Small Note Manager
🚀 Đã triển khai vào ngày 23/06/2025
Liên kết kiểm thử: https://fst.fwh.is/notes.html?i=1

Ứng dụng quản lý ghi chú đơn giản được xây dựng với Spring Boot. Đây là dự án đầu tay của tôi, một trải nghiệm học hỏi và thực hành đầy thú vị! 😄

✨ Tính năng nổi bật
Xác thực bảo mật: Đăng ký và đăng nhập người dùng với JSON Web Tokens (JWT).
Quản lý ghi chú cá nhân: Người dùng có thể tạo, chỉnh sửa, xóa và xem các ghi chú của riêng mình.
Chức năng quản trị: Quyền admin cho phép xem tất cả các ghi chú trong hệ thống.
API RESTful: Cung cấp các API RESTful an toàn và dễ sử dụng.
🛠️ Yêu cầu và Cài đặt
Yêu cầu hệ thống
Đảm bảo bạn đã cài đặt các công cụ sau:

Java 17+ ☕
Maven
MySQL
IDE: IntelliJ IDEA hoặc VS Code được khuyến nghị.
Hướng dẫn cài đặt
Làm theo các bước dưới đây để chạy ứng dụng trên máy cục bộ của bạn:

Clone dự án:

Bash

git clone https://github.com/shyhubb/Notes.git
Truy cập thư mục dự án:

Bash

cd note
Cấu hình cơ sở dữ liệu:

Tạo một cơ sở dữ liệu MySQL mới có tên smallnote.
Cập nhật tệp src/main/resources/application.properties với thông tin cơ sở dữ liệu của bạn:
<!-- end list -->

Properties

spring.datasource.url=jdbc:mysql://localhost:3306/smallnote
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-jwt-secret-key
Lưu ý: Thay thế your-jwt-secret-key bằng một khóa bí mật gồm 32 ký tự để đảm bảo an toàn.

Chạy ứng dụng:

Bash

mvn spring-boot:run
Kiểm tra API:
Ứng dụng sẽ chạy trên cổng 8080. Bạn có thể kiểm tra các API bằng Postman hoặc bất kỳ công cụ kiểm thử API nào khác. Tham khảo phần API Endpoints bên dưới để biết chi tiết.

⚙️ Công nghệ sử dụng
Backend: Spring Boot, Spring Data JPA, Spring Security
Database: MySQL
Xác thực: JSON Web Tokens (JWT)
Build Tool: Maven
📚 API Endpoints
Các API dưới đây có thể được kiểm tra bằng Postman. Các endpoint /user/* và /admin/* yêu cầu header Authorization: Bearer <token>, trong đó <token> được lấy từ endpoint /auth/login.

Xác thực
Phương thức	Endpoint	Mô tả	Request Body	Response
POST	http://localhost:8080/auth/register	Đăng ký tài khoản	{ "name": "string", "account": "string", "password": "string" }	String (ví dụ: "Create Account Success.")
POST	http://localhost:8080/auth/login	Đăng nhập, lấy JWT	{ "account": "string", "password": "string" }	{ "message": "string", "token": "string" }

Xuất sang Trang tính
Ghi chú (Dành cho Người dùng)
Phương thức	Endpoint	Mô tả	Request Body	Response
POST	http://localhost:8080/user/notes/create	Tạo ghi chú mới	{ "title": "string", "content": "string" }	String (ví dụ: "Note created.")
POST	http://localhost:8080/user/notes/update/{id}	Cập nhật ghi chú	{ "title": "string", "content": "string" }	String (ví dụ: "Update Note Success.")
POST	http://localhost:8080/user/notes/delete/{id}	Xóa ghi chú	-	String (ví dụ: "Delete Note Success.")
GET	http://localhost:8080/user/notes/view	Xem tất cả ghi chú	-	{ "message": "string", "data": [{ "note_id": number, "user_id": number, "account": "string", "title": "string", "content": "string", "date": "string" }] }
GET	http://localhost:8080/user/notes/view/details/{id}	Xem chi tiết ghi chú	-	{ "message": "string", "data": { "note_id": number, "user_id": number, "account": "string", "title": "string", "content": "string", "date": "string" } }

Xuất sang Trang tính
Admin
Phương thức	Endpoint	Mô tả	Request Body	Response
GET	http://localhost:8080/admin/notes/showall	Xem tất cả ghi chú	-	[{ "note_id": number, "user_id": number, "account": "string", "title": "string", "content": "string", "date": "string" }]

Xuất sang Trang tính
Lưu ý:

API /user/* chỉ có thể được truy cập bởi người dùng sở hữu ghi chú đó.
API /admin/* yêu cầu quyền quản trị.
📸 Ảnh chụp màn hình
(Sẽ cập nhật sớm! Bạn có thể thêm ảnh chụp màn hình Postman hoặc giao diện người dùng nếu có.)

🤝 Đóng góp
Bạn muốn đóng góp cho dự án này? Rất hoan nghênh! 😊

Fork repository này.
Tạo một nhánh mới cho tính năng của bạn: git checkout -b feature/your-feature.
Commit các thay đổi của bạn: git commit -m 'Thêm tính năng mới'.
Đẩy (push) nhánh của bạn lên: git push origin feature/your-feature.
Tạo một Pull Request.
📜 Giấy phép
Dự án này được cấp phép theo MIT License.

🙌 Lời cảm ơn
Tôi xin chân thành cảm ơn cộng đồng Spring Boot đã cung cấp những tài nguyên tuyệt vời để tôi có thể hoàn thành dự án này.

Dự án này được xây dựng với 💖 như một phần trong hành trình học Spring Boot của tôi.

Tác giả: Shyhubb – shyhubel@gmail.com
Liên kết dự án: https://github.com/shyhubb/Notes
