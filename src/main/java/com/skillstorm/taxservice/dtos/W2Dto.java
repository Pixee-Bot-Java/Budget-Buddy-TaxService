package com.skillstorm.taxservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillstorm.taxservice.constants.State;
import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.models.W2;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class W2Dto {

    private int id;
    private int taxReturnId;
    private int year;
    private int userId;
    private String employer;
    private State state;
    private BigDecimal wages;
    private BigDecimal tips;
    private BigDecimal federalIncomeTaxWithheld;
    private BigDecimal stateIncomeTaxWithheld;
    private BigDecimal socialSecurityTaxWithheld;
    private BigDecimal medicareTaxWithheld;
    private String imageKey;

    public W2Dto() {
        // Default value to avoid null pointers:
        this.state = State.ALABAMA;
        this.tips = BigDecimal.ZERO.setScale(2);
    }

    @JsonCreator
    public W2Dto(@JsonProperty("state") String state) {
        this();
        if(state != null) {
            this.state = State.fromCode(state.trim().toUpperCase());
        }
    }

    public W2Dto(W2 w2) {
        this();
        this.id = w2.getId();
        this.taxReturnId = w2.getTaxReturn().getId();
        this.year = w2.getYear();
        this.userId = w2.getUserId();
        this.employer = w2.getEmployer();
        this.state = State.fromValue(w2.getState());
        this.wages = w2.getWages();
        this.tips = w2.getTips();
        this.federalIncomeTaxWithheld = w2.getFederalIncomeTaxWithheld();
        this.stateIncomeTaxWithheld = w2.getStateIncomeTaxWithheld();
        this.socialSecurityTaxWithheld = w2.getSocialSecurityTaxWithheld();
        this.medicareTaxWithheld = w2.getMedicareTaxWithheld();
        this.imageKey = w2.getImageKey();
    }

    public W2 mapToEntity() {
        W2 w2 = new W2();
        w2.setId(id);
        w2.setTaxReturn(new TaxReturn(taxReturnId));
        w2.setYear(year);
        w2.setUserId(userId);
        w2.setEmployer(employer);
        w2.setState(state.getValue());
        w2.setWages(wages);
        w2.setTips(tips);
        w2.setFederalIncomeTaxWithheld(federalIncomeTaxWithheld);
        w2.setStateIncomeTaxWithheld(stateIncomeTaxWithheld);
        w2.setSocialSecurityTaxWithheld(socialSecurityTaxWithheld);
        w2.setMedicareTaxWithheld(medicareTaxWithheld);
        w2.setImageKey(imageKey);
        return w2;
    }

    @Override
    public String toString() {
        return "W2Dto{" +
                "id=" + id +
                ", taxReturnId=" + taxReturnId +
                ", year=" + year +
                ", userId=" + userId +
                ", employer='" + employer + '\'' +
                ", state=" + state +
                ", wages=" + wages +
                ", tips=" + tips +
                ", federalIncomeTaxWithheld=" + federalIncomeTaxWithheld +
                ", stateIncomeTaxWithheld=" + stateIncomeTaxWithheld +
                ", socialSecurityTaxWithheld=" + socialSecurityTaxWithheld +
                ", medicareTaxWithheld=" + medicareTaxWithheld +
                ", imageKey='" + imageKey + '\'' +
                '}';
    }
}
