package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class EducationTaxCreditAotcTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        EducationTaxCreditAotc credit = new EducationTaxCreditAotc();

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
        int maxCreditAmountPerStudent = 2500;
        int expensesThresholdFullCredit = 4000;
        int expensesThresholdPartialCredit = 5000;
        BigDecimal expensesPartialCreditRate = BigDecimal.valueOf(0.75);
        boolean refundable = true;
        int refundLimit = 1000;
        BigDecimal refundRate = BigDecimal.valueOf(0.4);

        // When
        EducationTaxCreditAotc credit = new EducationTaxCreditAotc(
            id, fullCreditIncomeThreshold, partialCreditIncomeThreshold, incomePartialCreditRate,
            maxCreditAmountPerStudent, expensesThresholdFullCredit, expensesThresholdPartialCredit,
            expensesPartialCreditRate, refundable, refundLimit, refundRate
        );

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getFullCreditIncomeThreshold()).isEqualTo(fullCreditIncomeThreshold);
        assertThat(credit.getPartialCreditIncomeThreshold()).isEqualTo(partialCreditIncomeThreshold);
        assertThat(credit.getIncomePartialCreditRate()).isEqualTo(incomePartialCreditRate);
        assertThat(credit.getMaxCreditAmountPerStudent()).isEqualTo(maxCreditAmountPerStudent);
        assertThat(credit.getExpensesThresholdFullCredit()).isEqualTo(expensesThresholdFullCredit);
        assertThat(credit.getExpensesThresholdPartialCredit()).isEqualTo(expensesThresholdPartialCredit);
        assertThat(credit.getExpensesPartialCreditRate()).isEqualTo(expensesPartialCreditRate);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        EducationTaxCreditAotc credit = new EducationTaxCreditAotc();
        int fullCreditIncomeThreshold = 80000;
        int partialCreditIncomeThreshold = 90000;
        BigDecimal incomePartialCreditRate = BigDecimal.valueOf(0.5);
        int maxCreditAmountPerStudent = 2500;
        int expensesThresholdFullCredit = 4000;
        int expensesThresholdPartialCredit = 5000;
        BigDecimal expensesPartialCreditRate = BigDecimal.valueOf(0.75);
        boolean refundable = true;
        int refundLimit = 1000;
        BigDecimal refundRate = BigDecimal.valueOf(0.4);

        // When
        credit.setFullCreditIncomeThreshold(fullCreditIncomeThreshold);
        credit.setPartialCreditIncomeThreshold(partialCreditIncomeThreshold);
        credit.setIncomePartialCreditRate(incomePartialCreditRate);
        credit.setMaxCreditAmountPerStudent(maxCreditAmountPerStudent);
        credit.setExpensesThresholdFullCredit(expensesThresholdFullCredit);
        credit.setExpensesThresholdPartialCredit(expensesThresholdPartialCredit);
        credit.setExpensesPartialCreditRate(expensesPartialCreditRate);
        credit.setRefundable(refundable);
        credit.setRefundLimit(refundLimit);
        credit.setRefundRate(refundRate);

        // Then
        assertThat(credit.getFullCreditIncomeThreshold()).isEqualTo(fullCreditIncomeThreshold);
        assertThat(credit.getPartialCreditIncomeThreshold()).isEqualTo(partialCreditIncomeThreshold);
        assertThat(credit.getIncomePartialCreditRate()).isEqualTo(incomePartialCreditRate);
        assertThat(credit.getMaxCreditAmountPerStudent()).isEqualTo(maxCreditAmountPerStudent);
        assertThat(credit.getExpensesThresholdFullCredit()).isEqualTo(expensesThresholdFullCredit);
        assertThat(credit.getExpensesThresholdPartialCredit()).isEqualTo(expensesThresholdPartialCredit);
        assertThat(credit.getExpensesPartialCreditRate()).isEqualTo(expensesPartialCreditRate);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        EducationTaxCreditAotc credit1 = new EducationTaxCreditAotc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2500, 4000, 5000, BigDecimal.valueOf(0.75),
            true, 1000, BigDecimal.valueOf(0.4)
        );
        EducationTaxCreditAotc credit2 = new EducationTaxCreditAotc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2500, 4000, 5000, BigDecimal.valueOf(0.75),
            true, 1000, BigDecimal.valueOf(0.4)
        );

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        EducationTaxCreditAotc credit = new EducationTaxCreditAotc(
            1, 80000, 90000, BigDecimal.valueOf(0.5),
            2500, 4000, 5000, BigDecimal.valueOf(0.75),
            true, 1000, BigDecimal.valueOf(0.4)
        );

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("EducationTaxCreditAotc");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("fullCreditIncomeThreshold=80000");
        assertThat(creditString).contains("partialCreditIncomeThreshold=90000");
        assertThat(creditString).contains("incomePartialCreditRate=0.5");
        assertThat(creditString).contains("maxCreditAmountPerStudent=2500");
        assertThat(creditString).contains("expensesThresholdFullCredit=4000");
        assertThat(creditString).contains("expensesThresholdPartialCredit=5000");
        assertThat(creditString).contains("expensesPartialCreditRate=0.75");
        assertThat(creditString).contains("refundable=true");
        assertThat(creditString).contains("refundLimit=1000");
        assertThat(creditString).contains("refundRate=0.4");
    }
}
