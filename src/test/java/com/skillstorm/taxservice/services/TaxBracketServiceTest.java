package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.repositories.TaxBracketRepository;

@ExtendWith(MockitoExtension.class)
class TaxBracketServiceTest {

    @Mock
    private TaxBracketRepository taxBracketRepository;

    @InjectMocks
    private TaxBracketService taxBracketService;

    @Test
    void findByFilingStatusID_Found_ReturnsTaxBrackets() {
        int filingStatusId = 1;

        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(filingStatusId);

        TaxBracket taxBracket1 = new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.1), 0, 10000);
        TaxBracket taxBracket2 = new TaxBracket(2, filingStatus, BigDecimal.valueOf(0.15), 10001, 20000);

        List<TaxBracket> taxBrackets = new ArrayList<>();
        taxBrackets.add(taxBracket1);
        taxBrackets.add(taxBracket2);

        when(taxBracketRepository.findByFilingStatus_Id(filingStatusId)).thenReturn(taxBrackets);

        List<TaxBracket> result = taxBracketService.findByFilingStatusID(filingStatusId);

        assertEquals(2, result.size());
        assertEquals(taxBracket1, result.get(0));
        assertEquals(taxBracket2, result.get(1));
    }

    @Test
    void findByFilingStatusID_NotFound_ReturnsEmptyList() {
        int filingStatusId = 1;

        when(taxBracketRepository.findByFilingStatus_Id(filingStatusId)).thenReturn(new ArrayList<>());

        List<TaxBracket> result = taxBracketService.findByFilingStatusID(filingStatusId);

        assertEquals(0, result.size());
    }
}
