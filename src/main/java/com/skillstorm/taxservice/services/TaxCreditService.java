package com.skillstorm.taxservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;
import com.skillstorm.taxservice.models.taxcredits.SaversTaxCredit;
import com.skillstorm.taxservice.repositories.ChildTaxCreditRepository;
import com.skillstorm.taxservice.repositories.EarnedIncomeTaxCreditRepository;
import com.skillstorm.taxservice.repositories.EducationTaxCreditAotcRepository;
import com.skillstorm.taxservice.repositories.EducationTaxCreditLlcRepository;
import com.skillstorm.taxservice.repositories.SaversTaxCreditRepository;

@Service
public class TaxCreditService {

  @Autowired
  private ChildTaxCreditRepository childTaxCreditRepository;

  @Autowired
  private EarnedIncomeTaxCreditRepository earnedIncomeTaxCreditRepository;

  @Autowired
  private EducationTaxCreditAotcRepository educationTaxCreditAotcRepository;

  @Autowired
  private EducationTaxCreditLlcRepository educationTaxCreditLlcRepository;

  @Autowired
  private SaversTaxCreditRepository saversTaxCreditRepository;


  public ChildTaxCredit getChildTaxCreditById(int id) {
    return childTaxCreditRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("child tax credit with id: " + id + " not found"));
  }

  public EarnedIncomeTaxCredit getEarnedIncomeTaxCreditById(int id) {
    return earnedIncomeTaxCreditRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("earned income tax credit with id: " + id + " not found"));
  }

  public EducationTaxCreditAotc getEducationTaxCreditAotcById(int id) {
    return educationTaxCreditAotcRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("education tax credit aotc with id: " + id + " not found"));
  }

  public EducationTaxCreditLlc getEducationTaxCreditLlcById(int id) {
    return educationTaxCreditLlcRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("education tax credit llc with id: " + id + " not found"));
  }

  public SaversTaxCredit getSaversTaxCreditById(int id) {
    return saversTaxCreditRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("savers tax credit with id: " + id + " not found"));
  }
  
}
