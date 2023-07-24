package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Producer;
import viethung.repositories.impl.ProducerRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class ProducerRepositoryImpl implements ProducerRepository {
    @Override
    public Producer insert(Producer producer) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(producer);
            transaction.commit();
            return producer;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Producer update(Producer producer, String producerId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Producer producerUpdate = em.find(Producer.class,producerId);
            producerUpdate.setCode(producer.getCode());
            producerUpdate.setName(producer.getName());
            em.merge(producerUpdate);
            transaction.commit();
            return producerUpdate;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Producer delete(String producerId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Producer producerDelete = em.find(Producer.class,producerId);
            em.remove(producerDelete);
            transaction.commit();
            return new Producer();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Producer> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<Producer> producers = null;
        try {
            String jpql = "select o from Producer o";
            TypedQuery<Producer> query = em.createQuery(jpql, Producer.class);
            producers = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return producers;
    }

    @Override
    public Producer getById(String producerId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Producer.class,producerId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Producer getByCode(String producerCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from Producer o where o.code = :producerCode";
            TypedQuery<Producer> query = em.createQuery(jpql,Producer.class);
            query.setParameter("producerCode",producerCode);
            return query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(new ProducerRepositoryImpl().insert(new Producer(null,"1","1")));
//    }
}
