package com.dank.contexts;

import com.dank.entities.Category;

public interface CategoryContext {
    Category getByName(String name);
}
