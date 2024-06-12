package rikkei.academy.modules.category.dao;


import rikkei.academy.generic.IGenericDao;
import rikkei.academy.modules.category.Category;

import java.util.List;

public interface ICategoryDao extends IGenericDao<Category, Integer> {
    List<Category> findByPagination(Integer page , Integer size);
    List<Category> searchByName(String keyword);
    long getTotalsElement();
    boolean existByName(String name);

}
