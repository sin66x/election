package com.rqbank.eelection.model;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.util.date.DateConverter;
import com.rqbank.eelection.util.date.*;
import java.util.Date;

public class ElectionDTO {
    public ElectionDTO(){}
    public ElectionDTO(Election election){
        loadFromDomain(election);
    }

    private int id;
    private String name;
    private Date createdDate;
    private String startDate;
    private String endDate;
    private int maxSelection;
    private int category;
    private String isActive;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMaxSelection() {
        return maxSelection;
    }

    public void setMaxSelection(int maxSelection) {
        this.maxSelection = maxSelection;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ElectionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", maxSelection=" + maxSelection +
                ", category=" + category +
                ", isActive=" + isActive +
                '}';
    }

    public void loadFromDomain(Election election){
        id= election.getId();
        name = election.getName();
        createdDate = election.getCreatedDate();
        startDate = DateConverter.miladiToShamsi(election.getStartDate()).toString("yyyy/mm/dd");
        endDate = DateConverter.miladiToShamsi(election.getEndDate()).toString("yyyy/mm/dd");
        maxSelection = election.getMaxSelection();
        isActive = election.getIsActive();
        if (election.getCategory()!=null)
            category = election.getCategory().getId();
    }

    public Date getMiladiEndDate()  {
        mDate mdate = null;
        try {
            mdate = mDate.parse("yyyy/mm/dd",endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DateConverter.shamsiToMiladi(mdate);
    }

    public Date getMiladiStartDate() {
        mDate mdate = null;
        try {
            mdate = mDate.parse("yyyy/mm/dd",startDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DateConverter.shamsiToMiladi(mdate);
    }
}
