package com.skillstorm.taxservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RefundDto {

    private BigDecimal federalRefund;
    private BigDecimal stateRefund;


}
