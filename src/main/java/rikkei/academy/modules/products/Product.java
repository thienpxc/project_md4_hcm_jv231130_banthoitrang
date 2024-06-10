package rikkei.academy.modules.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.modules.category.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;
    private String description;
    private double price;
    private int stock;
    private Date created_at;
    private Date updated_at;
    private boolean status;
    private String image;
    private String manufacturer;

}
