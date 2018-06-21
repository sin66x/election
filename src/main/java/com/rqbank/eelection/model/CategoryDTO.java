package com.rqbank.eelection.model;

import com.rqbank.eelection.domain.Category;

import java.util.Date;


public class CategoryDTO {
    public CategoryDTO(){

    }
    public CategoryDTO(Category category){
        loadFromDomain(category);
    }

    private int id;
    private String name;
    private Date createdDate;
    private int parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", parent=" + parent +
                '}';
    }

    public void loadFromDomain(Category category) {
        id = category.getId();
        name = category.getName();
        createdDate = category.getCreatedDate();
        if (category.getParent() != null)
            parent = category.getParent().getId();
    }

}
