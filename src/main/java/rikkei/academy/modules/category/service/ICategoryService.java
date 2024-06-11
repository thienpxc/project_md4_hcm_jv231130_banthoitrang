package rikkei.academy.modules.category.service;


import rikkei.academy.modules.IGeneric;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.dto.request.CategoryRequeest;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.dto.request.ProductRequest;
import rikkei.academy.modules.products.dto.response.ProductResponse;

import java.util.List;

public interface ICategoryService extends IGeneric<CategoryRequeest,Integer> {
    List<Category> findAllCategory();
    List<Category> findByPagination(Integer page ,Integer limit);
    void save(CategoryRequeest request);
    List<Category> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);
}

