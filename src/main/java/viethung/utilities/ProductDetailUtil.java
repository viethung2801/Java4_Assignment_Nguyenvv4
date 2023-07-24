package viethung.utilities;

import viethung.models.ProductDetail;
import viethung.repositories.ProductRepositoryImpl;
import viethung.repositories.impl.ProductRepository;

public class ProductDetailUtil {
    public static String validateInsert(ProductDetail productDetail) {
        ProductRepository productRepo = new ProductRepositoryImpl();
        if (productDetail.getProduct().getCode() == null || productDetail.getProduct().getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (productDetail.getProduct().getName() == null || productDetail.getProduct().getName().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (productDetail.getCost() > productDetail.getPrice()) {
            return "Fail! Cost not be less than Price";
        }
        if (productRepo.getByCode(productDetail.getProduct().getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }

    public static String validateUpdate(ProductDetail productDetail) {
        ProductRepository productRepo = new ProductRepositoryImpl();
        if (productDetail.getProduct().getCode() == null || productDetail.getProduct().getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (productDetail.getProduct().getName() == null || productDetail.getProduct().getName().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (productDetail.getCost() > productDetail.getPrice()) {
            return "Fail! Cost not be less than Price";
        }
        return null;
    }
}
