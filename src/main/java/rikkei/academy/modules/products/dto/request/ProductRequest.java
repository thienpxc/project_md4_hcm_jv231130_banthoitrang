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
    @NotNull(message = "Danh mục không được để trống")
    private Integer catalogId;
    @NotBlank(message = "Mô tả không được để trống")
    private String des;
    @NotNull
    private double price;
    @NotNull
    private int stock;
    @NotNull
    private String manufacturer;
    private List<String> oldImageUrls;
    private List<MultipartFile> images;
    private boolean status = true;

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalogId=" + catalogId +
                ", des='" + des + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", images=" + images +
                ", status=" + status +
                '}';
    }
}
