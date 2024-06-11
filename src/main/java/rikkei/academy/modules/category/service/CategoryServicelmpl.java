package rikkei.academy.modules.category.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.dao.ICategoryDao;
import rikkei.academy.modules.category.dto.request.CategoryRequeest;
import rikkei.academy.modules.products.Product;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServicelmpl implements ICategoryService{
    private static final String uploadFolder = "C:\\Users\\dokie.DOKIEU\\OneDrive\\Desktop\\md4\\src\\main\\Webapp\\uploads\\category\\";
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private ServletContext servletContext;
   @Override
    public CategoryRequeest findById(Integer id) {

         return new CategoryRequeest(categoryDao.findById(id));
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
    public void save(CategoryRequeest request) {
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
            String uploadPath = servletContext.getRealPath("/uploads");
            File folder = new File(uploadPath);
            if (!folder.exists()){
                folder.mkdirs();
            }
            String fileName = request.getImage().getOriginalFilename();
            try {
                FileCopyUtils.copy(request.getImage().getBytes(), new File(uploadPath + File.separator + fileName));
                FileCopyUtils.copy(request.getImage().getBytes(), new File(uploadFolder + fileName));
                category.setImage("/uploads/" + fileName);
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
