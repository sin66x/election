package com.rqbank.eelection.service;

import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    int save(CategoryDTO category);
    Category findById(String id);
    void remove(String id);
}
