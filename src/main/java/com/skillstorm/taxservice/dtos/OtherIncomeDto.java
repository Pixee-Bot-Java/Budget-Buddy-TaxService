package com.skillstorm.taxservice.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OtherIncomeDto {
  
  private int taxReturnId;
  private BigDecimal longTermCapitalGains = BigDecimal.valueOf(0);
  private BigDecimal shortTermCapitalGains = BigDecimal.valueOf(0);
  private BigDecimal otherInvestmentIncome = BigDecimal.valueOf(0);
  private BigDecimal netBusinessIncome = BigDecimal.valueOf(0);
  private BigDecimal additionalIncome = BigDecimal.valueOf(0);
}
