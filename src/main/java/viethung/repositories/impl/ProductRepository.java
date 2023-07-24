package viethung.repositories.impl;


import viethung.models.Product;

import java.util.List;

public interface ProductRepository {
    Product insert(Product product);
    Product update(Product product, String productId);
    Product delete(String productId);
    List<Product> getAll();
    Product getById(String productId);
    Product getByCode(String productCode);
}
