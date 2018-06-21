package com.rqbank.eelection.domain;

import javax.persistence.*;
import java.util.Date;


/* this class has been generated automatically by HibernateGenerator */

@Entity
@Table(name = "TBL_EVOTE_VOTES")
public class Vote {
    private int id;
    private User user;
    private Candidate candidate;
    private Date createdDate;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                '}';
    }
}