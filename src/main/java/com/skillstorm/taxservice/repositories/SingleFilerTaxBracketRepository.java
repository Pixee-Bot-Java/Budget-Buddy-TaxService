package com.skillstorm.taxservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.SingleFilerTaxBracket;

@Repository
public interface SingleFilerTaxBracketRepository extends JpaRepository<SingleFilerTaxBracket, Long> {
    // You can define custom query methods here if needed
}

