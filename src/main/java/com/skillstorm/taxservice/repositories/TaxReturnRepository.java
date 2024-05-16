package com.skillstorm.taxservice.repositories;

import com.skillstorm.taxservice.models.TaxReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxReturnRepository extends JpaRepository<TaxReturn, Integer> {
}
