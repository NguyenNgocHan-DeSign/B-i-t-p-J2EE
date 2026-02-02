package com.example.baitap.Controller;

import com.example.baitap.Model.Book;
import com.example.baitap.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Dùng @Controller thay vì @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // <--- LƯU Ý: Đổi từ @RestController sang @Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // 1. Hiển thị danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks()); // Truyền list sách sang View
        return "book"; // Trả về file book.html
    }

    // 2. Form thêm sách mới
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book()); // Tạo đối tượng rỗng để map vào form [cite: 890]
        return "add-book"; // Trả về file add-book.html
    }

    // 3. Xử lý lưu sách mới
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books"; // Chuyển hướng về trang danh sách [cite: 897]
    }

    // 4. Form sửa sách
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "edit-book"; // Trả về file edit-book.html
        }
        return "redirect:/books";
    }

    // 5. Xử lý cập nhật sách
    @PostMapping("/edit") // [cite: 906]
    public String updateBook(@ModelAttribute Book book) { // Lưu ý: Book cần có Setter ID để binding
        bookService.updateBook(book.getId(), book);
        return "redirect:/books";
    }

    // 6. Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books"; // [cite: 916]
    }
}