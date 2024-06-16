package rikkei.academy.modules.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    private final String uploadFolderProduct ="C:\\Users\\hoanc\\OneDrive\\Desktop\\project_md4_hcm_jv231130_banthoitrang\\src\\main\\Webapp\\uploads\\";





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

        List<OldImage> oldImage = p.getImages().stream().map(i -> new OldImage(i.getId(), i.getUrl())).collect(Collectors.toList());
        return new ProductRequestUpdate(p.getId(), p.getName(), p.getCategoryId().getId(), p.getDescription(), p.getPrice(), p.getStock(), p.getManufacturer(), oldImage, null, p.isStatus());
    }

    @Override
    public Product updateProduct(ProductRequestUpdate pro, Product product) {
        List<String> urls = null;
        if (pro.getImages().get(0).getSize() == 0) {
            System.out.println("không thay đổi ảnh");
            // không thay đổi ảnh
            urls = product.getImages().stream().map(ProductImages::getUrl).collect(Collectors.toList());
        } else {
            System.out.println("thay đổi ảnh");
            // upload mới
            urls = uploadFileService.uploadFile(pro.getImages(), uploadFolderProduct).stream().map(ProductImages::getUrl).collect(Collectors.toList());
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
    public List<Product> findByPagination(Integer page, Integer limit, String category) {
        return productDaolmpl.findByPagination(page, limit, category);
    }

    @Override
    public List<Product> searchByName(String keyword) {
        return productDaolmpl.searchByName(keyword);
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
        ProductImages image = productDaolmpl.findImageById(id);
        Product product = image.getProduct();
        product.getImages().remove(image);
        productDaolmpl.deleteImage(id);
    }

    @Override
    public List<Product> findByCategory(Integer categoryId) {
        return productDaolmpl.findByCategoryId(categoryId);

    }

    @Override
    public ProductImages findImageById(Integer id) {
        return productDaolmpl.findImageById(id);
    }

    @Override
    public List<Product> findNewestProduct(Integer page, Integer size){
        return productDaolmpl.findNewestProduct(page, size);
    };
    public List<Product> findOldestProducts(Integer page, Integer size){
        return productDaolmpl.findOldestProducts(page, size);
    };
}
