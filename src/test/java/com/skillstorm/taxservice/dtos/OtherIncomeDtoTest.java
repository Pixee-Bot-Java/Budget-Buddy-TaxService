package com.skillstorm.taxservice.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OtherIncomeDtoTest {

    @Test
    void testNoArgsConstructor() {
        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        assertThat(otherIncomeDto).isNotNull();
        assertThat(otherIncomeDto.getTaxReturnId()).isEqualTo(0); // Assuming default value is 0
        assertThat(otherIncomeDto.getLongTermCapitalGains()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getShortTermCapitalGains()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getOtherInvestmentIncome()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getNetBusinessIncome()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getAdditionalIncome()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void testNoArgsConstructorAndGettersAndSetters() {
        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        assertThat(otherIncomeDto).isNotNull();
        
        // Test default values
        assertThat(otherIncomeDto.getTaxReturnId()).isEqualTo(0);
        assertThat(otherIncomeDto.getLongTermCapitalGains()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getShortTermCapitalGains()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getOtherInvestmentIncome()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getNetBusinessIncome()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(otherIncomeDto.getAdditionalIncome()).isEqualByComparingTo(BigDecimal.ZERO);
        
        // Test setters
        otherIncomeDto.setTaxReturnId(1);
        assertThat(otherIncomeDto.getTaxReturnId()).isEqualTo(1);

        BigDecimal longTermCapitalGains = BigDecimal.valueOf(1000);
        otherIncomeDto.setLongTermCapitalGains(longTermCapitalGains);
        assertThat(otherIncomeDto.getLongTermCapitalGains()).isEqualByComparingTo(longTermCapitalGains);

        BigDecimal shortTermCapitalGains = BigDecimal.valueOf(2000);
        otherIncomeDto.setShortTermCapitalGains(shortTermCapitalGains);
        assertThat(otherIncomeDto.getShortTermCapitalGains()).isEqualByComparingTo(shortTermCapitalGains);

        BigDecimal otherInvestmentIncome = BigDecimal.valueOf(3000);
        otherIncomeDto.setOtherInvestmentIncome(otherInvestmentIncome);
        assertThat(otherIncomeDto.getOtherInvestmentIncome()).isEqualByComparingTo(otherInvestmentIncome);

        BigDecimal netBusinessIncome = BigDecimal.valueOf(4000);
        otherIncomeDto.setNetBusinessIncome(netBusinessIncome);
        assertThat(otherIncomeDto.getNetBusinessIncome()).isEqualByComparingTo(netBusinessIncome);

        BigDecimal additionalIncome = BigDecimal.valueOf(5000);
        otherIncomeDto.setAdditionalIncome(additionalIncome);
        assertThat(otherIncomeDto.getAdditionalIncome()).isEqualByComparingTo(additionalIncome);
    }
}