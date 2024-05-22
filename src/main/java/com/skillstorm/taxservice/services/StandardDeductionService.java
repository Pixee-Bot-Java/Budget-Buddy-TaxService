package com.skillstorm.taxservice.services;

import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.StandardDeduction;
import com.skillstorm.taxservice.repositories.StandardDeductionRepository;

@Service
public class StandardDeductionService {
  
  private final StandardDeductionRepository standardDeductionRepository;

  public StandardDeductionService(StandardDeductionRepository standardDeductionRepository) {
    this.standardDeductionRepository = standardDeductionRepository;
  }

  // Get the standardized deduction amount by Filing Status id
  public StandardDeduction getByFilingStatusId(int filingStatusId) {
    return standardDeductionRepository.findByFilingStatus_Id(filingStatusId)
      .orElseThrow(() -> new NotFoundException("Standardized deduction not found for filing status id: " + filingStatusId));
  }
}
