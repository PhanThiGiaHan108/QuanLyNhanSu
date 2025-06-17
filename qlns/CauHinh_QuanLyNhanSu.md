# Cấu hình Ứng dụng Quản lý Nhân Sự

## Khôi phục cấu hình chính xác:

### ✅ Đã khôi phục lại cấu hình ban đầu:
- Main class: `com.raven.main.ApplicationLauncher` (Ứng dụng Quản lý Nhân Sự)
- Artifact ID: `QuanLyNhanSu`
- Jar file: `QuanLyNhanSu-1.0-SNAPSHOT.jar`

### ✅ Các ứng dụng trong project:

1. **Quản lý Nhân Sự** ⭐ (ĐANG CHẠY)
   - Main class: `com.raven.main.ApplicationLauncher`
   - Chức năng: Quản lý thông tin nhân viên, lương, phòng ban
   - Lưu trữ: XML files (employees.xml, config.xml)

2. **Quản lý Dân Cư** 
   - Main class: `com.mycompany.quanlydoituongdacbiet.QuanLyDoiTuong.QuanLyDoiTuongDacBiet`
   - Chức năng: Quản lý thông tin dân cư, đối tượng đặc biệt

## Cách chạy ứng dụng Quản lý Nhân Sự:

### Phương pháp 1: File JAR (Khuyến nghị)
```
.\run.bat
```

### Phương pháp 2: Maven trực tiếp
```
.\run-maven.bat
```

### Phương pháp 3: Build và chạy
```
.\build.bat
.\run.bat
```

## Thông tin đăng nhập:
- **Username**: admin
- **Password**: 123456

## ✅ Kết quả:
- Bây giờ chỉ ứng dụng **"Quản lý Nhân Sự"** sẽ được khởi chạy
- Giao diện hiện đại với dashboard và sidebar menu
- Quản lý đầy đủ thông tin nhân viên với các chức năng CRUD
- Tìm kiếm và thống kê dữ liệu nhân sự
