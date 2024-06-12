package rikkei.academy.modules.category;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.products.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String Image;
    private Boolean status=true;
}
