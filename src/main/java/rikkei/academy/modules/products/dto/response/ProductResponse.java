package rikkei.academy.modules.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.products.Product;

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
    private Integer stock;
    private Date createdAt;
    private Category category;

    public ProductResponse(Product product) {
        this.id  = product.getId();
        this.name = product.getName();
        this.createdAt = product.getCreatedAt();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.category = product.getCategoryId();
    }

}
