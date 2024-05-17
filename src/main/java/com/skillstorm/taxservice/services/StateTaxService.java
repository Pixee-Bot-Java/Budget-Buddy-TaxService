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

  public List<StateTax> getTaxBracketsByStateName(String stateName) {
    List<StateTax> taxBrackets = stateTaxRepository.findByStateName(stateName);
    if (taxBrackets.isEmpty()) {
        throw new NotFoundException("State tax info not found for state name: " + stateName);
    }
    return taxBrackets;
  }

  public List<StateTax> getTaxBracketsByStateCode(String stateCode) {
    List<StateTax> taxBrackets = stateTaxRepository.findByStateCode(stateCode);
    if (taxBrackets.isEmpty()) {
        throw new NotFoundException("State tax info not found for state code: " + stateCode);
    }
    return taxBrackets;
  }

}
