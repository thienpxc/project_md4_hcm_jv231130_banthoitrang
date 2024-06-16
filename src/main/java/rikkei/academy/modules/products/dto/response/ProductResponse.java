package rikkei.academy.modules.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.modules.category.models.Category;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.ProductImages;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String manufacturer;
    private Integer stock;
    private boolean status;
    private Date createdAt;
    List<String> oldImageUrls;
    private Category category;

    public ProductResponse(Product product) {
        this.id  = product.getId();
        this.name = product.getName();
        this.createdAt = product.getCreatedAt();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.status = product.isStatus();
        this.category = product.getCategoryId();
        this.oldImageUrls = product.getImages().stream().map(ProductImages::getUrl).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", stock=" + stock +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", oldImageUrls=" + oldImageUrls +
                ", category=" + category +
                '}';
    }
}
