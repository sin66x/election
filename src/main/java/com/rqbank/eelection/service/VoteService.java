package com.rqbank.eelection.service;

public interface VoteService {
    boolean hadVoted(String username, String electionId);

    boolean hadRight(String username, String electionId);

    void vote(String username, String electionId, String[] candidateIds);
}
