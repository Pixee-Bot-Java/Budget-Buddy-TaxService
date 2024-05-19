package com.skillstorm.taxservice.utilities.mappers;

import org.springframework.stereotype.Component;

import com.skillstorm.taxservice.dtos.TaxReturnCreditDto;
import com.skillstorm.taxservice.models.TaxReturnCredit;

@Component
public class TaxReturnCreditMapper {

  private TaxReturnCreditMapper() { }
  
  public static TaxReturnCreditDto toDto(TaxReturnCredit taxReturnCredit) {
    TaxReturnCreditDto dto = new TaxReturnCreditDto();
    dto.setTaxReturnId(taxReturnCredit.getTaxReturn().getId());
    dto.setNumDependentsAotc(taxReturnCredit.getNumDependentsAotc());
    dto.setNumChildren(taxReturnCredit.getNumChildren());
    dto.setChildCareExpenses(taxReturnCredit.getChildCareExpenses());
    dto.setEducationExpenses(taxReturnCredit.getEducationExpenses());
    dto.setIraContributions(taxReturnCredit.getIraContributions());
    dto.setClaimedAsDependent(taxReturnCredit.isClaimedAsDependent());
    return dto;
  }

  public static TaxReturnCredit toEntity(TaxReturnCreditDto dto) {
    TaxReturnCredit entity = new TaxReturnCredit();
    entity.setNumDependentsAotc(dto.getNumDependentsAotc());
    entity.setNumChildren(dto.getNumChildren());
    entity.setChildCareExpenses(dto.getChildCareExpenses());
    entity.setEducationExpenses(dto.getEducationExpenses());
    entity.setIraContributions(dto.getIraContributions());
    entity.setClaimedAsDependent(dto.isClaimedAsDependent());
    return entity;
  }

  public static TaxReturnCredit updateEntity(TaxReturnCredit entity, TaxReturnCreditDto dto) {
    // Update the fields of the existing Address entity
    entity.setNumDependentsAotc(dto.getNumDependentsAotc());
    entity.setNumChildren(dto.getNumChildren());
    entity.setChildCareExpenses(dto.getChildCareExpenses());
    entity.setEducationExpenses(dto.getEducationExpenses());
    entity.setIraContributions(dto.getIraContributions());
    entity.setClaimedAsDependent(dto.isClaimedAsDependent());
    return entity;
  }
}
