package viethung.repositories.impl;

import viethung.models.Category;

import java.util.List;

public interface CategoryRepository {
    Category insert(Category category);
    Category update(Category category, String categoryId);
    Category delete(String categoryId);
    List<Category> getAll();
    Category getById(String categoryId);
    Category getByCode(String categoryCode);
}
