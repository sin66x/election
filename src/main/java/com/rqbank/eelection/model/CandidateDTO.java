package com.rqbank.eelection.model;

import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.util.date.DateConverter;
import com.rqbank.eelection.util.date.mDate;

import java.util.Date;

public class CandidateDTO {
    public CandidateDTO(){}
    public CandidateDTO(Candidate candidate){
        loadFromDomain(candidate);
    }

    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String candidateCode;
    private String birthdate;
    private String createdDate;
    private String personalCode;
    private String birthCity;
    private String employDate;
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
    private int election;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getEmployDate() {
        return employDate;
    }

    public void setEmployDate(String employDate) {
        this.employDate = employDate;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getOfficialPosition() {
        return officialPosition;
    }

    public void setOfficialPosition(String officialPosition) {
        this.officialPosition = officialPosition;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getEducationField() {
        return educationField;
    }

    public void setEducationField(String educationField) {
        this.educationField = educationField;
    }

    public String getEducationUni() {
        return educationUni;
    }

    public void setEducationUni(String educationUni) {
        this.educationUni = educationUni;
    }

    public String getInternalHistory() {
        return internalHistory;
    }

    public void setInternalHistory(String internalHistory) {
        this.internalHistory = internalHistory;
    }

    public String getExternalHistory() {
        return externalHistory;
    }

    public void setExternalHistory(String externalHistory) {
        this.externalHistory = externalHistory;
    }

    public int getElection() {
        return election;
    }

    public void setElection(int election) {
        this.election = election;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", candidateCode='" + candidateCode + '\'' +
                ", birthdate=" + birthdate +
                ", createdDate=" + createdDate +
                ", personalCode='" + personalCode + '\'' +
                ", birthCity='" + birthCity + '\'' +
                ", employDate=" + employDate +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", officialPosition='" + officialPosition + '\'' +
                ", educationDegree='" + educationDegree + '\'' +
                ", educationField='" + educationField + '\'' +
                ", educationUni='" + educationUni + '\'' +
                ", internalHistory='" + internalHistory + '\'' +
                ", externalHistory='" + externalHistory + '\'' +
                ", election=" + election +
                '}';
    }


    public Date getMiladiEmployDate() {
        mDate mdate = null;
        try {
            mdate = mDate.parse("yyyy/mm/dd",employDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DateConverter.shamsiToMiladi(mdate);
    }


    public Date getMiladiBirthdate() {
        mDate mdate = null;
        try {
            mdate = mDate.parse("yyyy/mm/dd",birthdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DateConverter.shamsiToMiladi(mdate);
    }

    private void loadFromDomain(Candidate candidate) {
        id = candidate.getId();
        firstName = candidate.getFirstName();
        lastName = candidate.getLastName();
        fatherName = candidate.getFatherName();
        candidateCode = candidate.getCandidateCode();
        birthdate = DateConverter.miladiToShamsi(candidate.getBirthdate()).toString("yyyy/mm/dd");
        createdDate  = DateConverter.miladiToShamsi(candidate.getCreatedDate()).toString("yyyy/mm/dd");
        personalCode = candidate.getPersonalCode();
        birthCity = candidate.getBirthCity();
        employDate = DateConverter.miladiToShamsi(candidate.getEmployDate()).toString("yyyy/mm/dd");
        provinceName = candidate.getProvinceName();
        provinceCode = candidate.getProvinceCode();
        branchName = candidate.getBranchName();
        branchCode = candidate.getBranchCode();
        officialPosition = candidate.getOfficialPosition();
        educationDegree = candidate.getEducationDegree();
        educationField = candidate.getEducationField();
        educationUni = candidate.getEducationUni();
        internalHistory = candidate.getInternalHistory();
        externalHistory = candidate.getExternalHistory();
        isActive = candidate.getIsActive();
        if (candidate.getElection()!=null)
            election = candidate.getElection().getId();
    }
}
