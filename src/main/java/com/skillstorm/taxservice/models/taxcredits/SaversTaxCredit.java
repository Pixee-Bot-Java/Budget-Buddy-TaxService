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
@Table(name = "savers_tax_credit")
public class SaversTaxCredit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // Income threshold below which the highest contribution rate is applied as credit
  @Column(name = "agi_threshold_first_contribution_limit")
  private int agiThresholdFirstContributionLimit;

  // Income range past the first threshold within which the second highest contribution rate is applied as credit
  @Column(name = "agi_threshold_second_contribution_limit")
  private int agiThresholdSecondContributionLimit;

  // Income range past the second threshold within which the third highest contribution rate is applied as credit
  @Column(name = "agi_threshold_third_contribution_limit")
  private int agiThresholdThirdContributionLimit;


  // Rate at which contribution is applied as credit given agi below first threshold
  @Column(name = "first_contribution_rate")
  private BigDecimal firstContributionRate;

  // Rate at which contribution is applied as credit given agi within second threshold
  @Column(name = "second_contribution_rate")
  private BigDecimal secondContributionRate;

  // Rate at which contribution is applied as credit given agi within third threshold
  @Column(name = "third_contribution_rate")
  private BigDecimal thirdContributionRate;


  // Max contribution amount that qualifies for credit and is considered in calculating credit amount
  @Column(name = "max_contribution_amount")
  private int maxContributionAmount;

  // Indicates whether credit is refundable
  @Column
  private boolean refundable;
  
}
