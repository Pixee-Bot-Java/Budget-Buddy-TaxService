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
@Table(name = "child_tax_credit")
public class ChildTaxCredit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "per_qualifying_child")
  private int perQualifyingChild;

  @Column(name = "per_other_child")
  private int perOtherChild;

  @Column(name = "income_threshold")
  private int incomeThreshold;

  @Column(name = "rate_factor")
  private BigDecimal rateFactor;

  @Column
  private boolean refundable;

  @Column(name = "refund_limit")
  private int refundLimit;

  @Column(name = "refund_rate")
  private BigDecimal refundRate;
  
}
