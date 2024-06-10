package rikkei.academy.modules.products.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.category.Category;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private Integer id;
    private String name;
    private Category catalogId;
    private String des;
    private double price;
    private int stock;
    private MultipartFile imageUrl;
    private boolean status = true;

}
