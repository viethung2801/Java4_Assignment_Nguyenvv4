package viethung.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Category;
import viethung.repositories.impl.CategoryRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Category insert(Category category) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.persist(category);

            transaction.commit();
            return category;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public Category update(Category category, String categoryId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Category categoryUpdate = em.find(Category.class, categoryId);
            categoryUpdate.setCode(category.getCode());
            categoryUpdate.setName(category.getName());
            em.merge(categoryUpdate);

            transaction.commit();
            return category;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Category delete(String categoryId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Category cRemove = em.find(Category.class, categoryId);
            em.remove(cRemove);

            em.getTransaction().commit();
            return new Category();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<Category> categories = null;
        try {
            String jpql = "select c from Category c";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            categories = query.getResultList();

        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            em.close();
        }
        return categories;
    }

    @Override
    public Category getById(String categoryId) {
        EntityManager em = JpaUtil.getConnection();
        try {

            Category category = em.find(Category.class, categoryId);

            return category;
        } catch (Exception ex) {
//            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Category getByCode(String categoryCode) {
        EntityManager em = JpaUtil.getConnection();
        Category category = null;
        try {
            String jpql = "select c from Category c where c.code = :categoryCode";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("categoryCode",categoryCode);
            category = query.getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return category;
    }

}
