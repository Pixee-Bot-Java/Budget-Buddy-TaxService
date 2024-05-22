package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.models.StandardDeduction;
import com.skillstorm.taxservice.repositories.StandardDeductionRepository;

@ExtendWith(MockitoExtension.class)
class StandardDeductionServiceTest {

    @Mock
    private StandardDeductionRepository standardDeductionRepository;

    @InjectMocks
    private StandardDeductionService standardDeductionService;

    @Test
    void getByFilingStatusId_Found_ReturnsStandardDeduction() {
        int filingStatusId = 1;
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(filingStatusId);

        StandardDeduction standardDeduction = new StandardDeduction();
        standardDeduction.setId(1);
        standardDeduction.setFilingStatus(filingStatus);
        standardDeduction.setDeductionAmount(12500);

        when(standardDeductionRepository.findByFilingStatus_Id(filingStatusId)).thenReturn(Optional.of(standardDeduction));

        StandardDeduction result = standardDeductionService.getByFilingStatusId(filingStatusId);

        assertNotNull(result);
        assertEquals(12500, result.getDeductionAmount());
    }

    @Test
    void getByFilingStatusId_NotFound_ThrowsNotFoundException() {
        int filingStatusId = 1;

        when(standardDeductionRepository.findByFilingStatus_Id(filingStatusId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            standardDeductionService.getByFilingStatusId(filingStatusId);
        });

        assertEquals("Standardized deduction not found for filing status id: " + filingStatusId, exception.getMessage());
    }
}
