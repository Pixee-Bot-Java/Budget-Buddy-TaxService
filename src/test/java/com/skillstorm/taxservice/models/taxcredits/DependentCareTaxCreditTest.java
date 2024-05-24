package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DependentCareTaxCreditTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        DependentCareTaxCredit credit = new DependentCareTaxCredit();

        // Then
        assertThat(credit).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        int incomeRange = 50000;
        BigDecimal rate = BigDecimal.valueOf(0.20);

        // When
        DependentCareTaxCredit credit = new DependentCareTaxCredit(id, incomeRange, rate);

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getIncomeRange()).isEqualTo(incomeRange);
        assertThat(credit.getRate()).isEqualTo(rate);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        DependentCareTaxCredit credit = new DependentCareTaxCredit();
        int incomeRange = 50000;
        BigDecimal rate = BigDecimal.valueOf(0.20);

        // When
        credit.setIncomeRange(incomeRange);
        credit.setRate(rate);

        // Then
        assertThat(credit.getIncomeRange()).isEqualTo(incomeRange);
        assertThat(credit.getRate()).isEqualTo(rate);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        DependentCareTaxCredit credit1 = new DependentCareTaxCredit(1, 50000, BigDecimal.valueOf(0.20));
        DependentCareTaxCredit credit2 = new DependentCareTaxCredit(1, 50000, BigDecimal.valueOf(0.20));

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        DependentCareTaxCredit credit = new DependentCareTaxCredit(1, 50000, BigDecimal.valueOf(0.20));

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("DependentCareTaxCredit");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("incomeRange=50000");
        assertThat(creditString).contains("rate=0.2");
    }
}
