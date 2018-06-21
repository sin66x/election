package com.rqbank.eelection.domain;

import javax.persistence.*;
import java.util.Date;


/* this class has been generated automatically by HibernateGenerator */

@Entity
@Table(name = "TBL_EVOTE_CANDIDATES")
public class Candidate {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String candidateCode;
    private Date birthdate;
    private Date createdDate;
    private String personalCode;
    private String birthCity;
    private Date employDate;
    private String provinceName;
    private String provinceCode;
    private String branchName;
    private String branchCode;
    private String officialPosition;
    private String educationDegree;
    private String educationField;
    private String educationUni;
    private String internalHistory;
    private String externalHistory;
    private String isActive;
    private Election election;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "FATHER_NAME")
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "CANDIDATE_CODE")
    public String getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }

    @Column(name = "BIRTHDATE")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "PERSONAL_CODE")
    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    @Column(name = "BIRTH_CITY")
    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    @Column(name = "EMPLOY_DATE")
    public Date getEmployDate() {
        return employDate;
    }

    public void setEmployDate(Date employDate) {
        this.employDate = employDate;
    }

    @Column(name = "PROVINCE_NAME")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "PROVINCE_CODE")
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Column(name = "BRANCH_NAME")
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Column(name = "BRANCH_CODE")
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Column(name = "OFFICIAL_POSITION")
    public String getOfficialPosition() {
        return officialPosition;
    }

    public void setOfficialPosition(String officialPosition) {
        this.officialPosition = officialPosition;
    }

    @Column(name = "EDUCATION_DEGREE")
    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    @Column(name = "EDUCATION_FIELD")
    public String getEducationField() {
        return educationField;
    }

    public void setEducationField(String educationField) {
        this.educationField = educationField;
    }

    @Column(name = "EDUCATION_UNI")
    public String getEducationUni() {
        return educationUni;
    }

    public void setEducationUni(String educationUni) {
        this.educationUni = educationUni;
    }

    @Column(name = "INTERNAL_HISTORY")
    public String getInternalHistory() {
        return internalHistory;
    }

    public void setInternalHistory(String internalHistory) {
        this.internalHistory = internalHistory;
    }

    @Column(name = "EXTERNAL_HISTORY")
    public String getExternalHistory() {
        return externalHistory;
    }

    public void setExternalHistory(String externalHistory) {
        this.externalHistory = externalHistory;
    }

    @ManyToOne
    @JoinColumn(name = "ELECTION_ID")
    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
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
        return "Candidate{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}