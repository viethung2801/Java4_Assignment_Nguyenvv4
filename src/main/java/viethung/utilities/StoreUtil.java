package viethung.utilities;

import viethung.models.Store;
import viethung.repositories.StoreRepositoryImpl;
import viethung.repositories.impl.StoreRepository;

public class StoreUtil {
    public static String validateInsert(Store store) {
        StoreRepository storeRepo = new StoreRepositoryImpl();
        if ( store.getCode() == null || store.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (store.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if ( store.getName() == null || store.getName().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (store.getName().length() > 50) {
            return "Fail! Name more than 50 characters";
        }
        if (storeRepo.getByCode(store.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }
    public static String validateUpdate(Store store) {
        StoreRepository storeRepo = new StoreRepositoryImpl();
        if ( store.getCode() == null || store.getCode().trim().equals("")) {
            return "Fail! Code is empty";
        }
        if (store.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if ( store.getName() == null || store.getName().trim().equals("")) {
            return "Fail! Name is empty";
        }
        if (store.getName().length() > 50) {
            return "Fail! Name more than 50 characters";
        }
        return null;
    }
}
