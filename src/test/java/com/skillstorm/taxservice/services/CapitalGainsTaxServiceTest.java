package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.CapitalGainsTax;
import com.skillstorm.taxservice.repositories.CapitalGainsTaxRepository;

@ExtendWith(MockitoExtension.class)
class CapitalGainsTaxServiceTest {

    @Mock
    private CapitalGainsTaxRepository capitalGainsTaxRepository;

    @InjectMocks
    private CapitalGainsTaxService capitalGainsTaxService;

    @Test
    void testFindByFilingStatusID_WhenTaxInfoExists_ShouldReturnTaxInfoList() {
        // Mocking the repository method to return a list of tax info
        List<CapitalGainsTax> mockTaxInfoList = new ArrayList<>();
        mockTaxInfoList.add(new CapitalGainsTax());
        when(capitalGainsTaxRepository.findByFilingStatus_Id(anyInt())).thenReturn(mockTaxInfoList);

        // Calling the service method
        List<CapitalGainsTax> result = capitalGainsTaxService.findByFilingStatusID(1);

        // Verifying that the repository method was called with the correct parameter
        verify(capitalGainsTaxRepository).findByFilingStatus_Id(1);

        // Asserting that the result is not null and has the correct size
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByFilingStatusID_WhenTaxInfoDoesNotExist_ShouldThrowNotFoundException() {
      // Mocking the repository method to return an empty list
      when(capitalGainsTaxRepository.findByFilingStatus_Id(anyInt())).thenReturn(Collections.emptyList());

      // Calling the service method and expecting NotFoundException
      assertThrows(NotFoundException.class, () -> {
          capitalGainsTaxService.findByFilingStatusID(1);
      });
    }
}
