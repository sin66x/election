package com.rqbank.eelection.service;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.domain.User;

import java.util.List;

public interface UserService {

    List<Election> getActiveElections(String username);

    User findByUsername(String username);

    void createDefaultAdmin();

    List<User> findAll();

    int save(User user);

    void remove(String id);

    void enableUser(User admin);

    User findById(String id);

    void createUsersForElection(List<User> users, String electionId);

    List<User> findUsersForElection(String electionId);
}
