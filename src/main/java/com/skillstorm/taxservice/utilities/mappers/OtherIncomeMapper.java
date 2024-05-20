package com.skillstorm.taxservice.utilities.mappers;

import org.springframework.stereotype.Component;

import com.skillstorm.taxservice.dtos.OtherIncomeDto;
import com.skillstorm.taxservice.models.OtherIncome;

@Component
public class OtherIncomeMapper {
  public static OtherIncomeDto toDto(OtherIncome otherIncome) {
    OtherIncomeDto dto = new OtherIncomeDto();

    dto.setTaxReturnId(otherIncome.getTaxReturn().getId());
    dto.setLongTermCapitalGains(otherIncome.getLongTermCapitalGains());
    dto.setShortTermCapitalGains(otherIncome.getShortTermCapitalGains());
    dto.setOtherInvestmentIncome(otherIncome.getOtherInvestmentIncome());
    dto.setNetBusinessIncome(otherIncome.getNetBusinessIncome());
    dto.setAdditionalIncome(otherIncome.getAdditionalIncome());

    return dto;
  }

  public static OtherIncome toEntity(OtherIncomeDto dto) {
    OtherIncome entity = new OtherIncome();

    entity.setLongTermCapitalGains(dto.getLongTermCapitalGains());
    entity.setShortTermCapitalGains(dto.getShortTermCapitalGains());
    entity.setOtherInvestmentIncome(dto.getOtherInvestmentIncome());
    entity.setNetBusinessIncome(dto.getNetBusinessIncome());
    entity.setAdditionalIncome(dto.getAdditionalIncome());

    return entity;
  }

  public static OtherIncome updateEntity(OtherIncome entity, OtherIncomeDto dto) {
    entity.setLongTermCapitalGains(dto.getLongTermCapitalGains());
    entity.setShortTermCapitalGains(dto.getShortTermCapitalGains());
    entity.setOtherInvestmentIncome(dto.getOtherInvestmentIncome());
    entity.setNetBusinessIncome(dto.getNetBusinessIncome());
    entity.setAdditionalIncome(dto.getAdditionalIncome());

    return entity;
  }
}
