package com.rqbank.eelection.repository;

import com.rqbank.eelection.domain.Election;
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
public interface ElectionRepository extends JpaRepository<Election,Integer> {
    List<Election> findByIsActive(String isActive);
    @Query(value = "SELECT e.* FROM tbl_evote_elections e \n" +
            "\tjoin tbl_evote_users_elections ue \n" +
            "\t\ton ue.election_id=e.id and e.is_active='true'\n" +
            "\tjoin tbl_evote_users u \n" +
            "\t\ton u.id = ue.user_id\n" +
            "\twhere u.username=?1",
            nativeQuery = true)
    List<Election> findActiveElections(String username);
}
