package com.rqbank.eelection.domain;

import com.rqbank.eelection.util.date.DateConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/* this class has been generated automatically by HibernateGenerator */

@Entity
@Table(name = "TBL_EVOTE_ELECTIONS")
public class Election {
    private int id;
    private String name;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private int maxSelection;
    private Category category;
    private String isActive;
    private List<Candidate> candidates;
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

    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "MAX_SELECTION")
    public int getMaxSelection() {
        return maxSelection;
    }

    public void setMaxSelection(int maxSelection) {
        this.maxSelection = maxSelection;
    }

    @Column(name = "IS_ACTIVE")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", maxSelection=" + maxSelection +
                '}';
    }

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Transient
    public String getJalaliStartDate(){
        return DateConverter.miladiToShamsi(startDate).toString("yyyy/mm/dd");
    }

    @Transient
    public String getJalaliEndDate(){

        return DateConverter.miladiToShamsi(endDate).toString("yyyy/mm/dd");
    }

}