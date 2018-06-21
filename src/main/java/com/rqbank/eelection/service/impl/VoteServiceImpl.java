package com.rqbank.eelection.service.impl;

import com.rqbank.eelection.domain.Candidate;
import com.rqbank.eelection.domain.UserElection;
import com.rqbank.eelection.domain.Vote;
import com.rqbank.eelection.repository.UserElectionRepository;
import com.rqbank.eelection.repository.VoteRepository;
import com.rqbank.eelection.service.CandidateService;
import com.rqbank.eelection.service.UserService;
import com.rqbank.eelection.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserElectionRepository userElectionRepository;

    @Autowired
    CandidateService candidateService;

    @Autowired
    UserService userService;

    @Override
    public boolean hadVoted(String username, String electionId) {
        List<Vote> votes = voteRepository.findVotes(username,Integer.parseInt(electionId));
        if (votes!=null && votes.size()>0)
            return true;
        return false;
    }

    @Override
    public boolean hadRight(String username, String electionId) {
        UserElection userElection = userElectionRepository.hadRight(username,Integer.parseInt(electionId));
        if (userElection!=null)
            return true;
        else
            return false;
    }

    @Override
    public void vote(String username, String electionId, String[] candidateIds) {

        for (int i=0;i<candidateIds.length;i++) {
            Vote vote = new Vote();
            Candidate candidate = candidateService.findById(candidateIds[i]);
            if (candidate!=null && candidate.getElection().getId()==Integer.parseInt(electionId)) {
                vote.setCandidate(candidate);
                vote.setCreatedDate(new Date());
                vote.setUser(userService.findByUsername(username));
                voteRepository.save(vote);
            }
        }
        voteRepository.flush();
    }
}
