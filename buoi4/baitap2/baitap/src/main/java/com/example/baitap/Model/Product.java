package com.example.baitap.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length; // Lưu ý import này

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống") // [cite: 1425]
    private String name;

    @Length(min = 0, max = 200, message = "Tên hình ảnh không quá 200 kí tự") // [cite: 1427]
    private String image;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được nhỏ hơn 1") // [cite: 1429]
    @Max(value = 9999999, message = "Giá sản phẩm không được lớn hơn 9999999") // [cite: 1430]
    private Long price;

    private Category category; // [cite: 1436]
}
