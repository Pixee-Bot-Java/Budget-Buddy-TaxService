package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ChildTaxCreditTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit();

        // Then
        assertThat(credit).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        int perQualifyingChild = 2000;
        int perOtherChild = 1000;
        int incomeThreshold = 75000;
        BigDecimal rateFactor = BigDecimal.valueOf(0.05);
        boolean refundable = true;
        int refundLimit = 1400;
        BigDecimal refundRate = BigDecimal.valueOf(0.15);

        // When
        ChildTaxCredit credit = new ChildTaxCredit(id, perQualifyingChild, perOtherChild, incomeThreshold, rateFactor, refundable, refundLimit, refundRate);

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getPerQualifyingChild()).isEqualTo(perQualifyingChild);
        assertThat(credit.getPerOtherChild()).isEqualTo(perOtherChild);
        assertThat(credit.getIncomeThreshold()).isEqualTo(incomeThreshold);
        assertThat(credit.getRateFactor()).isEqualTo(rateFactor);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit();
        int perQualifyingChild = 2000;
        int perOtherChild = 1000;
        int incomeThreshold = 75000;
        BigDecimal rateFactor = BigDecimal.valueOf(0.05);
        boolean refundable = true;
        int refundLimit = 1400;
        BigDecimal refundRate = BigDecimal.valueOf(0.15);

        // When
        credit.setPerQualifyingChild(perQualifyingChild);
        credit.setPerOtherChild(perOtherChild);
        credit.setIncomeThreshold(incomeThreshold);
        credit.setRateFactor(rateFactor);
        credit.setRefundable(refundable);
        credit.setRefundLimit(refundLimit);
        credit.setRefundRate(refundRate);

        // Then
        assertThat(credit.getPerQualifyingChild()).isEqualTo(perQualifyingChild);
        assertThat(credit.getPerOtherChild()).isEqualTo(perOtherChild);
        assertThat(credit.getIncomeThreshold()).isEqualTo(incomeThreshold);
        assertThat(credit.getRateFactor()).isEqualTo(rateFactor);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        ChildTaxCredit credit1 = new ChildTaxCredit(1, 2000, 1000, 75000, BigDecimal.valueOf(0.05), true, 1400, BigDecimal.valueOf(0.15));
        ChildTaxCredit credit2 = new ChildTaxCredit(1, 2000, 1000, 75000, BigDecimal.valueOf(0.05), true, 1400, BigDecimal.valueOf(0.15));

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit(1, 2000, 1000, 75000, BigDecimal.valueOf(0.05), true, 1400, BigDecimal.valueOf(0.15));

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("ChildTaxCredit");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("perQualifyingChild=2000");
        assertThat(creditString).contains("perOtherChild=1000");
        assertThat(creditString).contains("incomeThreshold=75000");
        assertThat(creditString).contains("rateFactor=0.05");
        assertThat(creditString).contains("refundable=true");
        assertThat(creditString).contains("refundLimit=1400");
        assertThat(creditString).contains("refundRate=0.15");
    }
}
