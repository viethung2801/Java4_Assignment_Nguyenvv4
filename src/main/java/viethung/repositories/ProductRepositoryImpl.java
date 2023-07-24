package viethung.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Product;
import viethung.repositories.impl.ProductRepository;
import viethung.utilities.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {


    @Override
    public Product insert(Product product) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(product);
            transaction.commit();
            return product;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Product update(Product product, String productId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Product productUpdate = em.find(Product.class,productId);
            productUpdate.setCode(product.getCode());
            productUpdate.setName(product.getName());

            productUpdate = em.merge(productUpdate);
            transaction.commit();
            return productUpdate;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Product delete(String productId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Product productDelete = em.find(Product.class,productId);
            em.remove(productDelete);
            return new Product();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        EntityManager em = JpaUtil.getConnection();
        try {
            List<Product> products = new ArrayList<>();
            String jpql = "select o from Product o";
            TypedQuery<Product> query = em.createQuery(jpql,Product.class);
            products = query.getResultList();
            return products;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Product getById(String productId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Product.class,productId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Product getByCode(String productCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from Product o where o.code = :productCode";
            TypedQuery<Product> query = em.createQuery(jpql,Product.class);
            query.setParameter("productCode",productCode);
            return query.getSingleResult();
        }catch (Exception e){
//            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }
}
