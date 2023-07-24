package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Color;
import viethung.repositories.impl.ColorRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class ColorRepositoryImpl implements ColorRepository {

    @Override
    public Color insert(Color color) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        Color cResult = null;
        try {
            transaction.begin();
            em.persist(color);
            cResult = color;
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            em.close();
        }
        return cResult;
    }

    @Override
    public Color update(Color color, String colorId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        Color cResult = null;
        try {
            transaction.begin();
            cResult = em.find(Color.class,colorId);
            cResult.setName(color.getName());
            cResult.setCode(color.getCode());

            em.merge(cResult);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            em.close();
        }
        return cResult;
    }

    @Override
    public Color delete(String colorId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        Color cResult = null;
        try {
            transaction.begin();
            cResult = em.find(Color.class,colorId);
            em.remove(cResult);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return cResult;
    }

    @Override
    public List<Color> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<Color> colors = null;
        try {
            String jpql = "select c from Color c";
            TypedQuery<Color> query = em.createQuery(jpql, Color.class);
            colors = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return colors;
    }

    @Override
    public Color getById(String colorId) {
        EntityManager em = JpaUtil.getConnection();
        Color cResult = null;
        try {
            cResult = em.find(Color.class,colorId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return cResult;
    }

    @Override
    public Color getByCode(String colorCode) {
        EntityManager em = JpaUtil.getConnection();
        Color cResult = null;
        try {
            String jpql = "select c from Color c where c.code = :colorCode";
            TypedQuery<Color> query = em.createQuery(jpql,Color.class);
            query.setParameter("colorCode",colorCode);
            cResult = query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return cResult;
    }
}
