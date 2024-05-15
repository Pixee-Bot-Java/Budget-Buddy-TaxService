package com.skillstorm.taxservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.TaxBracket;

@Repository
public interface TaxBracketRepository extends JpaRepository<TaxBracket, Integer> {
  
  public List<TaxBracket> findByFilingStatus_Id(int id);
}
