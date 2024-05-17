package com.skillstorm.taxservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxservice.models.CapitalGainsTax;

@Repository
public interface CapitalGainsTaxRepository extends JpaRepository<CapitalGainsTax, Integer> {
  
  public List<CapitalGainsTax> findByFilingStatus_Id(int id);

}
