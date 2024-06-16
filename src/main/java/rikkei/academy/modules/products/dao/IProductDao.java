package rikkei.academy.modules.products.dao;

import rikkei.academy.generic.IGenericDao;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.ProductImages;

import java.util.List;

public interface IProductDao extends IGenericDao<Product,Integer> {
    void saveProductImages(ProductImages productImages);
    ProductImages findImageById(Integer id);
    List<Product> findByPagination(Integer page ,Integer size, String category);
    List<Product> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);

    public void update(Product product);

    void deleteImage(Integer id);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findNewestProduct(Integer page, Integer size);
    List<Product> findOldestProducts(Integer page, Integer size);

}
