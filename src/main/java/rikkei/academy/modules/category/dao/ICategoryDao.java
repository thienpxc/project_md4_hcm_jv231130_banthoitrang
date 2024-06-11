package rikkei.academy.modules.category.dao;

import rikkei.academy.modules.IGenericDao;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.dto.request.CategoryRequeest;
import rikkei.academy.modules.products.Product;

import java.util.List;

public interface ICategoryDao extends IGenericDao<Category, Integer> {
    List<Category> findByPagination(Integer page , Integer size);
    List<Category> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);

}
