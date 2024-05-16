package com.skillstorm.taxservice.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education_tax_credit_llc")
public class EducationTaxCreditLlc {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // Income threshold for joint filers below which full credit amount is applied
  @Column(columnDefinition = "INT DEFAULT 160000")
  private int fullCreditIncomeThresholdJointFiler;

  // Range of income for joint filers past the full credit threshold within which partial credit amount is applied
  @Column(columnDefinition = "INT DEFAULT 20000")
  private int partialCreditIncomeThresholdJointFiler;

  // Income threshold for other filers below which full credit amount is applied
  @Column(columnDefinition = "INT DEFAULT 80000")
  private int fullCreditIncomeThresholdOtherFiler;

  // Range of income for other filers past the full credit threshold within which partial credit amount is applied
  @Column(columnDefinition = "INT DEFAULT 10000")
  private int partialCreditIncomeThresholdOtherFiler;

  // Rate of full credit amount which partial credit amount is reduced to
  @Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 0.75")
  private BigDecimal incomePartialCreditRate;


  // Maximum allowed credit amount to be applied (only one per tax return)
  @Column(columnDefinition = "INT DEFAULT 2000")
  private int maxCreditAmountPerStudent;


  // Expenses threshold that is considered to be applied as credit
  @Column(columnDefinition = "INT DEFAULT 10000")
  private int expensesThreshold;

  // Rate at which expenses are applied as credit
  @Column(columnDefinition = "DECIMA(5, 2) DEFAULT 0.2")
  private BigDecimal creditRate;

  
}
