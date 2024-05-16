package com.skillstorm.taxservice.models.taxcredits;

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
  @Column(name = "full_credit_income_threshold")
  private int fullCreditIncomeThreshold;

  // Range of income for joint filers past the full credit threshold within which partial credit amount is applied
  @Column(name = "partial_credit_income_threshold")
  private int partialCreditIncomeThreshold;

  // Rate of full credit amount which partial credit amount is reduced to
  @Column(name = "income_partial_credit_rate")
  private BigDecimal incomePartialCreditRate;


  // Maximum allowed credit amount to be applied (only one per tax return)
  @Column(name = "max_credit_amount")
  private int maxCreditAmount;


  // Expenses threshold that is considered to be applied as credit
  @Column(name = "expenses_threshold")
  private int expensesThreshold;

  // Rate at which expenses are applied as credit
  @Column(name = "credit_rate")
  private BigDecimal creditRate;

  
}
