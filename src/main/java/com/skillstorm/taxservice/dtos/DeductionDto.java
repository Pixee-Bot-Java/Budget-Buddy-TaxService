package com.skillstorm.taxservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skillstorm.taxservice.models.Deduction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeductionDto {

    private int id;
    private String name;
    private BigDecimal agiLimit;
    private boolean itemized;

    public DeductionDto() {
        super();
        agiLimit = BigDecimal.ONE;
    }

    public DeductionDto(int id) {
        this();
        this.id = id;
    }

    public DeductionDto(Deduction deduction) {
        this();
        this.id = deduction.getId();
        this.name = deduction.getName();
        this.agiLimit = deduction.getAgiLimit();
        this.itemized = deduction.isItemized();
    }

    @JsonIgnore
    public Deduction mapToEntity() {
        Deduction deduction = new Deduction();
        deduction.setId(id);
        deduction.setName(name);
        deduction.setAgiLimit(agiLimit);
        deduction.setItemized(itemized);

        return deduction;
    }


}
