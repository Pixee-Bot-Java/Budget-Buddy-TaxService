package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import com.skillstorm.taxservice.dtos.OtherIncomeDto;
import com.skillstorm.taxservice.models.OtherIncome;
import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.repositories.OtherIncomeRepository;
import com.skillstorm.taxservice.repositories.TaxReturnRepository;

@ExtendWith(MockitoExtension.class)
public class OtherIncomeServiceTest {

    @Mock
    private OtherIncomeRepository otherIncomeRepository;

    @Mock
    private TaxReturnRepository taxReturnRepository;

    @Mock
    private Environment env;

    @InjectMocks
    private OtherIncomeService otherIncomeService;

    @Test
    void findById_ExistingId_ReturnsOtherIncomeDto() {
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(1);

        int id = 1;
        OtherIncome otherIncome = new OtherIncome();
        otherIncome.setId(id);
        otherIncome.setTaxReturn(taxReturn);
        otherIncome.setLongTermCapitalGains(BigDecimal.TEN);
        otherIncome.setShortTermCapitalGains(BigDecimal.ZERO);
        otherIncome.setOtherInvestmentIncome(BigDecimal.ZERO);
        otherIncome.setNetBusinessIncome(BigDecimal.ZERO);
        otherIncome.setAdditionalIncome(BigDecimal.ZERO);

        when(otherIncomeRepository.findById(id)).thenReturn(Optional.of(otherIncome));

        OtherIncomeDto result = otherIncomeService.findById(id);

        assertNotNull(result);
        assertEquals(BigDecimal.TEN, result.getLongTermCapitalGains());
    }

    // Test cases for findByTaxReturnId, createOtherIncome, updateOtherIncome, deleteOtherIncome

    @Test
    void sumOtherIncome_ValidOtherIncomeDto_ReturnsSum() throws IllegalAccessException {
        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        otherIncomeDto.setLongTermCapitalGains(BigDecimal.valueOf(100));
        otherIncomeDto.setShortTermCapitalGains(BigDecimal.valueOf(200));
        otherIncomeDto.setOtherInvestmentIncome(BigDecimal.valueOf(300));
        otherIncomeDto.setNetBusinessIncome(BigDecimal.valueOf(400));
        otherIncomeDto.setAdditionalIncome(BigDecimal.valueOf(500));

        BigDecimal sum = otherIncomeDto.getSum();

        assertEquals(BigDecimal.valueOf(1500), sum);
    }

    @Test
    void createOtherIncome_ValidDto_ReturnsCreatedDto() {
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(1);

        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        otherIncomeDto.setTaxReturnId(taxReturn.getId());

        when(taxReturnRepository.findById(1)).thenReturn(Optional.of(new TaxReturn()));
        when(otherIncomeRepository.save(any(OtherIncome.class))).thenAnswer(invocation -> {
            OtherIncome saved = invocation.getArgument(0);
            saved.setId(1);
            return saved;
        });

        OtherIncomeDto result = otherIncomeService.createOtherIncome(otherIncomeDto);

        assertNotNull(result);
        assertEquals(1, result.getTaxReturnId());
    }

    @Test
    void updateOtherIncome_ExistingDto_ReturnsUpdatedDto() {
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(1);

        OtherIncome existingOtherIncome = new OtherIncome();
        existingOtherIncome.setTaxReturn(taxReturn);
        existingOtherIncome.getTaxReturn().setId(1);
        existingOtherIncome.setLongTermCapitalGains(BigDecimal.TEN);

        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        otherIncomeDto.setTaxReturnId(taxReturn.getId());

        when(otherIncomeRepository.findByTaxReturnId(taxReturn.getId())).thenReturn(Optional.of(existingOtherIncome));
        when(otherIncomeRepository.save(any(OtherIncome.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OtherIncomeDto result = otherIncomeService.updateOtherIncome(otherIncomeDto);

        assertNotNull(result);
        assertEquals(BigDecimal.ZERO, result.getLongTermCapitalGains());
    }

    @Test
    void deleteOtherIncome_ExistingDto_DeletesDto() {
        TaxReturn taxReturn = new TaxReturn();
        OtherIncome existingOtherIncome = new OtherIncome();
        existingOtherIncome.setTaxReturn(new TaxReturn());

        when(otherIncomeRepository.findByTaxReturnId(taxReturn.getId())).thenReturn(Optional.of(existingOtherIncome));

        otherIncomeService.deleteOtherIncome(new OtherIncomeDto());

        verify(otherIncomeRepository, times(1)).delete(existingOtherIncome);
    }
}