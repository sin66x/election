package com.rqbank.eelection.domain;

import javax.persistence.*;
import java.util.Date;


/* this class has been generated automatically by HibernateGenerator */

@Entity
@Table(name = "TBL_EVOTE_USERS")
public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String isActive;
    private Date createdDate;

    public User(){}

    public User(String username, String password,String role, String isActive) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        createdDate = new Date();

    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "IS_ACTIVE")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}