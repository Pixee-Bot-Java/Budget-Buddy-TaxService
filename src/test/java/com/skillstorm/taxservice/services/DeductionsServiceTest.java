package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.Deduction;
import com.skillstorm.taxservice.repositories.DeductionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeductionsServiceTest {

    @InjectMocks
    private static DeductionService deductionService;

    @Mock
    private static DeductionRepository deductionRepository;

    private Deduction returnedDeduction;
    private List<Deduction> deductionList;

    @BeforeEach
    public void setup() {
        deductionService = new DeductionService(deductionRepository);

        setupDeductions();
    }

    private void setupDeductions() {

        returnedDeduction = new Deduction();
        returnedDeduction.setId(1);
        returnedDeduction.setName("Test Deduction");
        returnedDeduction.setAgiLimit(BigDecimal.valueOf(0.25));

        deductionList = List.of(
                new Deduction(1, "Test Deduction 1", BigDecimal.valueOf(0.25)),
                new Deduction(2, "Test Deduction 2", BigDecimal.valueOf(0.50)),
                new Deduction(3, "Test Deduction 3", BigDecimal.valueOf(0.75))
        );
    }

    // Test find Deduction by ID Success:
    @Test
    void findDeductionByIdSuccessTest() {

        // Define stubbing:
        when(deductionRepository.findById(1)).thenReturn(java.util.Optional.of(returnedDeduction));

        // Call method to test:
        Deduction result = deductionService.findById(1);

        // Verify:
        assertEquals(1, result.getId(), "Deduction ID should be: 1");
        assertEquals("Test Deduction", result.getName(), "Deduction name should be: Test Deduction");
        assertEquals(BigDecimal.valueOf(0.25), result.getAgiLimit(), "Adjusted Gross Income Limit should be: 0.25");
    }

    // Test find Deduction by ID Failure:
    @Test
    void findDeductionByIdFailureTest() {

        // Define stubbing:
        when(deductionRepository.findById(1)).thenReturn(Optional.empty());

        // Call method to test:
        assertThrows(NotFoundException.class, () -> deductionService.findById(1), "NotFoundException should be thrown");
    }

    // Test find all Deductions Success:
    @Test
    void findAllDeductionsSuccessTest() {

        // Define stubbing:
        when(deductionRepository.findAll()).thenReturn(deductionList);

        // Call method to test:
        List<Deduction> result = deductionService.findAll();

        // Verify:
        assertEquals(3, result.size(), "List of Deductions should contain 3 Deductions");
    }
}
