package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Order;
import viethung.repositories.impl.OrderRepository;
import viethung.utilities.JpaUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public List<Order> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<Order> orders = null;
        try {
            String jpql = "select o from Order o order by o.dateCreate desc";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            orders = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return orders;
    }

    @Override
    public List<Order> getAllByStatus(int status) {
        EntityManager em = JpaUtil.getConnection();
        List<Order> orders = null;
        try {
            String jpql = "select o from Order o where o.status =:status";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("status", status);
            orders = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return orders;
    }

    @Override
    public Order insert(Order order) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(order);
//            em.persist(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Order getById(String orderId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Order.class,orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Order getByCode(String orderCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from Order o where o.code =:orderCode";
            TypedQuery<Order> query = em.createQuery(jpql, Order.class);
            query.setParameter("orderCode", orderCode);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public String nextCode() {
        EntityManager em = JpaUtil.getConnection();
        Integer quantity = null;
        try {
            String jpql = "select count(o) from Order o";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            quantity = Integer.parseInt(query.getSingleResult().toString()) + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return "HD" + new Random().nextInt();
        } finally {
            em.close();
        }
        return "HD" + quantity;
    }

    @Override
    public void delete(String orderId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Order order = em.find(Order.class,orderId);
            em.remove(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void pay(String orderId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Order order = em.find(Order.class,orderId);
            order.setStatus(1);
            order.setDatePay(new Date());
            order.setDateReceive(new Date());
            order.setDateShip(new Date());
            em.merge(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateOrder(Order order, String orderId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Order orderUpdate = em.find(Order.class,orderId);
            orderUpdate.setCustomer(order.getCustomer());
            orderUpdate.setStaff(order.getStaff());
            orderUpdate.setReceiver(order.getReceiver());
            orderUpdate.setPhoneNumber(order.getPhoneNumber());
            orderUpdate.setAddress(order.getAddress());
            em.merge(orderUpdate);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

//    public static void main(String[] args) {
//        System.out.println(new OrderRepositoryImpl().nextCode());
//    }
}
