package com.skillstorm.taxservice.dtos;

import com.skillstorm.taxservice.models.TaxReturnDeduction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxReturnDeductionDto {

    private int id;
    private int taxReturn;
    private int deduction;
    private String deductionName;
    private BigDecimal amountSpent;
    private BigDecimal netDeduction;

    public TaxReturnDeductionDto(TaxReturnDeduction taxReturnDeduction) {
        this.id = taxReturnDeduction.getId();
        this.taxReturn = taxReturnDeduction.getTaxReturn().getId();
        this.deduction = taxReturnDeduction.getDeduction().getId();
        this.deductionName = taxReturnDeduction.getDeduction().getName();
        this.amountSpent = taxReturnDeduction.getAmountSpent();
        this.netDeduction = taxReturnDeduction.getNetDeduction();
    }

    public TaxReturnDeduction mapToEntity() {
        TaxReturnDeduction taxReturnDeduction = new TaxReturnDeduction();
        taxReturnDeduction.setId(this.id);
        return taxReturnDeduction;
    }
}
