package com.skillstorm.taxservice.repositories.taxcredits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCreditLimit;

@Repository
public interface DependentCareTaxCreditLimitRepository extends JpaRepository<DependentCareTaxCreditLimit, Integer> {
  
  public DependentCareTaxCreditLimit findByNumDependents(int numDependents);

}
