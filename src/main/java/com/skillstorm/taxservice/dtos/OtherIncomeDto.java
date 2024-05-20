package com.skillstorm.taxservice.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OtherIncomeDto {
  
  private int taxReturnId;
  private BigDecimal longTermCapitalGains;
  private BigDecimal shortTermCapitalGains;
  private BigDecimal otherInvestmentIncome;
  private BigDecimal netBusinessIncome;
  private BigDecimal additionalIncome;
}
