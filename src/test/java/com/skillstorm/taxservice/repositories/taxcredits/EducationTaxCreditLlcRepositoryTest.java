package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EducationTaxCreditLlcRepositoryTest {

    @Autowired
    private EducationTaxCreditLlcRepository educationTaxCreditLlcRepository;

    @Test
    public void testSaveAndFindById() {
        // Given
        EducationTaxCreditLlc educationTaxCreditLlc = new EducationTaxCreditLlc();
        educationTaxCreditLlc.setFullCreditIncomeThreshold(30000);
        educationTaxCreditLlc.setPartialCreditIncomeThreshold(25000);
        educationTaxCreditLlc.setIncomePartialCreditRate(BigDecimal.valueOf(0.5));
        educationTaxCreditLlc.setMaxCreditAmount(2000);
        educationTaxCreditLlc.setExpensesThreshold(10000);
        educationTaxCreditLlc.setCreditRate(BigDecimal.valueOf(0.25));
        educationTaxCreditLlc.setRefundable(true);

        // When
        EducationTaxCreditLlc savedCredit = educationTaxCreditLlcRepository.save(educationTaxCreditLlc);
        EducationTaxCreditLlc foundCredit = educationTaxCreditLlcRepository.findById(savedCredit.getId()).orElse(null);

        // Then
        assertThat(foundCredit).isNotNull();
        assertThat(foundCredit.getFullCreditIncomeThreshold()).isEqualTo(30000);
        assertThat(foundCredit.getPartialCreditIncomeThreshold()).isEqualTo(25000);
        assertThat(foundCredit.getIncomePartialCreditRate()).isEqualByComparingTo(BigDecimal.valueOf(0.5));
        assertThat(foundCredit.getMaxCreditAmount()).isEqualTo(2000);
        assertThat(foundCredit.getExpensesThreshold()).isEqualTo(10000);
        assertThat(foundCredit.getCreditRate()).isEqualByComparingTo(BigDecimal.valueOf(0.25));
        assertThat(foundCredit.isRefundable()).isTrue();
    }

    @Test
    public void testDelete() {
        // Given
        EducationTaxCreditLlc educationTaxCreditLlc = new EducationTaxCreditLlc();
        educationTaxCreditLlc.setFullCreditIncomeThreshold(30000);
        educationTaxCreditLlc.setPartialCreditIncomeThreshold(25000);
        educationTaxCreditLlc.setIncomePartialCreditRate(BigDecimal.valueOf(0.5));
        educationTaxCreditLlc.setMaxCreditAmount(2000);
        educationTaxCreditLlc.setExpensesThreshold(10000);
        educationTaxCreditLlc.setCreditRate(BigDecimal.valueOf(0.25));
        educationTaxCreditLlc.setRefundable(true);
        EducationTaxCreditLlc savedCredit = educationTaxCreditLlcRepository.save(educationTaxCreditLlc);

        // When
        educationTaxCreditLlcRepository.delete(savedCredit);

        // Then
        assertThat(educationTaxCreditLlcRepository.findById(savedCredit.getId())).isEmpty();
    }
}
