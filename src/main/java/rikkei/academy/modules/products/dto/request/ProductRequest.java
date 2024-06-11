package rikkei.academy.modules.products.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.category.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private Integer id;
    @NotBlank(message = "Tên không được để trống")
    private String name;

    private Category catalogId;
    @NotBlank(message = "Mô tả không được để trống")
    private String des;
    @NotNull
    @Min(value = 0, message = "Giá không được nhỏ hơn 0")
    private double price;
    @NotNull
    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
    private int stock;
    private MultipartFile imageUrl;

}
