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
@Table(name = "child_tax_credit")
public class ChildTaxCredit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(columnDefinition = "INT DEFAULT 2000")
  private int perQualifyingChild;

  @Column(columnDefinition = "INT DEFAULT 500")
  private int perOtherChild;

  @Column(columnDefinition = "INT DEFAULT 400000")
  private int jointThreshold;

  @Column(columnDefinition = "INT DEFAULT 200000")
  private int otherThreshold;

  @Column(columnDefinition = "DECIMAL(5, 2) DEFAULT 0.05")
  private BigDecimal rateFactor;
  
}
