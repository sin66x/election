package com.rqbank.eelection.repository;

import com.rqbank.eelection.domain.Election;
import com.rqbank.eelection.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.username=?2, u.isActive = ?3, u.role=?4 where u.id=?1")
    void updateWithoutPassword(int id, String username, String isActive, String role);
}
