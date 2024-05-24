package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EarnedIncomeTaxCreditRepositoryTest {

    @Autowired
    private EarnedIncomeTaxCreditRepository earnedIncomeTaxCreditRepository;

    @Test
    public void testSaveAndFindById() {
        // Given
        EarnedIncomeTaxCredit earnedIncomeTaxCredit = new EarnedIncomeTaxCredit();
        earnedIncomeTaxCredit.setAgiThreshold3Children(30000);
        earnedIncomeTaxCredit.setAgiThreshold2Children(25000);
        earnedIncomeTaxCredit.setAgiThreshold1Children(20000);
        earnedIncomeTaxCredit.setAgiThreshold0Children(15000);
        earnedIncomeTaxCredit.setAmount3Children(1000);
        earnedIncomeTaxCredit.setAmount2Children(750);
        earnedIncomeTaxCredit.setAmount1Children(500);
        earnedIncomeTaxCredit.setAmount0Children(250);
        earnedIncomeTaxCredit.setInvestmentIncomeLimit(5000);
        earnedIncomeTaxCredit.setRefundable(true);
        earnedIncomeTaxCredit.setRefundLimit(200);
        earnedIncomeTaxCredit.setRefundRate(BigDecimal.valueOf(0.20));

        // When
        EarnedIncomeTaxCredit savedCredit = earnedIncomeTaxCreditRepository.save(earnedIncomeTaxCredit);
        EarnedIncomeTaxCredit foundCredit = earnedIncomeTaxCreditRepository.findById(savedCredit.getId()).orElse(null);

        // Then
        assertThat(foundCredit).isNotNull();
        assertThat(foundCredit.getAgiThreshold3Children()).isEqualTo(30000);
        assertThat(foundCredit.getAgiThreshold2Children()).isEqualTo(25000);
        assertThat(foundCredit.getAgiThreshold1Children()).isEqualTo(20000);
        assertThat(foundCredit.getAgiThreshold0Children()).isEqualTo(15000);
        assertThat(foundCredit.getAmount3Children()).isEqualTo(1000);
        assertThat(foundCredit.getAmount2Children()).isEqualTo(750);
        assertThat(foundCredit.getAmount1Children()).isEqualTo(500);
        assertThat(foundCredit.getAmount0Children()).isEqualTo(250);
        assertThat(foundCredit.getInvestmentIncomeLimit()).isEqualTo(5000);
        assertThat(foundCredit.isRefundable()).isTrue();
        assertThat(foundCredit.getRefundLimit()).isEqualTo(200);
        assertThat(foundCredit.getRefundRate()).isEqualByComparingTo(BigDecimal.valueOf(0.20));
    }

    @Test
    public void testDelete() {
        // Given
        EarnedIncomeTaxCredit earnedIncomeTaxCredit = new EarnedIncomeTaxCredit();
        earnedIncomeTaxCredit.setAgiThreshold3Children(30000);
        earnedIncomeTaxCredit.setRefundable(true);
        EarnedIncomeTaxCredit savedCredit = earnedIncomeTaxCreditRepository.save(earnedIncomeTaxCredit);

        // When
        earnedIncomeTaxCreditRepository.deleteById(savedCredit.getId());

        // Then
        assertThat(earnedIncomeTaxCreditRepository.findById(savedCredit.getId())).isEmpty();
    }
}
