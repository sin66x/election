package com.rqbank.eelection.service;

import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.model.CandidateDTO;

import java.util.List;

public interface CandidateService {
    int save(CandidateDTO candidateDTO);

    List<Candidate> findCandidateByElection(String electionId);

    void remove(String id);

    Candidate findById(String id);

    void saveCandidateForElection(List<Candidate> candidates, String electionId);
}
