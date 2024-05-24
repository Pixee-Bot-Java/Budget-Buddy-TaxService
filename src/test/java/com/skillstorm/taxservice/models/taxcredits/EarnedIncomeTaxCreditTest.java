package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class EarnedIncomeTaxCreditTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        EarnedIncomeTaxCredit credit = new EarnedIncomeTaxCredit();

        // Then
        assertThat(credit).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        int agiThreshold3Children = 50000;
        int agiThreshold2Children = 40000;
        int agiThreshold1Children = 30000;
        int agiThreshold0Children = 20000;
        int amount3Children = 6000;
        int amount2Children = 5000;
        int amount1Children = 4000;
        int amount0Children = 3000;
        int investmentIncomeLimit = 10000;
        boolean refundable = true;
        int refundLimit = 1400;
        BigDecimal refundRate = BigDecimal.valueOf(0.15);

        // When
        EarnedIncomeTaxCredit credit = new EarnedIncomeTaxCredit(
            id, agiThreshold3Children, agiThreshold2Children, agiThreshold1Children, agiThreshold0Children,
            amount3Children, amount2Children, amount1Children, amount0Children,
            investmentIncomeLimit, refundable, refundLimit, refundRate
        );

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getAgiThreshold3Children()).isEqualTo(agiThreshold3Children);
        assertThat(credit.getAgiThreshold2Children()).isEqualTo(agiThreshold2Children);
        assertThat(credit.getAgiThreshold1Children()).isEqualTo(agiThreshold1Children);
        assertThat(credit.getAgiThreshold0Children()).isEqualTo(agiThreshold0Children);
        assertThat(credit.getAmount3Children()).isEqualTo(amount3Children);
        assertThat(credit.getAmount2Children()).isEqualTo(amount2Children);
        assertThat(credit.getAmount1Children()).isEqualTo(amount1Children);
        assertThat(credit.getAmount0Children()).isEqualTo(amount0Children);
        assertThat(credit.getInvestmentIncomeLimit()).isEqualTo(investmentIncomeLimit);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        EarnedIncomeTaxCredit credit = new EarnedIncomeTaxCredit();
        int agiThreshold3Children = 50000;
        int agiThreshold2Children = 40000;
        int agiThreshold1Children = 30000;
        int agiThreshold0Children = 20000;
        int amount3Children = 6000;
        int amount2Children = 5000;
        int amount1Children = 4000;
        int amount0Children = 3000;
        int investmentIncomeLimit = 10000;
        boolean refundable = true;
        int refundLimit = 1400;
        BigDecimal refundRate = BigDecimal.valueOf(0.15);

        // When
        credit.setAgiThreshold3Children(agiThreshold3Children);
        credit.setAgiThreshold2Children(agiThreshold2Children);
        credit.setAgiThreshold1Children(agiThreshold1Children);
        credit.setAgiThreshold0Children(agiThreshold0Children);
        credit.setAmount3Children(amount3Children);
        credit.setAmount2Children(amount2Children);
        credit.setAmount1Children(amount1Children);
        credit.setAmount0Children(amount0Children);
        credit.setInvestmentIncomeLimit(investmentIncomeLimit);
        credit.setRefundable(refundable);
        credit.setRefundLimit(refundLimit);
        credit.setRefundRate(refundRate);

        // Then
        assertThat(credit.getAgiThreshold3Children()).isEqualTo(agiThreshold3Children);
        assertThat(credit.getAgiThreshold2Children()).isEqualTo(agiThreshold2Children);
        assertThat(credit.getAgiThreshold1Children()).isEqualTo(agiThreshold1Children);
        assertThat(credit.getAgiThreshold0Children()).isEqualTo(agiThreshold0Children);
        assertThat(credit.getAmount3Children()).isEqualTo(amount3Children);
        assertThat(credit.getAmount2Children()).isEqualTo(amount2Children);
        assertThat(credit.getAmount1Children()).isEqualTo(amount1Children);
        assertThat(credit.getAmount0Children()).isEqualTo(amount0Children);
        assertThat(credit.getInvestmentIncomeLimit()).isEqualTo(investmentIncomeLimit);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
        assertThat(credit.getRefundLimit()).isEqualTo(refundLimit);
        assertThat(credit.getRefundRate()).isEqualTo(refundRate);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        EarnedIncomeTaxCredit credit1 = new EarnedIncomeTaxCredit(
            1, 50000, 40000, 30000, 20000,
            6000, 5000, 4000, 3000,
            10000, true, 1400, BigDecimal.valueOf(0.15)
        );
        EarnedIncomeTaxCredit credit2 = new EarnedIncomeTaxCredit(
            1, 50000, 40000, 30000, 20000,
            6000, 5000, 4000, 3000,
            10000, true, 1400, BigDecimal.valueOf(0.15)
        );

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        EarnedIncomeTaxCredit credit = new EarnedIncomeTaxCredit(
            1, 50000, 40000, 30000, 20000,
            6000, 5000, 4000, 3000,
            10000, true, 1400, BigDecimal.valueOf(0.15)
        );

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("EarnedIncomeTaxCredit");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("agiThreshold3Children=50000");
        assertThat(creditString).contains("agiThreshold2Children=40000");
        assertThat(creditString).contains("agiThreshold1Children=30000");
        assertThat(creditString).contains("agiThreshold0Children=20000");
        assertThat(creditString).contains("amount3Children=6000");
        assertThat(creditString).contains("amount2Children=5000");
        assertThat(creditString).contains("amount1Children=4000");
        assertThat(creditString).contains("amount0Children=3000");
        assertThat(creditString).contains("investmentIncomeLimit=10000");
        assertThat(creditString).contains("refundable=true");
        assertThat(creditString).contains("refundLimit=1400");
        assertThat(creditString).contains("refundRate=0.15");
    }
}
