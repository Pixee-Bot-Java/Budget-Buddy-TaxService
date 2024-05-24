package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardDeductionTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        StandardDeduction standardDeduction = new StandardDeduction();

        // Then
        assertThat(standardDeduction).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        StandardDeduction standardDeduction = new StandardDeduction();
        int id = 1;
        FilingStatus filingStatus = new FilingStatus();
        int deductionAmount = 12500;

        // When
        standardDeduction.setId(id);
        standardDeduction.setFilingStatus(filingStatus);
        standardDeduction.setDeductionAmount(deductionAmount);

        // Then
        assertThat(standardDeduction.getId()).isEqualTo(id);
        assertThat(standardDeduction.getFilingStatus()).isEqualTo(filingStatus);
        assertThat(standardDeduction.getDeductionAmount()).isEqualTo(deductionAmount);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        StandardDeduction standardDeduction1 = new StandardDeduction();
        standardDeduction1.setId(1);
        standardDeduction1.setFilingStatus(filingStatus);
        standardDeduction1.setDeductionAmount(12500);

        StandardDeduction standardDeduction2 = new StandardDeduction();
        standardDeduction2.setId(1);
        standardDeduction2.setFilingStatus(filingStatus);
        standardDeduction2.setDeductionAmount(12500);

        // Then
        assertThat(standardDeduction1).isEqualTo(standardDeduction2);
        assertThat(standardDeduction1.hashCode()).isEqualTo(standardDeduction2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        StandardDeduction standardDeduction = new StandardDeduction();
        standardDeduction.setId(1);
        standardDeduction.setFilingStatus(filingStatus);
        standardDeduction.setDeductionAmount(12500);

        // When
        String deductionString = standardDeduction.toString();

        // Then
        assertThat(deductionString).contains("StandardDeduction");
        assertThat(deductionString).contains("id=1");
        assertThat(deductionString).contains("filingStatus=" + filingStatus);
        assertThat(deductionString).contains("deductionAmount=12500");
    }
}