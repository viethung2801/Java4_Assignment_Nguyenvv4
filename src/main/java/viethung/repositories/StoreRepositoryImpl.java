package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Store;
import viethung.repositories.impl.StoreRepository;
import viethung.utilities.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreRepositoryImpl implements StoreRepository {
    @Override
    public Store insert(Store store) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(store);
            transaction.commit();
            return store;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Store update(Store store, String storeId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Store storeUpdate = em.find(Store.class,storeId);

            storeUpdate.setCode(store.getCode());
            storeUpdate.setAddress(store.getAddress());
            storeUpdate.setName(store.getName());
            storeUpdate.setCity(store.getCity());
            storeUpdate.setCountry(store.getCountry());

            em.merge(storeUpdate);
            transaction.commit();
            return storeUpdate;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Store delete(String storeId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Store storeDelete = em.find(Store.class,storeId);
            em.remove(storeDelete);
            transaction.commit();
            return new Store();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Store getById(String storeId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Store.class,storeId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Store> getAll() {
        EntityManager em = JpaUtil.getConnection();
        try {
            List<Store> stores = new ArrayList<>();
            String jpql = "select o from Store o";
            TypedQuery<Store> query = em.createQuery(jpql,Store.class);
            stores = query.getResultList();
            return stores;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Store getByCode(String storeCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            Store store = null;
            String jpql = "select o from Store o where o.code = :storeCode";
            TypedQuery<Store> query = em.createQuery(jpql,Store.class);
            query.setParameter("storeCode",storeCode);
            store = query.getSingleResult();
            return store;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }
}
