package com.example.baitap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Hiển thị trang chào mừng, user click "Bắt Đầu" sẽ vào /books
    }
}
