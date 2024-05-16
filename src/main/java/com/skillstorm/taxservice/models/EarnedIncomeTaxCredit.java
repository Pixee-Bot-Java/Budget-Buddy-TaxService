package com.skillstorm.taxservice.models;

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

  @Column(columnDefinition = "INT DEFAULT 63398")
  private int agiThresholdJointFiling3Children;

  @Column(columnDefinition = "INT DEFAULT 59478")
  private int agiThresholdJointFiling2Children;

  @Column(columnDefinition = "INT DEFAULT 53120")
  private int agiThresholdJointFiling1Children;

  @Column(columnDefinition = "INT DEFAULT 24210")
  private int agiThresholdJointFiling0Children;


  @Column(columnDefinition = "INT DEFAULT 56838")
  private int agiThresholdOtherFiling3Children;

  @Column(columnDefinition = "INT DEFAULT 52918")
  private int agiThresholdOtherFiling2Children;

  @Column(columnDefinition = "INT DEFAULT 46560")
  private int agiThresholdOtherFiling1Children;

  @Column(columnDefinition = "INT DEFAULT 17640")
  private int agiThresholdOtherFiling0Children;


  @Column(columnDefinition = "INT DEFAULT 7430")
  private int amount3Children;

  @Column(columnDefinition = "INT DEFAULT 6604")
  private int amount2Children;

  @Column(columnDefinition = "INT DEFAULT 3995")
  private int amount1Children;

  @Column(columnDefinition = "INT DEFAULT 600")
  private int amount0Children;


  @Column(columnDefinition = "INT DEFAULT 11000")
  private int investmentIncomeLimit;
  
}
