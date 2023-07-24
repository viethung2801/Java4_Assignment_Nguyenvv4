package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Staff;
import viethung.repositories.impl.StaffRepository;
import viethung.utilities.JpaUtil;

import java.util.ArrayList;
import java.util.List;

public class StaffRepositoryImpl implements StaffRepository {
    @Override
    public Staff insert(Staff staff) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(staff);
            transaction.commit();
            return staff;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Staff update(Staff staff, String staffId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Staff staffUpdate = em.find(Staff.class,staffId);
            staffUpdate.setCode(staff.getCode());
            staffUpdate.setFirstName(staff.getFirstName());
            staffUpdate.setMiddleName(staff.getMiddleName());
            staffUpdate.setLastName(staff.getLastName());
            staffUpdate.setDateBirth(staff.getDateBirth());
            staffUpdate.setGender(staff.getGender());
            staffUpdate.setPhoneNumber(staff.getPhoneNumber());
            staffUpdate.setStore(staff.getStore());
            staffUpdate.setPosition(staff.getPosition());
            staffUpdate.setStatus(staff.getStatus());
            staffUpdate.setAddress(staff.getAddress());
            staffUpdate.setPassword(staff.getPassword());

            em.merge(staffUpdate);
            transaction.commit();
            return staffUpdate;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Staff delete(String staffId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Staff staffDelete = em.find(Staff.class,staffId);
            em.remove(staffDelete);
            transaction.commit();
            return new Staff();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Staff getById(String staffId) {
        EntityManager em = JpaUtil.getConnection();

        try {
            return em.find(Staff.class,staffId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Staff> getAll() {
        EntityManager em = JpaUtil.getConnection();

        try {
            String jpql="select o from Staff o";
            TypedQuery<Staff> query = em.createQuery(jpql,Staff.class);
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public Staff getByCode(String staffCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from Staff o where o.code = :staffCode";
            TypedQuery<Staff> query = em.createQuery(jpql,Staff.class);
            query.setParameter("staffCode",staffCode);
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        return null;
    }
}
