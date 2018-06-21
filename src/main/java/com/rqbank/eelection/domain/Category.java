package com.rqbank.eelection.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/* this class has been generated automatically by HibernateGenerator */

@Entity
@Table(name = "TBL_EVOTE_CATEGORIES")
public class Category {
    private int id;
    private String name;
    private Date createdDate;
    private Category parent;
    private List<Category> children;
    private List<Election> elections;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<Election> getElections() {
        return elections;
    }

    public void setElections(List<Election> elections) {
        this.elections = elections;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", parent=" + parent +
                '}';
    }
}