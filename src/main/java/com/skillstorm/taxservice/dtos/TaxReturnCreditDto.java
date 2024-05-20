package com.skillstorm.taxservice.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxReturnCreditDto {

  private int taxReturnId;
  private int numDependentsAotc = 0;
  private int numChildren = 0;
  private BigDecimal childCareExpenses = BigDecimal.valueOf(0);
  private BigDecimal educationExpenses = BigDecimal.valueOf(0);
  private BigDecimal iraContributions = BigDecimal.valueOf(0);
  private boolean claimedAsDependent = false;
}
