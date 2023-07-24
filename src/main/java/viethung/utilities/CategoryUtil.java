package viethung.utilities;

import viethung.models.Category;
import viethung.repositories.CategoryRepositoryImpl;
import viethung.repositories.impl.CategoryRepository;

public class CategoryUtil {
    private CategoryRepository categoryRepo = new CategoryRepositoryImpl();

    public String validateInsert(Category category) {
        if (category.getCode().trim().equals("") || category.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (category.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (category.getName().trim().equals("") || category.getName() == null) {
            return "Fail! Name is empty";
        }
        if (category.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        if (categoryRepo.getByCode(category.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }public String validateUpdate(Category category) {
        if (category.getCode().trim().equals("") || category.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (category.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (category.getCode().trim().equals("") || category.getCode() == null) {
            return "Fail! Name is empty";
        }
        if (category.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        return null;
    }
}
