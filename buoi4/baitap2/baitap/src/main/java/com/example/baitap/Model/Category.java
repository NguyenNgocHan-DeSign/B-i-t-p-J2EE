package com.example.baitap.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;

    @NotBlank(message = "Tên danh mục không được để trống") // [cite: 1464]
    private String name;
}
