package com.rqbank.eelection.repository;

import com.rqbank.eelection.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther Behnam Safari
 * date 6/13/18.
 * description
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
