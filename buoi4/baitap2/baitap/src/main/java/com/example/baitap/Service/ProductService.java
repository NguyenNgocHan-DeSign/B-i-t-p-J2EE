package com.example.baitap.Service;

import com.example.baitap.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();
    private int nextId = 1; // Bộ đếm ID tự động tăng

    // Lấy hết danh sách
    public List<Product> getAll() {
        return listProduct;
    }

    // Thêm mới
    public void add(Product newProduct) {
        newProduct.setId(nextId++); // Tự động gán ID và tăng bộ đếm
        listProduct.add(newProduct);
    }

    // Tìm sản phẩm theo ID
    public Product getById(int id) {
        return listProduct.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Cập nhật sản phẩm
    public void update(Product updatedProduct) {
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId() == updatedProduct.getId()) {
                listProduct.set(i, updatedProduct);
                break;
            }
        }
    }

    // Xóa sản phẩm
    public void delete(int id) {
        listProduct.removeIf(product -> product.getId() == id);
    }

    // Xử lý lưu ảnh
    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (imageProduct != null && !imageProduct.isEmpty()) {
            try {
                System.out.println("🔵 Bắt đầu xử lý upload file...");

                // Lấy đường dẫn project root
                String projectDir = System.getProperty("user.dir");
                System.out.println("📂 Project dir: " + projectDir);

                // Đường dẫn lưu vào target (để Spring Boot serve ngay) - ƯU TIÊN
                Path targetDir = Paths.get(projectDir, "target", "classes", "static", "images");

                // Đường dẫn lưu vào source (để commit vào Git)
                Path sourceDir = Paths.get(projectDir, "src", "main", "resources", "static", "images");

                // Tạo cả 2 thư mục nếu chưa tồn tại
                Files.createDirectories(targetDir);
                Files.createDirectories(sourceDir);

                System.out.println("📁 Target dir: " + targetDir.toAbsolutePath());
                System.out.println("📁 Source dir: " + sourceDir.toAbsolutePath());

                // Đổi tên file để tránh trùng lặp
                String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                System.out.println("📝 New filename: " + newFileName);

                // Lưu vào TARGET trước (quan trọng nhất)
                Path targetFile = targetDir.resolve(newFileName);
                Files.copy(imageProduct.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Đã lưu vào TARGET: " + targetFile.toAbsolutePath());

                // Copy từ target sang source
                Path sourceFile = sourceDir.resolve(newFileName);
                Files.copy(targetFile, sourceFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Đã copy vào SOURCE: " + sourceFile.toAbsolutePath());

                // Lưu tên file vào object Product
                newProduct.setImage(newFileName);

                System.out.println("✅✅✅ Upload thành công!");
            } catch (IOException e) {
                System.err.println("❌❌❌ LỖI KHI LƯU FILE: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ Không có file ảnh được upload hoặc file rỗng");
        }
    }
}
