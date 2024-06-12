package rikkei.academy.modules.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.category.Category;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    private Integer id;
    @NotBlank(message = "Tên không được để trống")
    private String name;
    private MultipartFile Image;

    public CategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();

    }

}
