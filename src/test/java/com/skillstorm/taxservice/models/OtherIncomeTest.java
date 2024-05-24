package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OtherIncomeTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        OtherIncome otherIncome = new OtherIncome();

        // Then
        assertThat(otherIncome).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        OtherIncome otherIncome = new OtherIncome();
        int id = 1;
        TaxReturn taxReturn = new TaxReturn();
        BigDecimal longTermCapitalGains = BigDecimal.valueOf(1000);
        BigDecimal shortTermCapitalGains = BigDecimal.valueOf(500);
        BigDecimal otherInvestmentIncome = BigDecimal.valueOf(200);
        BigDecimal netBusinessIncome = BigDecimal.valueOf(300);
        BigDecimal additionalIncome = BigDecimal.valueOf(400);

        // When
        otherIncome.setId(id);
        otherIncome.setTaxReturn(taxReturn);
        otherIncome.setLongTermCapitalGains(longTermCapitalGains);
        otherIncome.setShortTermCapitalGains(shortTermCapitalGains);
        otherIncome.setOtherInvestmentIncome(otherInvestmentIncome);
        otherIncome.setNetBusinessIncome(netBusinessIncome);
        otherIncome.setAdditionalIncome(additionalIncome);

        // Then
        assertThat(otherIncome.getId()).isEqualTo(id);
        assertThat(otherIncome.getTaxReturn()).isEqualTo(taxReturn);
        assertThat(otherIncome.getLongTermCapitalGains()).isEqualTo(longTermCapitalGains);
        assertThat(otherIncome.getShortTermCapitalGains()).isEqualTo(shortTermCapitalGains);
        assertThat(otherIncome.getOtherInvestmentIncome()).isEqualTo(otherInvestmentIncome);
        assertThat(otherIncome.getNetBusinessIncome()).isEqualTo(netBusinessIncome);
        assertThat(otherIncome.getAdditionalIncome()).isEqualTo(additionalIncome);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        TaxReturn taxReturn = new TaxReturn();
        OtherIncome otherIncome1 = new OtherIncome();
        otherIncome1.setId(1);
        otherIncome1.setTaxReturn(taxReturn);
        otherIncome1.setLongTermCapitalGains(BigDecimal.valueOf(1000));
        otherIncome1.setShortTermCapitalGains(BigDecimal.valueOf(500));
        otherIncome1.setOtherInvestmentIncome(BigDecimal.valueOf(200));
        otherIncome1.setNetBusinessIncome(BigDecimal.valueOf(300));
        otherIncome1.setAdditionalIncome(BigDecimal.valueOf(400));

        OtherIncome otherIncome2 = new OtherIncome();
        otherIncome2.setId(1);
        otherIncome2.setTaxReturn(taxReturn);
        otherIncome2.setLongTermCapitalGains(BigDecimal.valueOf(1000));
        otherIncome2.setShortTermCapitalGains(BigDecimal.valueOf(500));
        otherIncome2.setOtherInvestmentIncome(BigDecimal.valueOf(200));
        otherIncome2.setNetBusinessIncome(BigDecimal.valueOf(300));
        otherIncome2.setAdditionalIncome(BigDecimal.valueOf(400));

        // Then
        assertThat(otherIncome1).isEqualTo(otherIncome2);
        assertThat(otherIncome1.hashCode()).isEqualTo(otherIncome2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        TaxReturn taxReturn = new TaxReturn();
        OtherIncome otherIncome = new OtherIncome();
        otherIncome.setId(1);
        otherIncome.setTaxReturn(taxReturn);
        otherIncome.setLongTermCapitalGains(BigDecimal.valueOf(1000));
        otherIncome.setShortTermCapitalGains(BigDecimal.valueOf(500));
        otherIncome.setOtherInvestmentIncome(BigDecimal.valueOf(200));
        otherIncome.setNetBusinessIncome(BigDecimal.valueOf(300));
        otherIncome.setAdditionalIncome(BigDecimal.valueOf(400));

        // When
        String incomeString = otherIncome.toString();

        // Then
        assertThat(incomeString).contains("OtherIncome");
        assertThat(incomeString).contains("id=1");
        assertThat(incomeString).contains("taxReturn=" + taxReturn);
        assertThat(incomeString).contains("longTermCapitalGains=1000");
        assertThat(incomeString).contains("shortTermCapitalGains=500");
        assertThat(incomeString).contains("otherInvestmentIncome=200");
        assertThat(incomeString).contains("netBusinessIncome=300");
        assertThat(incomeString).contains("additionalIncome=400");
    }
}
