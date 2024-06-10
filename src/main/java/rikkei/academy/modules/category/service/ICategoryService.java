package rikkei.academy.modules.category.service;


import rikkei.academy.modules.IGeneric;
import rikkei.academy.modules.category.Category;

import java.util.List;

public interface ICategoryService extends IGeneric<Category,Integer> {
    List<Category> findByName (String name);
    List<Category> findAll(int page, int size, String search);
    int getTotalPage(int size, int len);
    boolean checkExistByName(String name);
}
