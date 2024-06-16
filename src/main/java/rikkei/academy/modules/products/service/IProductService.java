package rikkei.academy.modules.products.service;


import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.ProductImages;
import rikkei.academy.modules.products.dto.request.ProductRequestAdd;
import rikkei.academy.modules.products.dto.request.ProductRequestUpdate;
import rikkei.academy.modules.products.dto.response.ProductResponse;
import rikkei.academy.generic.IGeneric;

import java.util.List;

public interface IProductService extends IGeneric<Product,Integer> {

    ProductRequestUpdate updatePro(Product p);

    List<Product> findAllProduct();
    List<Product> findByPagination(Integer page ,Integer limit, String category);
    void save(ProductRequestAdd request);
    List<Product> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);

    Product updateProduct(ProductRequestUpdate pro, Product product);
    public void update(Product product);
    void deleteImage(Integer id);
    List<Product> findByCategory(Integer categoryId);
    ProductImages findImageById(Integer id);
    List<Product> findNewestProduct(Integer page, Integer size);
    List<Product> findOldestProducts(Integer page, Integer size);



}
