package rikkei.academy.modules.products;

import lombok.*;
import rikkei.academy.modules.category.Category;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Category category_id;
    private String description;
    private double price;
    private int stock;
    private Date created_at;
    private Date updated_at;
    private boolean status;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image_id;
    private String manufacturer;

}
