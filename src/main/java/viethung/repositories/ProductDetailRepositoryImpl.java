package viethung.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import viethung.models.ProductDetail;
import viethung.repositories.impl.ProductDetailRepository;
import viethung.utilities.JpaUtil;

import java.util.List;

public class ProductDetailRepositoryImpl implements ProductDetailRepository {
    @Override
    public ProductDetail insert(ProductDetail productDetail) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(productDetail);
            transaction.commit();
            return productDetail;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public ProductDetail update(ProductDetail productDetail, String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ProductDetail productDetailUpdate = em.find(ProductDetail.class,productDetailId);

            productDetailUpdate.setProduct(productDetail.getProduct());
            productDetailUpdate.setProducer(productDetail.getProducer());
            productDetailUpdate.setCategory(productDetail.getCategory());
            productDetailUpdate.setColor(productDetail.getColor());
            productDetailUpdate.setDescription(productDetail.getDescription());
            productDetailUpdate.setCost(productDetail.getCost());
            productDetailUpdate.setPrice(productDetail.getPrice());
            productDetailUpdate.setQuantity(productDetail.getQuantity());
            productDetailUpdate.setWarrantyYear(productDetail.getWarrantyYear());

            em.merge(productDetailUpdate);
            transaction.commit();
            return productDetailUpdate;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public ProductDetail delete(String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            ProductDetail productDetailDelete = em.find(ProductDetail.class,productDetailId);
            em.remove(productDetailDelete);
            transaction.commit();
            return new ProductDetail();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<ProductDetail> getAll() {
        EntityManager em = JpaUtil.getConnection();
        List<ProductDetail> productDetails = null;
        try {
            String jpql = "select o from ProductDetail o";
            TypedQuery<ProductDetail> query = em.createQuery(jpql,ProductDetail.class);
            productDetails = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return productDetails;
    }

    @Override
    public ProductDetail getById(String productDetailId) {
        EntityManager em = JpaUtil.getConnection();
        try {
            return em.find(ProductDetail.class,productDetailId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public ProductDetail getByCode(String productDetailId) {
        return null;
    }
}
