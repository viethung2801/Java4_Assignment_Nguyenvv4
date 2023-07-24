package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.OrderDetail;
import viethung.repositories.impl.OrderDetailRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @Override
    public List<OrderDetail> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<OrderDetail> orderDetailList = null;
        try {
            String jpql = "select o from OrderDetail o";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            orderDetailList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> getByOrderId(String orderId) {
        EntityManager em = JpaUtil.getConnection();
        List<OrderDetail> orderDetailList = null;
        try {
            String jpql = "select o from OrderDetail o where o.order.id = :orderId";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            orderDetailList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail insert(OrderDetail orderDetail) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(orderDetail);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail, String orderId, String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            //lấy ra
            String jpql = "select o from OrderDetail o where o.order.id = :orderId and o.productDetail.id = :productDetailId";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            query.setParameter("productDetailId", productDetailId);
            OrderDetail orderDetailUpdate = query.getSingleResult();
            //update
            orderDetailUpdate.setQuanltity(orderDetail.getQuanltity());
            orderDetail.setPrice(orderDetail.getPrice());

            em.merge(orderDetailUpdate);
            transaction.commit();

            return orderDetailUpdate;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void updateQuantity(String orderId, String productDetailId, int quantity) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            //lấy ra
            String jpql = "select o from OrderDetail o where o.order.id = :orderId and o.productDetail.id = :productDetailId";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            query.setParameter("productDetailId", productDetailId);
            OrderDetail orderDetailUpdate = query.getSingleResult();
            //update
            orderDetailUpdate.setQuanltity(quantity);

            em.merge(orderDetailUpdate);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String orderId, String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            String jpql = "select o from OrderDetail o where o.order.id = :orderId and o.productDetail.id = :productDetailId";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            query.setParameter("productDetailId", productDetailId);
            OrderDetail orderDetail = query.getSingleResult();

            em.remove(orderDetail);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public OrderDetail getById(String orderId, String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from OrderDetail o where o.order.id = :orderId and o.productDetail.id = :productDetailId";
            TypedQuery<OrderDetail> query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            query.setParameter("productDetailId", productDetailId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

}
