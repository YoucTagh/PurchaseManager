package com.youctagh.purchasemanager.frontend.service.category;

import com.youctagh.purchasemanager.frontend.domain.Category;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/**
 * @author YoucTagh
 */
public class CategoryServiceImplMap implements CategoryService {

    private final HashMap<Long, Category> categories;
    private static Long lastID = 0L;

    public CategoryServiceImplMap() {
        this.categories = new HashMap<>();
        initMap();
    }

    private void initMap() {
        lastID++;
        categories.put(lastID, (Category) new Category().setName("Fruit").setId(lastID));
        lastID++;
        categories.put(lastID, (Category) new Category().setName("Vegetable").setId(lastID));
        lastID++;
        categories.put(lastID, (Category) new Category().setName("Meat").setId(lastID));
        lastID++;
        categories.put(lastID, (Category) new Category().setName("Cake").setId(lastID));
        lastID++;
        categories.put(lastID, (Category) new Category().setName("Others").setId(lastID));
    }


    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public HashSet<Category> findAll() {
        return new HashSet<>(categories.values());
    }

    @Override
    public Optional<Category> addCategory(Category category) {
        for (Category item : categories.values()) {
            if (item.getName().equals(category.getName()))
                return Optional.empty();
        }
        lastID++;
        category.setId(lastID);
        categories.put(lastID, category);
        return Optional.of(category);
    }

    @Override
    public Optional<Category> deleteCategory(Category category) {
        return Optional.of(categories.remove(category.getId()));
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        for (Category item : categories.values()) {
            if (item.getName().equals(category.getName()))
                return Optional.empty();
        }
        final Category categoryOld = categories.get(category.getId());
        categories.remove(category.getId());
        categories.put(category.getId(), category);
        return Optional.of(categoryOld);
    }
}
