package com.skillstorm.taxservice.models.taxcredits;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SaversTaxCreditTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        SaversTaxCredit credit = new SaversTaxCredit();

        // Then
        assertThat(credit).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        int agiThresholdFirstContributionLimit = 20000;
        int agiThresholdSecondContributionLimit = 30000;
        int agiThresholdThirdContributionLimit = 40000;
        BigDecimal firstContributionRate = BigDecimal.valueOf(0.5);
        BigDecimal secondContributionRate = BigDecimal.valueOf(0.3);
        BigDecimal thirdContributionRate = BigDecimal.valueOf(0.1);
        int maxContributionAmount = 2000;
        boolean refundable = true;

        // When
        SaversTaxCredit credit = new SaversTaxCredit(
            id, agiThresholdFirstContributionLimit, agiThresholdSecondContributionLimit,
            agiThresholdThirdContributionLimit, firstContributionRate, secondContributionRate,
            thirdContributionRate, maxContributionAmount, refundable
        );

        // Then
        assertThat(credit.getId()).isEqualTo(id);
        assertThat(credit.getAgiThresholdFirstContributionLimit()).isEqualTo(agiThresholdFirstContributionLimit);
        assertThat(credit.getAgiThresholdSecondContributionLimit()).isEqualTo(agiThresholdSecondContributionLimit);
        assertThat(credit.getAgiThresholdThirdContributionLimit()).isEqualTo(agiThresholdThirdContributionLimit);
        assertThat(credit.getFirstContributionRate()).isEqualTo(firstContributionRate);
        assertThat(credit.getSecondContributionRate()).isEqualTo(secondContributionRate);
        assertThat(credit.getThirdContributionRate()).isEqualTo(thirdContributionRate);
        assertThat(credit.getMaxContributionAmount()).isEqualTo(maxContributionAmount);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        SaversTaxCredit credit = new SaversTaxCredit();
        int agiThresholdFirstContributionLimit = 20000;
        int agiThresholdSecondContributionLimit = 30000;
        int agiThresholdThirdContributionLimit = 40000;
        BigDecimal firstContributionRate = BigDecimal.valueOf(0.5);
        BigDecimal secondContributionRate = BigDecimal.valueOf(0.3);
        BigDecimal thirdContributionRate = BigDecimal.valueOf(0.1);
        int maxContributionAmount = 2000;
        boolean refundable = true;

        // When
        credit.setAgiThresholdFirstContributionLimit(agiThresholdFirstContributionLimit);
        credit.setAgiThresholdSecondContributionLimit(agiThresholdSecondContributionLimit);
        credit.setAgiThresholdThirdContributionLimit(agiThresholdThirdContributionLimit);
        credit.setFirstContributionRate(firstContributionRate);
        credit.setSecondContributionRate(secondContributionRate);
        credit.setThirdContributionRate(thirdContributionRate);
        credit.setMaxContributionAmount(maxContributionAmount);
        credit.setRefundable(refundable);

        // Then
        assertThat(credit.getAgiThresholdFirstContributionLimit()).isEqualTo(agiThresholdFirstContributionLimit);
        assertThat(credit.getAgiThresholdSecondContributionLimit()).isEqualTo(agiThresholdSecondContributionLimit);
        assertThat(credit.getAgiThresholdThirdContributionLimit()).isEqualTo(agiThresholdThirdContributionLimit);
        assertThat(credit.getFirstContributionRate()).isEqualTo(firstContributionRate);
        assertThat(credit.getSecondContributionRate()).isEqualTo(secondContributionRate);
        assertThat(credit.getThirdContributionRate()).isEqualTo(thirdContributionRate);
        assertThat(credit.getMaxContributionAmount()).isEqualTo(maxContributionAmount);
        assertThat(credit.isRefundable()).isEqualTo(refundable);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        SaversTaxCredit credit1 = new SaversTaxCredit(
            1, 20000, 30000, 40000,
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.1), 2000, true
        );
        SaversTaxCredit credit2 = new SaversTaxCredit(
            1, 20000, 30000, 40000,
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.1), 2000, true
        );

        // Then
        assertThat(credit1).isEqualTo(credit2);
        assertThat(credit1.hashCode()).isEqualTo(credit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        SaversTaxCredit credit = new SaversTaxCredit(
            1, 20000, 30000, 40000,
            BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.1), 2000, true
        );

        // When
        String creditString = credit.toString();

        // Then
        assertThat(creditString).contains("SaversTaxCredit");
        assertThat(creditString).contains("id=1");
        assertThat(creditString).contains("agiThresholdFirstContributionLimit=20000");
        assertThat(creditString).contains("agiThresholdSecondContributionLimit=30000");
        assertThat(creditString).contains("agiThresholdThirdContributionLimit=40000");
        assertThat(creditString).contains("firstContributionRate=0.5");
        assertThat(creditString).contains("secondContributionRate=0.3");
        assertThat(creditString).contains("thirdContributionRate=0.1");
        assertThat(creditString).contains("maxContributionAmount=2000");
        assertThat(creditString).contains("refundable=true");
    }
}
