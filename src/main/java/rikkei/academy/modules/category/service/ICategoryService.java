package rikkei.academy.modules.category.service;


import rikkei.academy.generic.IGeneric;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.dto.request.CategoryRequest;

import java.util.List;

public interface ICategoryService extends IGeneric<CategoryRequest,Integer> {
    List<Category> findAllCategory();
    Category findCategoryById(Integer id);
    List<Category> findByPagination(Integer page ,Integer limit);
    void save(CategoryRequest request);
    List<Category> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);
}

