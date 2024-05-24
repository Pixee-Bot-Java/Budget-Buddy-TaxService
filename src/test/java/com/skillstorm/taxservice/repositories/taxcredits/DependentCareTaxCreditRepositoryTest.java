package com.skillstorm.taxservice.repositories.taxcredits;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCredit;

import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DependentCareTaxCreditRepositoryTest {

    @Autowired
    private DependentCareTaxCreditRepository dependentCareTaxCreditRepository;

    @Test
    public void testFindById() {
        // Given
        DependentCareTaxCredit dependentCareTaxCredit = new DependentCareTaxCredit();
        dependentCareTaxCredit.setIncomeRange(50000);
        dependentCareTaxCredit.setRate(BigDecimal.valueOf(0.25));
        DependentCareTaxCredit saved = dependentCareTaxCreditRepository.save(dependentCareTaxCredit);

        // When
        Optional<DependentCareTaxCredit> found = dependentCareTaxCreditRepository.findById(saved.getId());

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getIncomeRange()).isEqualTo(50000);
        assertThat(found.get().getRate()).isEqualByComparingTo(BigDecimal.valueOf(0.25));
    }

    @Test
    public void testSave() {
        // Given
        DependentCareTaxCredit dependentCareTaxCredit = new DependentCareTaxCredit();
        dependentCareTaxCredit.setIncomeRange(60000);
        dependentCareTaxCredit.setRate(BigDecimal.valueOf(0.30));

        // When
        DependentCareTaxCredit saved = dependentCareTaxCreditRepository.save(dependentCareTaxCredit);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getIncomeRange()).isEqualTo(60000);
        assertThat(saved.getRate()).isEqualByComparingTo(BigDecimal.valueOf(0.30));
    }

    @Test
    public void testDeleteById() {
        // Given
        DependentCareTaxCredit dependentCareTaxCredit = new DependentCareTaxCredit();
        dependentCareTaxCredit.setIncomeRange(70000);
        dependentCareTaxCredit.setRate(BigDecimal.valueOf(0.35));
        DependentCareTaxCredit saved = dependentCareTaxCreditRepository.save(dependentCareTaxCredit);

        // When
        dependentCareTaxCreditRepository.deleteById(saved.getId());

        // Then
        assertThat(dependentCareTaxCreditRepository.findById(saved.getId())).isEmpty();
    }
}
