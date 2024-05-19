package com.skillstorm.taxservice.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxReturnCreditDto {

  private int taxReturnId;
  private int numDependentsAotc;
  private int numChildren;
  private BigDecimal childCareExpenses;
  private BigDecimal educationExpenses;
  private BigDecimal iraContributions;
  private boolean claimedAsDependent;
}
