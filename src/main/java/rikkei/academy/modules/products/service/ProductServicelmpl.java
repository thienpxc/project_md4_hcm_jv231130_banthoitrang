package rikkei.academy.modules.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import rikkei.academy.modules.products.dto.request.ProductRequest;
import rikkei.academy.modules.products.dto.response.ProductResponse;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.dao.ProductDaolmpl;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private static final String uploadFolder ="C:\\Users\\dokie.DOKIEU\\OneDrive\\Desktop\\md4\\src\\main\\Webapp\\uploads\\";

    @Override
    public List<ProductResponse> findAllProduct() {
        List<Product> list = productDaolmpl.findAll();
        return list.stream().map(ProductResponse::new).collect(Collectors.toList());
    }


    @Override
    public Product findById(Integer id) {
        return null;
    }

//    @Override
//    public void save(ProductRequest request) {
//        // chuyển đổi
//        Product product = new Product();
//        if (request.getId() != null){
//            // neu laf chuc nang cap nhap
//            product = productDaolmpl.findById(request.getId());
//        } else {
//            product.setCreated_at(new Date());
//            product.setStatus(true);
//        }
//        product.setName(request.getName());
//        product.setDescription(request.getDes());
//        product.setPrice(request.getPrice());
//        product.setStock(request.getStock());
//        product.setCategory_id(request.getCatalogId());
//
//        // upload mới
//        if (request.getImageUrl() != null && request.getImageUrl().getSize() != 0){
//            String uploadPath = servletContext.getRealPath("/uploads");
//            File folder = new File(uploadPath);
//            if (!folder.exists()){
//                folder.mkdirs();
//            }
//            String fileName = request.getImageUrl().getOriginalFilename();
//            try {
//                FileCopyUtils.copy(request.getImageUrl().getBytes(), new File(uploadPath + File.separator + fileName));
//                FileCopyUtils.copy(request.getImageUrl().getBytes(), new File(uploadFolder + fileName));
//                product.setImage("/uploads/" + fileName);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        productDaolmpl.save(product);
//    }

    @Override
    public void delete(Integer id) {
        productDaolmpl.delete(id);
    }


    @Override
    public List<Product> findByPagination(Integer page, Integer limit) {
        return productDaolmpl.findByPagination(page, limit);
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
