package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class EducationTaxCreditLlcTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        EducationTaxCreditLlc credit = new EducationTaxCreditLlc();

        // Then
        assertThat(credit).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        int fullCreditIncomeThreshold = 80000;
        int partialCreditIncomeThreshold = 90000;
        BigDecimal incomePartialCreditRate = BigDecimal.valueOf(0.5);
        int maxCreditAmount = 2000;
        int expensesThreshold = 4000;
        BigDecimal creditRate = BigDecimal.valueOf(0.75);
        boolean refundable = true;

        // When
        EducationTaxCreditLlc credit = new EducationTaxCreditLlc(
            id, fullCreditIncomeThreshold, partialCreditIncomeThreshold, incomePartialCreditRate,
            maxCreditAmount, expensesThreshold, creditRate, refundable
        );

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getFullCreditIncomeThreshold()).isEqualTo(fullCreditIncomeThreshold);
        assertThat(credit.getPartialCreditIncomeThreshold()).isEqualTo(partialCreditIncomeThreshold);
        assertThat(credit.getIncomePartialCreditRate()).isEqualTo(incomePartialCreditRate);
        assertThat(credit.getMaxCreditAmount()).isEqualTo(maxCreditAmount);
        assertThat(credit.getExpensesThreshold()).isEqualTo(expensesThreshold);
        assertThat(credit.getCreditRate()).isEqualTo(creditRate);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        EducationTaxCreditLlc credit = new EducationTaxCreditLlc();
        int fullCreditIncomeThreshold = 80000;
        int partialCreditIncomeThreshold = 90000;
        BigDecimal incomePartialCreditRate = BigDecimal.valueOf(0.5);
        int maxCreditAmount = 2000;
        int expensesThreshold = 4000;
        BigDecimal creditRate = BigDecimal.valueOf(0.75);
        boolean refundable = true;

        // When
        credit.setFullCreditIncomeThreshold(fullCreditIncomeThreshold);
        credit.setPartialCreditIncomeThreshold(partialCreditIncomeThreshold);
        credit.setIncomePartialCreditRate(incomePartialCreditRate);
        credit.setMaxCreditAmount(maxCreditAmount);
        credit.setExpensesThreshold(expensesThreshold);
        credit.setCreditRate(creditRate);
        credit.setRefundable(refundable);

        // Then
        assertThat(credit.getFullCreditIncomeThreshold()).isEqualTo(fullCreditIncomeThreshold);
        assertThat(credit.getPartialCreditIncomeThreshold()).isEqualTo(partialCreditIncomeThreshold);
        assertThat(credit.getIncomePartialCreditRate()).isEqualTo(incomePartialCreditRate);
        assertThat(credit.getMaxCreditAmount()).isEqualTo(maxCreditAmount);
        assertThat(credit.getExpensesThreshold()).isEqualTo(expensesThreshold);
        assertThat(credit.getCreditRate()).isEqualTo(creditRate);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        EducationTaxCreditLlc credit1 = new EducationTaxCreditLlc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2000, 4000, BigDecimal.valueOf(0.75),
            true
        );
        EducationTaxCreditLlc credit2 = new EducationTaxCreditLlc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2000, 4000, BigDecimal.valueOf(0.75),
            true
        );

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        EducationTaxCreditLlc credit = new EducationTaxCreditLlc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2000, 4000, BigDecimal.valueOf(0.75),
            true
        );

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("EducationTaxCreditLlc");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("fullCreditIncomeThreshold=80000");
        assertThat(creditString).contains("partialCreditIncomeThreshold=90000");
        assertThat(creditString).contains("incomePartialCreditRate=0.5");
        assertThat(creditString).contains("maxCreditAmount=2000");
        assertThat(creditString).contains("expensesThreshold=4000");
        assertThat(creditString).contains("creditRate=0.75");
        assertThat(creditString).contains("refundable=true");
    }
}
