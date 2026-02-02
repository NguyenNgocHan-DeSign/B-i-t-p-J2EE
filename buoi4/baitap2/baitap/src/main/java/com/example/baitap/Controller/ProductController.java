package com.example.baitap.Controller;

import com.example.baitap.Model.Product;
import com.example.baitap.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
            BindingResult result,
            @RequestParam(value = "imageProduct", required = false) MultipartFile imageProduct,
            Model model) {

        System.out.println("📦 Nhận request tạo sản phẩm: " + newProduct.getName());
        System.out.println("🖼️ File ảnh: " + (imageProduct != null ? imageProduct.getOriginalFilename() : "null"));
        System.out.println("📏 Kích thước: " + (imageProduct != null ? imageProduct.getSize() + " bytes" : "0"));

        // Nếu có lỗi validate (ví dụ: để trống tên), trả về lại trang form
        if (result.hasErrors()) {
            System.err.println("❌ Validation errors: " + result.getAllErrors());
            return "product/create";
        }

        // Nếu không lỗi, xử lý ảnh và lưu
        productService.updateImage(newProduct, imageProduct);
        productService.add(newProduct);

        System.out.println("✅ Đã thêm sản phẩm ID: " + newProduct.getId());
        return "redirect:/products";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Product updatedProduct,
            BindingResult result,
            @RequestParam(value = "imageProduct", required = false) MultipartFile imageProduct) {

        if (result.hasErrors()) {
            return "product/edit";
        }

        // Lấy sản phẩm cũ để giữ lại ảnh cũ nếu không upload ảnh mới
        Product oldProduct = productService.getById(updatedProduct.getId());
        if (oldProduct != null && (imageProduct == null || imageProduct.isEmpty())) {
            updatedProduct.setImage(oldProduct.getImage());
        } else if (imageProduct != null && !imageProduct.isEmpty()) {
            productService.updateImage(updatedProduct, imageProduct);
        }

        productService.update(updatedProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
