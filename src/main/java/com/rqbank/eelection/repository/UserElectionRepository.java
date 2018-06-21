package com.rqbank.eelection.repository;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.domain.User;
import com.rqbank.eelection.domain.UserElection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther Behnam Safari
 * date 6/13/18.
 * description
 */

@Repository
public interface UserElectionRepository extends JpaRepository<UserElection,Integer>{
    @Query(value = "SELECT ue FROM UserElection ue where ue.user.username=?1 and ue.election.id=?2 " +
            " and ue.election.startDate<=CURRENT_DATE and ue.election.endDate>=CURRENT_DATE")
    UserElection hadRight(String username, int id);

    @Transactional
    void deleteByElection(Election election);

    List<UserElection> findByElection(Election election);
}
