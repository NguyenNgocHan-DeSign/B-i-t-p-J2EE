import java.util.*;
import java.util.stream.Collectors;

public class BookManagement {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);
        String msg = """
                
                Chương trình quản lý sách
                    1. Thêm 1 cuốn sách
                    2. Xóa 1 cuốn sách
                    3. Thay đổi sách
                    4. Xuất thông tin
                    5. Tìm sách lập trình
                    6. Lấy sách tối đa theo giá
                    7. Tìm kiếm theo tác giả
                    0. Thoát
                Chọn chức năng: """;

        int chon = 0;
        do {
            System.out.print(msg);
            chon = x.nextInt();
            x.nextLine(); // Đọc bỏ dòng thừa

            switch (chon) {
                case 1 -> {
                    // Thêm 1 cuốn sách
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("✓ Đã thêm sách thành công!");
                }

                case 2 -> {
                    // Xóa 1 cuốn sách
                    System.out.print("Nhập vào mã sách cần xóa: ");
                    int bookId = x.nextInt();
                    x.nextLine();
                    
                    // Tìm và xóa sách
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElse(null);
                    
                    if (find != null) {
                        listBook.remove(find);
                        System.out.println("✓ Đã xóa sách thành công!");
                    } else {
                        System.out.println("✗ Không tìm thấy sách với mã: " + bookId);
                    }
                }

                case 3 -> {
                    // Thay đổi sách
                    System.out.print("Nhập vào mã sách cần cập nhật: ");
                    int bookId = x.nextInt();
                    x.nextLine();
                    
                    Book find = listBook.stream()
                            .filter(p -> p.getId() == bookId)
                            .findFirst()
                            .orElse(null);
                    
                    if (find != null) {
                        System.out.println("Thông tin sách hiện tại:");
                        find.output();
                        System.out.println("\nNhập thông tin mới:");
                        find.input();
                        System.out.println("✓ Đã cập nhật sách thành công!");
                    } else {
                        System.out.println("✗ Không tìm thấy sách với mã: " + bookId);
                    }
                }

                case 4 -> {
                    // Xuất thông tin tất cả sách
                    System.out.println("\n=== DANH SÁCH TẤT CẢ SÁCH ===");
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách rỗng!");
                    } else {
                        listBook.forEach(Book::output);
                    }
                }

                case 5 -> {
                    // Tìm cuốn sách có tựa đề chứa chữ "Lập trình" (không phân biệt hoa thường)
                    System.out.println("\n=== SÁCH CÓ TỰA ĐỀ CHỨA 'LẬP TRÌNH' ===");
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                            .toList();
                    
                    if (list5.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào!");
                    } else {
                        list5.forEach(Book::output);
                    }
                }

                case 6 -> {
                    // Lấy sách: Nhập vào 1 số K và giá sách P mong muốn tìm kiếm
                    // Hãy lấy tối đa K cuốn sách đầu thỏa mãn có giá sách <= P
                    System.out.print("Nhập số lượng sách cần lấy (K): ");
                    int K = x.nextInt();
                    System.out.print("Nhập mức giá tối đa (P): ");
                    double P = x.nextDouble();
                    x.nextLine();
                    
                    System.out.println("\n=== TỐI ĐA " + K + " SÁCH CÓ GIÁ <= " + P + " ===");
                    List<Book> list6 = listBook.stream()
                            .filter(p -> p.getPrice() <= P)
                            .limit(K)
                            .toList();
                    
                    if (list6.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào!");
                    } else {
                        list6.forEach(Book::output);
                    }
                }

                case 7 -> {
                    // Nhập vào 1 danh sách các tác giả từ bàn phím
                    // Hãy cho biết tất cả cuốn sách của những tác giả này
                    System.out.print("Nhập số lượng tác giả: ");
                    int n = x.nextInt();
                    x.nextLine();
                    
                    Set<String> authorSet = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Nhập tên tác giả thứ " + (i + 1) + ": ");
                        String author = x.nextLine();
                        authorSet.add(author.toLowerCase());
                    }
                    
                    System.out.println("\n=== SÁCH CỦA CÁC TÁC GIẢ ĐÃ NHẬP ===");
                    List<Book> list7 = listBook.stream()
                            .filter(p -> authorSet.contains(p.getAuthor().toLowerCase()))
                            .toList();
                    
                    if (list7.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào!");
                    } else {
                        list7.forEach(Book::output);
                    }
                }

                case 0 -> {
                    System.out.println("Thoát chương trình. Tạm biệt!");
                }

                default -> {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                }
            }
        } while (chon != 0);
        
        x.close();
    }
}
