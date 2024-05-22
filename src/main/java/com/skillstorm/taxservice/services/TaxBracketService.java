package com.skillstorm.taxservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.repositories.TaxBracketRepository;

@Service
public class TaxBracketService {
  
  private final TaxBracketRepository taxBracketRepository;

  public TaxBracketService(TaxBracketRepository taxBracketRepository) {
    this.taxBracketRepository = taxBracketRepository;
  }

  // Get the tax bracket by filing status id
  public List<TaxBracket> findByFilingStatusID(int id) {
    List<TaxBracket> taxBrackets = taxBracketRepository.findByFilingStatus_Id(id);
    return taxBrackets;
  }
}
