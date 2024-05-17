package com.skillstorm.taxservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.CapitalGainsTax;
import com.skillstorm.taxservice.repositories.CapitalGainsTaxRepository;

@Service
public class CapitalGainsTaxService {
  
  @Autowired
  CapitalGainsTaxRepository capitalGainsTaxRepository;

  // Get the tax bracket by filing status id
  public List<CapitalGainsTax> findByFilingStatusID(int id) {
    List<CapitalGainsTax> capitalGainsTax = capitalGainsTaxRepository.findByFilingStatus_Id(id);
    if (capitalGainsTax.isEmpty()) {
      throw new NotFoundException("No capital gains tax info found for filing status id: " + id);
    }
    return capitalGainsTax;
  }
}
