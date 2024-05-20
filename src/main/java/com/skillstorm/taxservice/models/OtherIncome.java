package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "w2")
public class OtherIncome {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  @JoinColumn(name = "tax_return_id")
  private TaxReturn taxReturn;

  @Column
  private BigDecimal longTermCapitalGains;

  @Column
  private BigDecimal shortTermCapitalGains;

  @Column
  private BigDecimal otherInvestmentIncome;

  @Column
  private BigDecimal netBusinessIncome;

  @Column
  private BigDecimal additionalIncome;
}
