package com.skillstorm.taxservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillstorm.taxservice.models.Deduction;
import com.skillstorm.taxservice.models.TaxReturn;
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

    public TaxReturnDeductionDto() {
        this.amountSpent = BigDecimal.ZERO.setScale(2);
        this.netDeduction = BigDecimal.ZERO.setScale(2);
    }

    public TaxReturnDeductionDto(TaxReturnDeduction taxReturnDeduction) {
        this.id = taxReturnDeduction.getId();
        this.taxReturn = taxReturnDeduction.getTaxReturn().getId();
        this.deduction = taxReturnDeduction.getDeduction().getId();
        this.deductionName = taxReturnDeduction.getDeduction().getName();
        this.amountSpent = taxReturnDeduction.getAmountSpent();
        this.netDeduction = taxReturnDeduction.getNetDeduction();
    }

    @JsonIgnore
    public TaxReturnDeduction mapToEntity() {
        TaxReturnDeduction taxReturnDeduction = new TaxReturnDeduction();
        taxReturnDeduction.setId(this.id);
        taxReturnDeduction.setTaxReturn(new TaxReturn(this.taxReturn));
        taxReturnDeduction.setDeduction(new Deduction(this.deduction));
        taxReturnDeduction.setAmountSpent(this.amountSpent);
        taxReturnDeduction.setNetDeduction(this.netDeduction);
        return taxReturnDeduction;
    }
}
