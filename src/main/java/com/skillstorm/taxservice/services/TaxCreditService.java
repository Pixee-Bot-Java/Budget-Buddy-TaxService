package com.skillstorm.taxservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCreditLimit;
import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;
import com.skillstorm.taxservice.models.taxcredits.SaversTaxCredit;
import com.skillstorm.taxservice.repositories.taxcredits.ChildTaxCreditRepository;
import com.skillstorm.taxservice.repositories.taxcredits.DependentCareTaxCreditLimitRepository;
import com.skillstorm.taxservice.repositories.taxcredits.DependentCareTaxCreditRepository;
import com.skillstorm.taxservice.repositories.taxcredits.EarnedIncomeTaxCreditRepository;
import com.skillstorm.taxservice.repositories.taxcredits.EducationTaxCreditAotcRepository;
import com.skillstorm.taxservice.repositories.taxcredits.EducationTaxCreditLlcRepository;
import com.skillstorm.taxservice.repositories.taxcredits.SaversTaxCreditRepository;

@Service
public class TaxCreditService {

  // Inject tax credit repositories
  private final ChildTaxCreditRepository childTaxCreditRepository;
  private final EarnedIncomeTaxCreditRepository earnedIncomeTaxCreditRepository;
  private final EducationTaxCreditAotcRepository educationTaxCreditAotcRepository;
  private final EducationTaxCreditLlcRepository educationTaxCreditLlcRepository;
  private final SaversTaxCreditRepository saversTaxCreditRepository;
  private final DependentCareTaxCreditRepository dependentCareTaxCreditRepository;
  private final DependentCareTaxCreditLimitRepository dependentCareTaxCreditLimitRepository;

  public TaxCreditService(ChildTaxCreditRepository childTaxCreditRepository,
                            EarnedIncomeTaxCreditRepository earnedIncomeTaxCreditRepository,
                            EducationTaxCreditAotcRepository educationTaxCreditAotcRepository,
                            EducationTaxCreditLlcRepository educationTaxCreditLlcRepository,
                            SaversTaxCreditRepository saversTaxCreditRepository,
                            DependentCareTaxCreditRepository dependentCareTaxCreditRepository,
                            DependentCareTaxCreditLimitRepository dependentCareTaxCreditLimitRepository) {
    this.childTaxCreditRepository = childTaxCreditRepository;
    this.earnedIncomeTaxCreditRepository = earnedIncomeTaxCreditRepository;
    this.educationTaxCreditAotcRepository = educationTaxCreditAotcRepository;
    this.educationTaxCreditLlcRepository = educationTaxCreditLlcRepository;
    this.saversTaxCreditRepository = saversTaxCreditRepository;
    this.dependentCareTaxCreditRepository = dependentCareTaxCreditRepository;
    this.dependentCareTaxCreditLimitRepository = dependentCareTaxCreditLimitRepository;
  }

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

  public List<DependentCareTaxCredit> getDependentCareTaxCreditBrackets() {
    return dependentCareTaxCreditRepository.findAll();
  }

  public DependentCareTaxCreditLimit getDependentCareTaxCreditLimitByNumDependents(int numDependents) {
    if (numDependents == 1) {
      return dependentCareTaxCreditLimitRepository.findByNumDependents(1);
    } else if (numDependents >= 2) {
      return dependentCareTaxCreditLimitRepository.findByNumDependents(2);
    }
    throw new IllegalArgumentException("number of dependents: " + numDependents + " not a valid number");
  }
  
}
