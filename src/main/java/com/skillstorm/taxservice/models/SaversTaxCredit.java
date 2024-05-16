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
@Table(name = "savers_tax_credit")
public class SaversTaxCredit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // Income threshold below which the highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 43500")
  private int agiThresholdJointFilingFirstContributionLimit;

  // Income range past the first threshold within which the second highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 3999")
  private int agiThresholdJointFilingSecondContributionLimit;

  // Income range past the second threshold within which the third highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 25499")
  private int agiThresholdJointFilingThirdContributionLimit;


  // Income threshold below which the highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 32625")
  private int agiThresholdHeadHouseholdFirstContributionLimit;

  // Income range past the first threshold within which the second highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 2999")
  private int agiThresholdHeadHouseholdSecondContributionLimit;

  // Income range past the second threshold within which the third highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 19124")
  private int agiThresholdHeadHouseholdThirdContributionLimit;


  // Income threshold below which the highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 21750")
  private int agiThresholdOtherFilingFirstContributionLimit;

  // Income range past the first threshold within which the second highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 1999")
  private int agiThresholdOtherFilingSecondContributionLimit;

  // Income range past the second threshold within which the third highest contribution rate is applied as credit
  @Column(columnDefinition = "INT DEFAULT 12749")
  private int agiThresholdOtherFilingThirdContributionLimit;


  // Rate at which contribution is applied as credit given agi below first threshold
  @Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 0.5")
  private BigDecimal firstContributionRate;

  // Rate at which contribution is applied as credit given agi within second threshold
  @Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 0.2")
  private BigDecimal secondContributionRate;

   // Rate at which contribution is applied as credit given agi within third threshold
   @Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 0.1")
   private BigDecimal thirdontributionRate;


   // Max contribution amount that qualifies for credit and is considered in calculating credit amount
   @Column(columnDefinition = "INT DEFAULT 2000")
   private int maxContributionAmount;
  
}
