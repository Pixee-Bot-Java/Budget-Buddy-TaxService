package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Integer> {
}
