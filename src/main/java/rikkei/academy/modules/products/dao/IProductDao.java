package rikkei.academy.modules.products.dao;

import rikkei.academy.modules.IGenericDao;
import rikkei.academy.modules.products.Product;

import java.util.List;

public interface IProductDao extends IGenericDao<Product,Integer> {
    List<Product> findByPagination(Integer page ,Integer size);
    List<Product> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);


}
