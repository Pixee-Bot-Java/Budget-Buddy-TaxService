package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CapitalGainsTaxTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        CapitalGainsTax tax = new CapitalGainsTax();

        // Then
        assertThat(tax).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        FilingStatus filingStatus = Mockito.mock(FilingStatus.class);
        BigDecimal rate = BigDecimal.valueOf(0.15);
        int incomeRange = 50000;

        // When
        CapitalGainsTax tax = new CapitalGainsTax(id, filingStatus, rate, incomeRange);

        // Then
        assertThat(tax.getId()).isEqualTo(id);
        assertThat(tax.getFilingStatus()).isEqualTo(filingStatus);
        assertThat(tax.getRate()).isEqualTo(rate);
        assertThat(tax.getIncomeRange()).isEqualTo(incomeRange);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        CapitalGainsTax tax = new CapitalGainsTax();
        FilingStatus filingStatus = Mockito.mock(FilingStatus.class);
        BigDecimal rate = BigDecimal.valueOf(0.15);
        int incomeRange = 50000;

        // When
        tax.setId(1);
        tax.setFilingStatus(filingStatus);
        tax.setRate(rate);
        tax.setIncomeRange(incomeRange);

        // Then
        assertThat(tax.getId()).isEqualTo(1);
        assertThat(tax.getFilingStatus()).isEqualTo(filingStatus);
        assertThat(tax.getRate()).isEqualTo(rate);
        assertThat(tax.getIncomeRange()).isEqualTo(incomeRange);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        FilingStatus filingStatus = Mockito.mock(FilingStatus.class);
        CapitalGainsTax tax1 = new CapitalGainsTax(1, filingStatus, BigDecimal.valueOf(0.15), 50000);
        CapitalGainsTax tax2 = new CapitalGainsTax(1, filingStatus, BigDecimal.valueOf(0.15), 50000);

        // Then
        assertThat(tax1).isEqualTo(tax2);
        assertThat(tax1.hashCode()).isEqualTo(tax2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        FilingStatus filingStatus = Mockito.mock(FilingStatus.class);
        CapitalGainsTax tax = new CapitalGainsTax(1, filingStatus, BigDecimal.valueOf(0.15), 50000);

        // When
        String taxString = tax.toString();

        // Then
        assertThat(taxString).contains("CapitalGainsTax");
        assertThat(taxString).contains("id=1");
        assertThat(taxString).contains("filingStatus=" + filingStatus);
        assertThat(taxString).contains("rate=0.15");
        assertThat(taxString).contains("incomeRange=50000");
    }
}
