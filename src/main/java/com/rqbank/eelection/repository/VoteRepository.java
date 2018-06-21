package com.rqbank.eelection.repository;

import com.rqbank.eelection.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther Behnam Safari
 * date 6/13/18.
 * description
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {
    @Query(value = "SELECT v FROM Vote v where v.user.username=?1 and v.candidate.election.id=?2")
    List<Vote> findVotes(String username, int electionId);
}
