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
@Table(name = "education_tax_credit_aotc")
public class EducationTaxCreditAotc {

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
  //@Column(name = "income_partial_credit_rate")
  private BigDecimal incomePartialCreditRate;


  // Maximum allowed credit amount to be applied for each student (each tax credit)
  @Column(name = "max_credit_amount")
  private int maxCreditAmountPerStudent;


  // Expenses threshold under which 100% of the expenses are applied as credit
  @Column(name = "full_credit_expenses_threshold")
  private int expensesThresholdFullCredit;

  // Expenses Range past the full credit expenses threshold within which partial expenses are applied as credit
  @Column(name = "partial_credit_expenses_threshold")
  private int expensesThresholdPartialCredit;

  // Expenses are reduced to this rate before being applied as partial credit amount
  @Column(name = "partial_credit_expenses_rate")
  private BigDecimal expensesPartialCreditRate;


  @Column(name = "refund_limit")
  private int refundLimit;
}
