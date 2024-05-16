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
@Table(name = "education_tax_credit_aotc")
public class EducationTaxCreditAotc {

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


  // Maximum allowed credit amount to be applied for each student (each tax credit)
  @Column(columnDefinition = "INT DEFAULT 2500")
  private int maxCreditAmountPerStudent;


  // Expenses threshold under which 100% of the expenses are applied as credit
  @Column(columnDefinition = "INT DEFAULT 2000")
  private int expensesThresholdFullCredit;

  // Expenses Range past the full credit expenses threshold within which partial expenses are applied as credit
  @Column(columnDefinition = "INT DEFAULT 2000")
  private int expensesThresholdPartialCredit;

  // Expenses are reduced to this rate before being applied as partial credit amount
  @Column(columnDefinition = "DECIMA(5, 2) DEFAULT 0.04")
  private BigDecimal expensesPartialCreditRate;

  
}
