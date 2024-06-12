package rikkei.academy.modules.products;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    private boolean status = true;
}
