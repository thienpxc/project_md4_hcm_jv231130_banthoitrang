package rikkei.academy.modules.category.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.dao.ICategoryDao;
import rikkei.academy.modules.category.dto.request.CategoryRequest;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CategoryServicelmpl implements ICategoryService{

    public static final String uploadFolderCategory = "C:\\Users\\LACKY\\Desktop\\project\\project_md4_hcm_jv231130_banthoitrang\\src\\main\\Webapp\\uploads\\category\\";

    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ServletContext servletContext;
   @Override
    public CategoryRequest findById(Integer id) {
         return new CategoryRequest(categoryDao.findById(id));
    }
    @Override
    public Category findCategoryById(Integer id){
       return categoryDao.findById(id);
    }
    @Override
    public void delete(Integer id) {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> findAllCategory() {

          return categoryDao.findAll();
    }

    @Override
    public List<Category> findByPagination(Integer page, Integer limit) {
        return categoryDao.findByPagination(page,limit);
    }

    @Override
    public void save(CategoryRequest request) {
        // chuyển đổi
        Category category = new Category();
        if (request.getId() != null){
            // neu laf chuc nang cap nhap
            category = categoryDao.findById(request.getId());
        } else {

            category.setStatus(true);
        }
        category.setName(request.getName());


        // upload mới
        if (request.getImage() != null && request.getImage().getSize() != 0){
            String uploadPath = servletContext.getRealPath("/uploads/category/");
            File folder = new File(uploadPath);
            if (!folder.exists()){
                folder.mkdirs();
            }
            String fileName = request.getImage().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getImage().getBytes(), new File(uploadPath + File.separator + fileName));
                FileCopyUtils.copy(request.getImage().getBytes(), new File(uploadFolderCategory + fileName));
                category.setImage("/uploads/category/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        categoryDao.save(category);
    }



    @Override
    public List<Category> searchByName(String keyword) {
        return categoryDao.searchByName(keyword) ;
    }

    @Override
    public long getTotalsElement() {
        return categoryDao.getTotalsElement();
    }

    @Override
    public boolean existByName(String name) {
        return categoryDao.existByName(name);
    }
}
