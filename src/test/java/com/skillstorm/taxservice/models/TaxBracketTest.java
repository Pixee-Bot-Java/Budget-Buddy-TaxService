package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxBracketTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        TaxBracket taxBracket = new TaxBracket();

        // Then
        assertThat(taxBracket).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        TaxBracket taxBracket = new TaxBracket();
        int id = 1;
        FilingStatus filingStatus = new FilingStatus();
        BigDecimal rate = BigDecimal.valueOf(0.25);
        int minIncome = 10000;
        int maxIncome = 50000;

        // When
        taxBracket.setId(id);
        taxBracket.setFilingStatus(filingStatus);
        taxBracket.setRate(rate);
        taxBracket.setMinIncome(minIncome);
        taxBracket.setMaxIncome(maxIncome);

        // Then
        assertThat(taxBracket.getId()).isEqualTo(id);
        assertThat(taxBracket.getFilingStatus()).isEqualTo(filingStatus);
        assertThat(taxBracket.getRate()).isEqualTo(rate);
        assertThat(taxBracket.getMinIncome()).isEqualTo(minIncome);
        assertThat(taxBracket.getMaxIncome()).isEqualTo(maxIncome);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        TaxBracket taxBracket1 = new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.25), 10000, 50000);
        TaxBracket taxBracket2 = new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.25), 10000, 50000);

        // Then
        assertThat(taxBracket1).isEqualTo(taxBracket2);
        assertThat(taxBracket1.hashCode()).isEqualTo(taxBracket2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        TaxBracket taxBracket = new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.25), 10000, 50000);

        // When
        String bracketString = taxBracket.toString();

        // Then
        assertThat(bracketString).contains("TaxBracket");
        assertThat(bracketString).contains("id=1");
        assertThat(bracketString).contains("filingStatus=" + filingStatus);
        assertThat(bracketString).contains("rate=0.25");
        assertThat(bracketString).contains("minIncome=10000");
        assertThat(bracketString).contains("maxIncome=50000");
    }
}
