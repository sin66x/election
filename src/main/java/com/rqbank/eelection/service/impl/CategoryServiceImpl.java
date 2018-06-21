package com.rqbank.eelection.service.impl;

import com.rqbank.eelection.domain.Category;
import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.CategoryDTO;
import com.rqbank.eelection.repository.CategoryRepository;
import com.rqbank.eelection.service.CategoryService;
import com.rqbank.eelection.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository repository;

    @Autowired
    ElectionService electionService;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public int save(CategoryDTO categoryDTO) {
        Category category = new Category();
        if (categoryDTO.getId()!=0)
            category.setId(categoryDTO.getId());
        category.setCreatedDate(new Date());
        category.setName(categoryDTO.getName());
        Optional<Category> parent = repository.findById(categoryDTO.getParent());
        if (parent.isPresent())
            category.setParent(parent.get());
        repository.saveAndFlush(category);
        return category.getId();
    }

    @Override
    public Category findById(String id) {
        return repository.findById(Integer.parseInt(id)).get();
    }

    @Override
    public void remove(String id) {
        Optional<Category> removingCategory = repository.findById(Integer.parseInt(id));
        for (Category child: removingCategory.get().getChildren()){
            child.setParent(null);
            repository.saveAndFlush(child);
            System.out.println(">>DNE:"+child.toString());
        }

        for (Election election: removingCategory.get().getElections()){
            electionService.removeCategory(election);
        }
        repository.flush();
        removingCategory.get().setChildren(null);
        removingCategory.get().setElections(null);
        repository.delete(removingCategory.get());
        repository.flush();
    }
}
