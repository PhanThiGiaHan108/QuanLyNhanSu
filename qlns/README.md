# Quản lý Nhân Sự

Phần mềm Quản lý Nhân Sự là ứng dụng Java desktop cho phép quản lý thông tin nhân viên với các chức năng CRUD (Create, Read, Update, Delete), tìm kiếm và thống kê.

## Chức năng phần mềm

1. **Quản lý thông tin nhân viên**:
   - Thêm, sửa, xóa nhân viên
   - Hiển thị danh sách nhân viên trong bảng
   - Mã nhân viên tự động tăng

2. **Tìm kiếm**:
   - Tìm kiếm theo tên gần đúng
   - Tìm kiếm theo khoảng lương

3. **Thống kê**:
   - Tổng số nhân viên
   - Nhân viên có lương cao nhất/thấp nhất
   - Lương trung bình

4. **Đăng nhập**:
   - Hệ thống yêu cầu đăng nhập trước khi sử dụng
   - Giao diện dashboard với sidebar menu

5. **Lưu trữ dữ liệu**:
   - Lưu trong file XML (employees.xml và config.xml)
   - Không sử dụng cơ sở dữ liệu

## Cách chạy phần mềm

### Sử dụng tệp batch
1. Đảm bảo máy tính có cài đặt Java Runtime Environment (JRE) phiên bản 17 trở lên
2. Để biên dịch: Double-click vào `build.bat`
3. Để chạy: Double-click vào `run.bat`

### Sử dụng tệp JAR
1. Đảm bảo máy tính có cài đặt Java Runtime Environment (JRE) phiên bản 17 trở lên
2. Tải tệp `QuanLyNhanSu.jar` về máy
3. Double-click vào tệp JAR hoặc chạy từ terminal/command prompt:
   ```
   java -jar QuanLyNhanSu.jar
   ```

### Sử dụng mã nguồn
1. Clone repository:
   ```
   git clone https://github.com/your-username/QuanLyNhanSu.git
   ```
2. Vào thư mục dự án:
   ```
   cd QuanLyNhanSu
   ```
3. Biên dịch và chạy bằng Maven:
   ```
   mvn clean install
   mvn exec:java
   ```

## Hướng dẫn sử dụng

1. **Đăng nhập**:
   - Sử dụng tài khoản mặc định: 
     - Tên đăng nhập: `admin`
     - Mật khẩu: `admin`

2. **Dashboard**:
   - Giao diện chính hiển thị sau khi đăng nhập
   - Sử dụng sidebar menu để chuyển đổi giữa các chức năng

3. **Quản lý nhân viên**:
   - Thêm nhân viên mới: Điền thông tin vào form bên trái và nhấn "Thêm"
   - Cập nhật nhân viên: Chọn nhân viên từ bảng, sửa thông tin, nhấn "Cập nhật"
   - Xóa nhân viên: Chọn nhân viên từ bảng, nhấn "Xóa" và xác nhận

4. **Tìm kiếm nhân viên**:
   - Nhập tên hoặc khoảng lương cần tìm
   - Nhấn nút "Tìm kiếm"
   - Kết quả hiển thị trong bảng

5. **Thống kê**:
   - Xem thông tin tổng số nhân viên
   - Xem thông tin về lương cao nhất, thấp nhất, trung bình

6. **Đăng xuất**:
   - Nhấn nút "Đăng xuất" trên sidebar để thoát khỏi hệ thống

## Thông tin đăng nhập
- **Tên đăng nhập**: admin
- **Mật khẩu**: 123456

## Cấu trúc dữ liệu XML

1. **employees.xml**: Lưu trữ thông tin nhân viên
2. **config.xml**: Lưu trữ danh sách phòng ban, vị trí, trình độ học vấn

## Yêu cầu hệ thống
- Java Runtime Environment (JRE) 17 trở lên
- RAM: 2GB trở lên
- Ổ cứng: 100MB trống

## Công nghệ sử dụng
- Java 17
- Swing (GUI)
- JAXB (XML Binding)
- JCalendar (Date Picker) 