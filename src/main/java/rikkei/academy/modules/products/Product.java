package rikkei.academy.modules.products;

import lombok.*;
import rikkei.academy.modules.category.models.Category;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category categoryId;
    private String description;
    private double price;
    private int stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductImages> images;
    private Date createdAt;
    private Date updatedAt;
    private boolean status = true;
    private String manufacturer;
}
