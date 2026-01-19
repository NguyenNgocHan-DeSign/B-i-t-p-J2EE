# 📚 Hệ Thống Quản Lý Sách (Book Management System)

## 📋 Mô tả bài tập
Chương trình Java quản lý thông tin sách với các chức năng:
1. ✅ Thêm 1 cuốn sách
2. ❌ Xóa 1 cuốn sách
3. ✏️ Thay đổi sách
4. 📖 Xuất thông tin tất cả sách
5. 🔍 Tìm sách có tựa đề chứa "lập trình"
6. 💰 Lấy K cuốn sách có giá ≤ P
7. 👤 Tìm sách theo danh sách tác giả

## 🏗️ Cấu trúc
- **Book.java**: Class đại diện cho một cuốn sách
  - Thuộc tính: `id`, `title`, `author`, `price`
  - Phương thức: getters, setters, `input()`, `output()`
  
- **BookManagement.java**: Chương trình chính với menu
  - Sử dụng `ArrayList<Book>` để lưu trữ
  - Sử dụng **Stream API** (filter, limit, forEach)
  - Sử dụng **Switch Expression**
  - Sử dụng **Lambda Expressions**

## ▶️ Cách chạy

### Biên dịch:
```bash
javac Book.java BookManagement.java
```

### Chạy chương trình:
```bash
java BookManagement
```

## 💡 Ví dụ sử dụng

### Thêm sách (Chọn 1):
```
Nhập mã sách: 1
Nhập tên sách: Lập trình Java cơ bản
Nhập Tác giả: Nguyễn Văn A
Nhập Đơn giá: 150000
```

### Tìm sách "lập trình" (Chọn 5):
Tự động hiển thị tất cả sách có tựa đề chứa "lập trình" (không phân biệt hoa thường)

### Lấy sách theo giá (Chọn 6):
```
Nhập số lượng sách cần lấy (K): 3
Nhập mức giá tối đa (P): 200000
→ Hiển thị tối đa 3 sách đầu tiên có giá ≤ 200,000đ
```

### Tìm theo tác giả (Chọn 7):
```
Nhập số lượng tác giả: 2
Nhập tên tác giả thứ 1: Nguyễn Văn A
Nhập tên tác giả thứ 2: Trần Thị B
→ Hiển thị tất cả sách của 2 tác giả này
```

## 🎯 Các kỹ thuật được sử dụng
- ✅ Switch Expression (Java 14+)
- ✅ Lambda Expressions
- ✅ Stream API: `filter()`, `limit()`, `forEach()`, `findFirst()`, `orElse()`
- ✅ Method Reference: `Book::output`
- ✅ Text Block ("""...""") cho menu
- ✅ String.format() cho output
- ✅ Set<String> để so sánh tác giả

## 📝 Lưu ý
- Chương trình yêu cầu **Java 14+** để sử dụng Switch Expression và Text Block
- Tìm kiếm không phân biệt hoa thường (sử dụng `.toLowerCase()`)
- Sử dụng `Scanner.nextLine()` để tránh lỗi đọc input
