package rikkei.academy.modules.category.service;


import rikkei.academy.modules.category.Category;

import java.util.List;

public class CategoryServicelmpl implements ICategoryService{
    @Override
    public List<Category> findByName(String name) {
        return null;
    }

    @Override
    public List<Category> findAll(int page, int size, String search) {
        return null;
    }

    @Override
    public int getTotalPage(int size, int len) {
        return 0;
    }

    @Override
    public boolean checkExistByName(String name) {
        return false;
    }



    @Override
    public Category findById(Integer id) {
        return null;
    }



    @Override
    public void delete(Integer id) {

    }


}
