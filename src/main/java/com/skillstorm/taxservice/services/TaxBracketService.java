package com.skillstorm.taxservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.repositories.TaxBracketRepository;

@Service
public class TaxBracketService {
  
  @Autowired
  TaxBracketRepository taxBracketRepository;

  // Get the tax bracket by filing status id
  public List<TaxBracket> findByFilingStatusID(int id) {
    List<TaxBracket> taxBrackets = taxBracketRepository.findByFilingStatus_Id(id);
    return taxBrackets;
  }
}
