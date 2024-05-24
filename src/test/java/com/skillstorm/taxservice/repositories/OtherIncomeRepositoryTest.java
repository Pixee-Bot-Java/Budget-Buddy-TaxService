package com.skillstorm.taxservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.models.OtherIncome;
import com.skillstorm.taxservice.models.TaxReturn;

@ExtendWith(MockitoExtension.class)
public class OtherIncomeRepositoryTest {

    @Mock
    private OtherIncomeRepository otherIncomeRepository;

    @Mock
    private TaxReturnRepository taxReturnRepository;

    @Test
    public void testFindByTaxReturnId() {
        // Create a dummy TaxReturn object
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(1); // Set an arbitrary ID

        // Create a dummy OtherIncome object
        OtherIncome otherIncome = new OtherIncome();
        otherIncome.setId(1); // Set an arbitrary ID
        otherIncome.setTaxReturn(taxReturn); // Set the dummy TaxReturn object

        // Configure the otherIncomeRepository mock to return the dummy OtherIncome object
        when(otherIncomeRepository.findByTaxReturnId(1)).thenReturn(Optional.of(otherIncome));

        // Perform the test
        Optional<OtherIncome> result = otherIncomeRepository.findByTaxReturnId(1);

        // Assert that the result is not null and contains the expected OtherIncome object
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(otherIncome, result.get());
    }
}
