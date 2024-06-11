package rikkei.academy.modules.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.products.Image;
import rikkei.academy.modules.products.Product;

import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Image image ;
    private Integer stock;
    private Date createdAt;
    private Category category;

    public ProductResponse(Product product) {
        this.id  = product.getId();
        this.name = product.getName();
        this.image = new Image();
        this.createdAt = product.getCreated_at();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.category = product.getCategory_id();
    }

}
