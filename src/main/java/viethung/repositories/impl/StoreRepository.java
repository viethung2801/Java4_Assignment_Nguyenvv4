package viethung.repositories.impl;

import viethung.models.Store;

import java.util.List;

public interface StoreRepository {
    Store insert(Store store);
    Store update(Store store,String storeId);
    Store delete(String storeId);
    Store getById(String storeId);
    List<Store> getAll();
    Store getByCode(String storeCode);

}
