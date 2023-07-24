package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Customer;
import viethung.repositories.impl.CustomerRepository;
import viethung.services.impl.CustomerService;
import viethung.utilities.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer insert(Customer customer) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(customer);

            transaction.commit();
            return customer;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Customer update(Customer customer, String customerId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Customer customerUpdate = em.find(Customer.class,customerId);
            customerUpdate.setFirstName(customer.getFirstName());
            customerUpdate.setMiddleName(customer.getMiddleName());
            customerUpdate.setLastName(customer.getLastName());
            customerUpdate.setCode(customer.getCode());
            customerUpdate.setPhoneNumber(customer.getPhoneNumber());
            customerUpdate.setDateBirth(customer.getDateBirth());
            customerUpdate.setCity(customer.getCity());
            customerUpdate.setCountry(customer.getCountry());
            customerUpdate.setAddress(customer.getAddress());
            customerUpdate.setPassword(customer.getPassword());

            em.merge(customerUpdate);
            transaction.commit();
            return customerUpdate;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Customer delete(String customerId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Customer customerDelete = em.find(Customer.class,customerId);
            em.remove(customerDelete);
            transaction.commit();
            return new Customer();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        EntityManager em = JpaUtil.getConnection();
        try {
            List<Customer> customers = null;
            String jpql = "SELECT o FROM Customer o";
            TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);
            customers = query.getResultList();
            return customers;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Customer getById(String customerId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Customer.class,customerId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Customer getByCode(String customerCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            Customer customer = null;
            String jpql = "SELECT o FROM Customer o where o.code = :customerCode";
            TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);
            customer = query.getSingleResult();
            return customer;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }
}
