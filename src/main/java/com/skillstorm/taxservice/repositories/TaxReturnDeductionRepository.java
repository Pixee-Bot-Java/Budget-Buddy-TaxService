package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.TaxReturnDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxReturnDeductionRepository extends JpaRepository<TaxReturnDeduction, Integer> {
}
