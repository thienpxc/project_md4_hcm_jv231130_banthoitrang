package rikkei.academy.modules.category.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.category.Category;

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
}
