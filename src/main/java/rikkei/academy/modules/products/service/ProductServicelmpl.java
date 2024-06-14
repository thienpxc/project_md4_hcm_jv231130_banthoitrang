package rikkei.academy.modules.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.ProductImages;
import rikkei.academy.modules.products.dto.request.ProductRequestAdd;
import rikkei.academy.modules.products.dto.request.ProductRequestUpdate;
import rikkei.academy.modules.products.dto.response.OldImage;
import rikkei.academy.modules.products.dto.response.ProductResponse;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.dao.ProductDaolmpl;
import rikkei.academy.ultil.UploadFileService;

import javax.transaction.Transactional;
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
    private ICategoryService categoryService;
    @Autowired
    private UploadFileService uploadFileService;
    private final String uploadFolderProduct = "C:\\Users\\LACKY\\Desktop\\project\\project_md4_hcm_jv231130_banthoitrang\\src\\main\\Webapp\\uploads\\product\\";

    @Override
    public List<Product> findAllProduct() {
        return productDaolmpl.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDaolmpl.findById(id);

    }


    @Override
    public ProductRequestUpdate updatePro(Product p) {
        List<OldImage> oldImage =  p.getImages().stream().map(i -> new OldImage(i.getId(),i.getUrl())).collect(Collectors.toList());
        return new ProductRequestUpdate(p.getId(), p.getName(), p.getCategoryId().getId(), p.getDescription(), p.getPrice(), p.getStock(), p.getManufacturer(), oldImage, null, p.isStatus());

    }

    @Override
    public Product updateProduct(ProductRequestUpdate pro, Product product) {
        List<String> urls;
        if (pro.getImages().get(0).getSize() == 0) {
            // không thay đổi ảnh
            urls = pro.getOldImage().stream().map(OldImage::getUrl).collect(Collectors.toList());
        } else {
            // upload mới
            urls = uploadFileService.uploadFile(pro.getImages(),uploadFolderProduct).stream().map(ProductImages::getUrl).collect(Collectors.toList());
        }
        List<ProductImages> productImagesList = new ArrayList<>();
        for (String url : urls) {
            ProductImages productImages = new ProductImages();
            productImages.setUrl(url);
            productImages.setProduct(product);
            productImagesList.add(productImages);
        }
        Date date = new Date();
        return new Product(pro.getId(), pro.getName(), categoryService.findCategoryById(pro.getCatalogId()), pro.getDes(), pro.getPrice(), pro.getStock(), productImagesList, null, date, pro.isStatus(), pro.getManufacturer());
    }

    @Override
    public void update(Product product) {
        productDaolmpl.update(product);
    }

    @Override
    public void save(ProductRequestAdd request) {
        // chuyển đổi
        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setStatus(true);
        product.setName(request.getName());
        product.setDescription(request.getDes());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategoryId(categoryService.findCategoryById(request.getCatalogId()));
        product.setManufacturer(request.getManufacturer());
        // upload mới
        List<ProductImages> productImagesList = new ArrayList<>();
        List<String> urls = uploadFileService.uploadFile(request.getImages(), uploadFolderProduct).stream().map(ProductImages::getUrl).collect(Collectors.toList());
        for (String url : urls) {
            ProductImages productImages = new ProductImages();
            productImages.setUrl(url);
            productImages.setProduct(product);
            productImagesList.add(productImages);
        }
        product.setImages(productImagesList);
        productDaolmpl.save(product);
    }

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

    @Override

    public void deleteImage(Integer id) {
        productDaolmpl.deleteImage(id);
    }
        @Override
    public List<Product> findByCategory(Integer categoryId) {
        return productDaolmpl.findByCategoryId(categoryId);

    }
}
