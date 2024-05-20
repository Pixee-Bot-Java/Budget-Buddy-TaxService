package com.skillstorm.taxdemo.repositories;

import com.skillstorm.taxdemo.models.UserCreditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCreditDataRepository extends JpaRepository<UserCreditData, Long> {
    UserCreditData findByUserId(Long userId);
}
