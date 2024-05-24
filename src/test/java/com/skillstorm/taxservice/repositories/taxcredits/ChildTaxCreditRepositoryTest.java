package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ChildTaxCreditRepositoryTest {

    @Autowired
    private ChildTaxCreditRepository repository;

    @Test
    public void testSaveAndFindById() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit();
        credit.setPerQualifyingChild(2000);
        credit.setPerOtherChild(1000);
        credit.setIncomeThreshold(75000);
        credit.setRateFactor(BigDecimal.valueOf(0.05));
        credit.setRefundable(true);
        credit.setRefundLimit(1400);
        credit.setRefundRate(BigDecimal.valueOf(0.15));

        // When
        ChildTaxCredit savedCredit = repository.save(credit);
        ChildTaxCredit foundCredit = repository.findById(savedCredit.getId()).orElse(null);

        // Then
        assertThat(foundCredit).isNotNull();
        assertThat(foundCredit.getPerQualifyingChild()).isEqualTo(2000);
        assertThat(foundCredit.getPerOtherChild()).isEqualTo(1000);
        assertThat(foundCredit.getIncomeThreshold()).isEqualTo(75000);
        assertThat(foundCredit.getRateFactor()).isEqualTo(BigDecimal.valueOf(0.05));
        assertThat(foundCredit.isRefundable()).isTrue();
        assertThat(foundCredit.getRefundLimit()).isEqualTo(1400);
        assertThat(foundCredit.getRefundRate()).isEqualTo(BigDecimal.valueOf(0.15));
    }

    @Test
    public void testUpdate() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit();
        credit.setPerQualifyingChild(2000);
        credit.setPerOtherChild(1000);
        credit.setIncomeThreshold(75000);
        credit.setRateFactor(BigDecimal.valueOf(0.05));
        credit.setRefundable(true);
        credit.setRefundLimit(1400);
        credit.setRefundRate(BigDecimal.valueOf(0.15));

        ChildTaxCredit savedCredit = repository.save(credit);
        int savedId = savedCredit.getId();

        // When
        savedCredit.setPerQualifyingChild(2500);
        repository.save(savedCredit);
        ChildTaxCredit updatedCredit = repository.findById(savedId).orElse(null);

        // Then
        assertThat(updatedCredit).isNotNull();
        assertThat(updatedCredit.getPerQualifyingChild()).isEqualTo(2500);
    }

    @Test
    public void testDelete() {
        // Given
        ChildTaxCredit credit = new ChildTaxCredit();
        credit.setPerQualifyingChild(2000);
        credit.setPerOtherChild(1000);
        credit.setIncomeThreshold(75000);
        credit.setRateFactor(BigDecimal.valueOf(0.05));
        credit.setRefundable(true);
        credit.setRefundLimit(1400);
        credit.setRefundRate(BigDecimal.valueOf(0.15));

        ChildTaxCredit savedCredit = repository.save(credit);
        int savedId = savedCredit.getId();

        // When
        repository.deleteById(savedId);
        ChildTaxCredit foundCredit = repository.findById(savedId).orElse(null);

        // Then
        assertThat(foundCredit).isNull();
    }
}
