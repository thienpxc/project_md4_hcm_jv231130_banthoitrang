package rikkei.academy.modules.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.ProductImages;
import rikkei.academy.modules.products.dto.request.ProductRequest;
import rikkei.academy.modules.products.dto.response.ProductResponse;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.dao.ProductDaolmpl;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServicelmpl implements IProductService {
    @Autowired
    private ProductDaolmpl productDaolmpl;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ICategoryService categoryService;
    private static final String uploadFolder = "C:\\Users\\hoanc\\OneDrive\\Desktop\\project_md4_hcm_jv231130_banthoitrang\\src\\main\\Webapp\\uploads\\";

    @Override
    public List<Product> findAllProduct() {
        return productDaolmpl.findAll();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return new ProductResponse(productDaolmpl.findById(id));
    }

    @Override
    public void save(ProductRequest request) {
        // chuyển đổi
        Product product = new Product();
        if (request.getId() != null) {
            // neu laf chuc nang cap nhap
            product = productDaolmpl.findById(request.getId());
        } else {
            product.setCreatedAt(new Date());
            product.setStatus(true);
        }
        product.setName(request.getName());
        product.setDescription(request.getDes());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategoryId(categoryService.findCategoryById(request.getCatalogId()));
        product.setManufacturer(request.getManufacturer());
        // upload mới
        if (request.getImages() != null && request.getImages().size() != 0) {
            String uploadPath = servletContext.getRealPath("/uploads");
            File folder = new File(uploadPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            List<ProductImages> productImagesList = new ArrayList<>();
            for (int i = 0; i < request.getImages().size(); i++) {
                ProductImages productImages = new ProductImages();
                String fileName = request.getImages().get(i).getOriginalFilename();
                try {
                    FileCopyUtils.copy(request.getImages().get(i).getBytes(), new File(uploadPath + File.separator + fileName));
                    FileCopyUtils.copy(request.getImages().get(i).getBytes(), new File(uploadFolder + fileName));
                    productImages.setUrl("/uploads/" + fileName);
                    productImages.setProduct(product);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                productImagesList.add(productImages);
            }
            product.setImages(productImagesList);
        }
        productDaolmpl.save(product);
    }

    @Override
    public void delete(Integer id) {
        productDaolmpl.delete(id);
    }


    @Override
    public List<Product> findByPagination(Integer page, Integer limit) {
        return productDaolmpl.findByPagination(page,limit);
    }

    @Override
    public List<ProductResponse> searchByName(String keyword) {
        return productDaolmpl.searchByName(keyword).stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @Override
    public long getTotalsElement() {
        return productDaolmpl.getTotalsElement();
    }

    @Override
    public boolean existByName(String name) {
        return productDaolmpl.existByName(name);
    }
}
