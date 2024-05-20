package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.UserCreditData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCreditDataRepository extends JpaRepository<UserCreditData, Long> {
    UserCreditData findByUserId(Long userId);
}
