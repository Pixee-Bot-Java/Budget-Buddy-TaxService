package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildTaxCreditRepository extends JpaRepository<ChildTaxCredit, Integer> {
}

