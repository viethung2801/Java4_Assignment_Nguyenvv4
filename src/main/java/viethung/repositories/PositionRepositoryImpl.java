package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.Position;
import viethung.repositories.impl.PositionRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class PositionRepositoryImpl implements PositionRepository {

    @Override
    public Position insert(Position position) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.persist(position);

            transaction.commit();

            return position;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Position update(Position position, String positionId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Position positionUpdate = em.find(Position.class,positionId);
            positionUpdate.setCode(position.getCode());
            positionUpdate.setName(position.getName());
            em.merge(positionUpdate);
            transaction.commit();

            return positionUpdate;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Position delete(String positionId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Position positionDelete = em.find(Position.class,positionId);
            em.remove(positionDelete);
            transaction.commit();
            return positionDelete;
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Position> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<Position> positions = null;
        try {
            String jpql = "select o from Position o";
            TypedQuery<Position> query = em.createQuery(jpql, Position.class);
            positions = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return positions;
    }

    @Override
    public Position getById(String positionId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(Position.class, positionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Position getByCode(String positionCode) {
        EntityManager em = JpaUtil.getConnection();
        try {
            String jpql = "select o from Position o where o.code = :positionCode";
            TypedQuery<Position> query = em.createQuery(jpql, Position.class);
            query.setParameter("positionCode", positionCode);
            return query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
