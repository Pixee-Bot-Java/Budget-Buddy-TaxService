package com.skillstorm.taxservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.OtherIncome;

@Repository
public interface OtherIncomeRepository extends JpaRepository<OtherIncome, Integer> {
  
  public Optional<OtherIncome> findByTaxReturnId(int id);

}
