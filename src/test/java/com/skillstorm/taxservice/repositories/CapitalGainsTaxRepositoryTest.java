package com.skillstorm.taxservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.skillstorm.taxservice.models.CapitalGainsTax;
import com.skillstorm.taxservice.models.FilingStatus;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class CapitalGainsTaxRepositoryTest {

    @Autowired
    private CapitalGainsTaxRepository capitalGainsTaxRepository;

    @MockBean
    private FilingStatusRepository filingStatusRepository;

    @Test
    public void testFindByFilingStatusId() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(1);
        filingStatus.setStatus("Single");

        when(filingStatusRepository.findById(1)).thenReturn(java.util.Optional.of(filingStatus));

        // When
        List<CapitalGainsTax> taxes = capitalGainsTaxRepository.findByFilingStatus_Id(1);

        // Then
        assertThat(taxes).isNotNull();
        assertThat(taxes.size()).isEqualTo(0);
    }
}
