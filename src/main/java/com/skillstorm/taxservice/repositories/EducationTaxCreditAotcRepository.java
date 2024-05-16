package com.skillstorm.taxservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;

@Repository
public interface EducationTaxCreditAotcRepository extends JpaRepository<EducationTaxCreditAotc, Integer> {
}
