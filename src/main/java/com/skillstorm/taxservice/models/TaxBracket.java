package com.skillstorm.taxservice.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_brackets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxBracket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "filing_status_id")
  private FilingStatus filingStatus;

  @Column
  private BigDecimal rate;

  @Column(name = "min_income")
  private int minIncome;

  @Column(name = "max_income")
  private int maxIncome;
}
