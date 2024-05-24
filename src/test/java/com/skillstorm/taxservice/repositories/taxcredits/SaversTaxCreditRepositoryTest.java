package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.SaversTaxCredit;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SaversTaxCreditRepositoryTest {

    @Autowired
    private SaversTaxCreditRepository saversTaxCreditRepository;

    @Test
    public void testSaveAndFindById() {
        // Given
        SaversTaxCredit saversTaxCredit = new SaversTaxCredit();
        saversTaxCredit.setAgiThresholdFirstContributionLimit(25000);
        saversTaxCredit.setAgiThresholdSecondContributionLimit(50000);
        saversTaxCredit.setAgiThresholdThirdContributionLimit(75000);
        saversTaxCredit.setFirstContributionRate(BigDecimal.valueOf(0.2));
        saversTaxCredit.setSecondContributionRate(BigDecimal.valueOf(0.15));
        saversTaxCredit.setThirdContributionRate(BigDecimal.valueOf(0.1));
        saversTaxCredit.setMaxContributionAmount(5000);
        saversTaxCredit.setRefundable(true);

        // When
        SaversTaxCredit savedCredit = saversTaxCreditRepository.save(saversTaxCredit);
        SaversTaxCredit foundCredit = saversTaxCreditRepository.findById(savedCredit.getId()).orElse(null);

        // Then
        assertThat(foundCredit).isNotNull();
        assertThat(foundCredit.getAgiThresholdFirstContributionLimit()).isEqualTo(25000);
        assertThat(foundCredit.getAgiThresholdSecondContributionLimit()).isEqualTo(50000);
        assertThat(foundCredit.getAgiThresholdThirdContributionLimit()).isEqualTo(75000);
        assertThat(foundCredit.getFirstContributionRate()).isEqualByComparingTo(BigDecimal.valueOf(0.2));
        assertThat(foundCredit.getSecondContributionRate()).isEqualByComparingTo(BigDecimal.valueOf(0.15));
        assertThat(foundCredit.getThirdContributionRate()).isEqualByComparingTo(BigDecimal.valueOf(0.1));
        assertThat(foundCredit.getMaxContributionAmount()).isEqualTo(5000);
        assertThat(foundCredit.isRefundable()).isTrue();
    }

    @Test
    public void testDelete() {
        // Given
        SaversTaxCredit saversTaxCredit = new SaversTaxCredit();
        saversTaxCredit.setAgiThresholdFirstContributionLimit(25000);
        saversTaxCredit.setAgiThresholdSecondContributionLimit(50000);
        saversTaxCredit.setAgiThresholdThirdContributionLimit(75000);
        saversTaxCredit.setFirstContributionRate(BigDecimal.valueOf(0.2));
        saversTaxCredit.setSecondContributionRate(BigDecimal.valueOf(0.15));
        saversTaxCredit.setThirdContributionRate(BigDecimal.valueOf(0.1));
        saversTaxCredit.setMaxContributionAmount(5000);
        saversTaxCredit.setRefundable(true);
        SaversTaxCredit savedCredit = saversTaxCreditRepository.save(saversTaxCredit);

        // When
        saversTaxCreditRepository.delete(savedCredit);

        // Then
        assertThat(saversTaxCreditRepository.findById(savedCredit.getId())).isEmpty();
    }
}
