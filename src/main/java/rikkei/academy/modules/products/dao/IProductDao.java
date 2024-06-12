package rikkei.academy.modules.products.dao;

import rikkei.academy.generic.IGenericDao;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.ProductImages;

import java.util.List;

public interface IProductDao extends IGenericDao<Product,Integer> {
    void saveProductImages(ProductImages productImages);
    List<Product> findByPagination(Integer page ,Integer size);
    List<Product> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);
}
