package rikkei.academy.modules.products.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.ProductImages;

import java.util.List;

@Repository
public class ProductDaolmpl implements IProductDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("from Product where status = true ", Product.class).list();
    }

    @Override
    public Product findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class,id);
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class,id);
        if (product != null){
            product.setStatus(false);
            session.update(product);
        }
    }

    @Override
    public void saveProductImages(ProductImages productImages) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productImages);
    }

    @Override
    public List<Product> findByPagination(Integer page, Integer size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product where status = true", Product.class)
                .setMaxResults(size)
                .setFirstResult(page*size)
                .list();
    }

    @Override
    public List<Product> searchByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Product p where p.status = true and p.name like concat('%',:key,'%')", Product.class)
                .setParameter("key",keyword)
                .list();
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select count(p) from Product p where p.status = true", Long.class).getSingleResult();
    }

    @Override
    public boolean existByName(String name) {

        Session session = sessionFactory.getCurrentSession();

        return !session.createQuery("from Product where name like :name")
                .setParameter("name",name).list().isEmpty();
    }
}
