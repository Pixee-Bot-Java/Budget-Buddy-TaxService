package com.skillstorm.taxservice.repositories.taxcredits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;

@Repository
public interface EducationTaxCreditLlcRepository extends JpaRepository<EducationTaxCreditLlc, Integer> {
}
