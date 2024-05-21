package com.skillstorm.taxservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.repositories.StateTaxRepository;

@Service
public class StateTaxService {
  
  @Autowired
  private StateTaxRepository stateTaxRepository;

  // Get state income tax brackets by state name
  public List<StateTax> getTaxBracketsByStateName(String stateName) {
    List<StateTax> taxBrackets = stateTaxRepository.findByStateName(stateName);
    if (taxBrackets.isEmpty()) {
        throw new NotFoundException("State tax info not found for state name: " + stateName);
    }
    return taxBrackets;
  }

  // Get state income tax brackets by 2-letter state code
  public List<StateTax> getTaxBracketsByStateCode(String stateCode) {
    List<StateTax> taxBrackets = stateTaxRepository.findByStateCode(stateCode);
    if (taxBrackets.isEmpty()) {
        throw new NotFoundException("State tax info not found for state code: " + stateCode);
    }
    return taxBrackets;
  }

  // Get state income tax brackets by state id (1-50, alphabetical order)
  public List<StateTax> getTaxBracketsByStateId(int stateId) {
    List<StateTax> taxBrackets = stateTaxRepository.findByStateId(stateId);
    if (taxBrackets.isEmpty()) {
      throw new NotFoundException("State tax info not found for state ID: " + stateId);
    }
    return taxBrackets;
  }

}
