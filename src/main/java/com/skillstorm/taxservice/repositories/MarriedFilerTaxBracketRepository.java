package com.skillstorm.taxdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxdemo.models.MarriedFilerTaxBracket;

@Repository
public interface MarriedFilerTaxBracketRepository extends JpaRepository<MarriedFilerTaxBracket, Long> {
    // You can define custom query methods here if needed
}

