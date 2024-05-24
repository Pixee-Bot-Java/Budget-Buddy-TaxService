package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;

import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EducationTaxCreditAotcRepositoryTest {

    @Autowired
    private EducationTaxCreditAotcRepository educationTaxCreditAotcRepository;

    @Test
    public void testSaveAndFindById() {
        // Given
        EducationTaxCreditAotc educationTaxCreditAotc = new EducationTaxCreditAotc();
        educationTaxCreditAotc.setFullCreditIncomeThreshold(30000);
        educationTaxCreditAotc.setPartialCreditIncomeThreshold(25000);
        educationTaxCreditAotc.setIncomePartialCreditRate(BigDecimal.valueOf(0.5));
        educationTaxCreditAotc.setMaxCreditAmountPerStudent(2000);
        educationTaxCreditAotc.setExpensesThresholdFullCredit(10000);
        educationTaxCreditAotc.setExpensesThresholdPartialCredit(15000);
        educationTaxCreditAotc.setExpensesPartialCreditRate(BigDecimal.valueOf(0.25));
        educationTaxCreditAotc.setRefundable(true);
        educationTaxCreditAotc.setRefundLimit(300);
        educationTaxCreditAotc.setRefundRate(BigDecimal.valueOf(0.20));

        // When
        EducationTaxCreditAotc savedCredit = educationTaxCreditAotcRepository.save(educationTaxCreditAotc);
        EducationTaxCreditAotc foundCredit = educationTaxCreditAotcRepository.findById(savedCredit.getId()).orElse(null);

        // Then
        assertThat(foundCredit).isNotNull();
        assertThat(foundCredit.getFullCreditIncomeThreshold()).isEqualTo(30000);
        assertThat(foundCredit.getPartialCreditIncomeThreshold()).isEqualTo(25000);
        assertThat(foundCredit.getIncomePartialCreditRate()).isEqualByComparingTo(BigDecimal.valueOf(0.5));
        assertThat(foundCredit.getMaxCreditAmountPerStudent()).isEqualTo(2000);
        assertThat(foundCredit.getExpensesThresholdFullCredit()).isEqualTo(10000);
        assertThat(foundCredit.getExpensesThresholdPartialCredit()).isEqualTo(15000);
        assertThat(foundCredit.getExpensesPartialCreditRate()).isEqualByComparingTo(BigDecimal.valueOf(0.25));
        assertThat(foundCredit.isRefundable()).isTrue();
        assertThat(foundCredit.getRefundLimit()).isEqualTo(300);
        assertThat(foundCredit.getRefundRate()).isEqualByComparingTo(BigDecimal.valueOf(0.20));
    }

    @Test
    public void testDelete() {
        // Given
        EducationTaxCreditAotc educationTaxCreditAotc = new EducationTaxCreditAotc();
        educationTaxCreditAotc.setFullCreditIncomeThreshold(30000);
        educationTaxCreditAotc.setPartialCreditIncomeThreshold(25000);
        educationTaxCreditAotc.setIncomePartialCreditRate(BigDecimal.valueOf(0.5));
        educationTaxCreditAotc.setMaxCreditAmountPerStudent(2000);
        educationTaxCreditAotc.setExpensesThresholdFullCredit(10000);
        educationTaxCreditAotc.setExpensesThresholdPartialCredit(15000);
        educationTaxCreditAotc.setExpensesPartialCreditRate(BigDecimal.valueOf(0.25));
        educationTaxCreditAotc.setRefundable(true);
        educationTaxCreditAotc.setRefundLimit(300);
        educationTaxCreditAotc.setRefundRate(BigDecimal.valueOf(0.20));
        educationTaxCreditAotcRepository.save(educationTaxCreditAotc);

        // When
        educationTaxCreditAotcRepository.delete(educationTaxCreditAotc);

        // Then
        assertThat(educationTaxCreditAotcRepository.findById(educationTaxCreditAotc.getId())).isEmpty();
    }
}
