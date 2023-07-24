package viethung.repositories.impl;

import viethung.models.ProductDetail;

import java.util.List;

public interface ProductDetailRepository {
    ProductDetail insert(ProductDetail productDetail);
    ProductDetail update(ProductDetail productDetail, String productDetailId);
    ProductDetail delete(String productDetailId);
    List<ProductDetail> getAll();
    ProductDetail getById(String productDetailId);
    ProductDetail getByCode(String productDetailId);
}
