package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.W2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface W2Repository extends JpaRepository<W2, Integer> {

    // Find all W2s by UserId:
    List<W2> findAllByUserId(int userId);

    // Find all W2s by UserId and Year:
    List<W2> findAllByUserIdAndYear(int userId, int year);

    // Delete all W2s by TaxReturnId:
    void deleteAllByTaxReturnId(int taxReturnId);

    // Find all W2s by TaxReturnId:
    List<W2> findAllByTaxReturnId(int taxReturnId);
}
