package com.skillstorm.taxservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.StandardDeduction;

@Repository
public interface StandardDeductionRepository extends JpaRepository<StandardDeduction, Integer> {
  
  public Optional<StandardDeduction> findByFilingStatus_Id(int filingStatusId);
}
