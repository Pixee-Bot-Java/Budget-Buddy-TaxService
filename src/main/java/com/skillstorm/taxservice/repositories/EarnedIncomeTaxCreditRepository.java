package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EarnedIncomeTaxCreditRepository extends JpaRepository<EarnedIncomeTaxCredit, Integer> {
}
