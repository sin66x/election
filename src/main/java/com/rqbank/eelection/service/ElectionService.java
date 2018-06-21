package com.rqbank.eelection.service;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.model.ElectionDTO;

import java.util.List;

public interface ElectionService {
    void removeCategory(Election election);

    int save(ElectionDTO electionDTO);

    Election findById(String id);

    void remove(String id);


    List<Election> getAllActives();

    Election getElectionById(int election);

    List<Election> findActiveElections(String username);
}
