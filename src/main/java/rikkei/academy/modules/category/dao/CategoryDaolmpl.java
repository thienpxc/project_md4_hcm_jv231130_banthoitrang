package rikkei.academy.modules.category.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.category.models.Category;
import rikkei.academy.modules.category.models.CategoryList;

import java.util.List;

@Repository
public class CategoryDaolmpl implements ICategoryDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category where status = true ", Category.class).list();
    }

    @Override
    public Category findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class,id);
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class,id);
        session.delete(category);
    }

    @Override
    public List<Category> findByPagination(Integer page, Integer size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category where status = true", Category.class)
                .setMaxResults(size)
                .setFirstResult(page*size)
                .list();
    }

    @Override
    public List<Category> searchByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category  where status = true and name like concat('%',:key,'%')", Category.class)
                .setParameter("key",keyword)
                .list();
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Category  where status = true ",Long.class)
                .getSingleResult();
    }

    @Override
    public boolean existByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return !session.createQuery("from Category where name like :name")
                .setParameter("name",name).list().isEmpty();
    }

    @Override
    public List<CategoryList> findIdAndNameOfCategory() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select id, name from Category group by id, name";
        Query query = session.createQuery(sql);
        List<Object[]> resultList = query.list();
        List<CategoryList> categoryLists =  new  java.util.ArrayList<>();
        for (Object[] result : resultList){
            Integer id = Integer.parseInt(result[0].toString());
            String name = result[1].toString();
            categoryLists.add(new CategoryList(id,name));
        }
        return categoryLists;
    }
}
