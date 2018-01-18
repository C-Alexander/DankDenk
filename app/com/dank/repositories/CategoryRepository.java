package com.dank.repositories;

import com.dank.contexts.CategoryContext;
import com.dank.entities.Category;
import com.dank.entities.Meme;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CategoryRepository {

    private CategoryContext categoryContext;

    @Inject
    public CategoryRepository(CategoryContext categoryContext) {
        this.categoryContext = categoryContext;
    }


    public Category getCategoryByName(String name) {
        return categoryContext.getByName(name);
    }
}
