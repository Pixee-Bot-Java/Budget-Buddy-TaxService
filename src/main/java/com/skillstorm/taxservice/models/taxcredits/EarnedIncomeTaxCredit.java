package com.skillstorm.taxservice.models.taxcredits;

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
@Table(name = "earned_income_tax_credit")
public class EarnedIncomeTaxCredit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "agi_threshold_3children")
  private int agiThreshold3Children;

  @Column(name = "agi_threshold_2children")
  private int agiThreshold2Children;

  @Column(name = "agi_threshold_1children")
  private int agiThreshold1Children;

  @Column(name = "agi_threshold_0children")
  private int agiThreshold0Children;


  @Column(name = "amount_3children")
  private int amount3Children;

  @Column(name = "amount_2children")
  private int amount2Children;

  @Column(name = "amount_1children")
  private int amount1Children;

  @Column(name = "amount_0children")
  private int amount0Children;


  @Column(name = "investment_income_limit")
  private int investmentIncomeLimit;
  
}
