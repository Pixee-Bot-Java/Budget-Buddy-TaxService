package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.constants.FilingStatus;
import com.skillstorm.taxservice.constants.State;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.repositories.TaxReturnDeductionRepository;
import com.skillstorm.taxservice.repositories.TaxReturnRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxReturnServiceTest {

    @InjectMocks private static TaxReturnService taxReturnService;

    @Mock private static TaxReturnRepository taxReturnRepository;
    @Mock private static TaxReturnDeductionRepository taxReturnDeductionRepository;
    @Mock private static TaxCalculatorService taxCalculatorService;
    @Spy private static Environment environment;

    private static TaxReturnDto newTaxReturn;
    private static TaxReturn returnedNewTaxReturn;
    private static TaxReturnDto updatedTaxReturn;

    @BeforeEach
    void setUp() {
        taxReturnService = new TaxReturnService(taxReturnRepository, taxReturnDeductionRepository, taxCalculatorService, environment);

        setupTaxReturns();
    }

    private void setupTaxReturns() {
        newTaxReturn = new TaxReturnDto();
        newTaxReturn.setYear(2024);
        newTaxReturn.setUserId(1);
        newTaxReturn.setTotalIncome(BigDecimal.ZERO.setScale(2));
        //newTaxReturn.setAdjustedGrossIncome(BigDecimal.ZERO.setScale(2));
        //newTaxReturn.setTaxableIncome(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setFedTaxWithheld(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setStateTaxWithheld(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setSocialSecurityTaxWithheld(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setMedicareTaxWithheld(BigDecimal.ZERO.setScale(2));
        //newTaxReturn.setTotalCredits(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setFederalRefund(BigDecimal.ZERO.setScale(2));
        newTaxReturn.setStateRefund(BigDecimal.ZERO.setScale(2));

        returnedNewTaxReturn = new TaxReturn();
        returnedNewTaxReturn.setId(1);
        returnedNewTaxReturn.setYear(2024);
        returnedNewTaxReturn.setUserId(1);
        returnedNewTaxReturn.setTotalIncome(BigDecimal.ZERO.setScale(2));
        //returnedNewTaxReturn.setAdjustedGrossIncome(BigDecimal.ZERO.setScale(2));
        //returnedNewTaxReturn.setTaxableIncome(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setFedTaxWithheld(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setStateTaxWithheld(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setSocialSecurityTaxWithheld(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setMedicareTaxWithheld(BigDecimal.ZERO.setScale(2));
        //returnedNewTaxReturn.setTotalCredits(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setFederalRefund(BigDecimal.ZERO.setScale(2));
        returnedNewTaxReturn.setStateRefund(BigDecimal.ZERO.setScale(2));

        updatedTaxReturn = new TaxReturnDto();
        updatedTaxReturn.setId(1);
        updatedTaxReturn.setYear(2024);
        updatedTaxReturn.setUserId(1);
        updatedTaxReturn.setFilingStatus(FilingStatus.SINGLE);
        updatedTaxReturn.setFirstName("TestFirstName");
        updatedTaxReturn.setLastName("TestLastName");
        updatedTaxReturn.setAddress("TestAddress");
        updatedTaxReturn.setCity("TestCity");
        updatedTaxReturn.setState(State.AL);
        updatedTaxReturn.setZip("TestZipCode");
        updatedTaxReturn.setTotalIncome(BigDecimal.ZERO.setScale(2));
        //updatedTaxReturn.setAdjustedGrossIncome(BigDecimal.ZERO.setScale(2));
        //updatedTaxReturn.setTaxableIncome(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setFedTaxWithheld(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setStateTaxWithheld(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setSocialSecurityTaxWithheld(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setMedicareTaxWithheld(BigDecimal.ZERO.setScale(2));
        //updatedTaxReturn.setTotalCredits(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setFederalRefund(BigDecimal.ZERO.setScale(2));
        updatedTaxReturn.setStateRefund(BigDecimal.ZERO.setScale(2));
    }

    // Add new TaxReturn:
    @Test
    void addTaxReturn() {

        TaxReturnDto request = new TaxReturnDto();
        request.setYear(2024);
        request.setUserId(1);

        // Define stubbing:
        when(taxReturnRepository.saveAndFlush(newTaxReturn.mapToEntity())).thenReturn(returnedNewTaxReturn);

        // Call the method to be tested:
        TaxReturnDto result = taxReturnService.addTaxReturn(request);

        // Verify the result:
        assertEquals(1, result.getId(), "The TaxReturn ID should be 1.");
        assertEquals(2024, result.getYear(), "The TaxReturn year should be 2024.");
        assertEquals(1, result.getUserId(), "The TaxReturn user ID should be 1.");
    }

    // Get TaxReturn by id success:
    @Test
    void findByIdSuccess() {

        // Define stubbing:
        when(taxReturnRepository.findById(1)).thenReturn(Optional.of(returnedNewTaxReturn));

        // Call the method to be tested:
        TaxReturnDto result = taxReturnService.findById(1);

        // Verify the result:
        assertEquals(1, result.getId(), "The TaxReturn ID should be 1.");
        assertEquals(2024, result.getYear(), "The TaxReturn year should be 2024.");
        assertEquals(1, result.getUserId(), "The TaxReturn user ID should be 1.");
    }

    // Get TaxReturn by id failure:
    @Test
    void findByIdFailure() {

        // Define stubbing:
        when(taxReturnRepository.findById(1)).thenReturn(Optional.empty());

        // Verify the exception
        assertThrows(NotFoundException.class, () -> taxReturnService.findById(1), "NotFoundException should be thrown.");
    }

    // Find all TaxReturns by userId:
    @Test
    void findAllByUserId() {

        // Define stubbing:
        when(taxReturnRepository.findAllByUserId(1)).thenReturn(List.of(returnedNewTaxReturn));

        // Call the method to be tested:
        List<TaxReturnDto> result = taxReturnService.findAllByUserId(1);

        // Verify the result:
        assertEquals(1, result.size(), "The size of the list should be 1.");
        assertEquals(1, result.get(0).getId(), "The TaxReturn ID should be 1.");
        assertEquals(2024, result.get(0).getYear(), "The TaxReturn year should be 2024.");
        assertEquals(1, result.get(0).getUserId(), "The TaxReturn user ID should be 1.");
    }

    // Find all TaxReturns by userId and year:
    @Test
    void findAllByUserIdAndYear() {

        // Define stubbing:
        when(taxReturnRepository.findAllByUserIdAndYear(1, 2024)).thenReturn(List.of(returnedNewTaxReturn));

        // Call the method to be tested:
        List<TaxReturnDto> result = taxReturnService.findAllByUserIdAndYear(1, 2024);

        // Verify the result:
        assertEquals(1, result.size(), "The size of the list should be 1.");
        assertEquals(1, result.get(0).getId(), "The TaxReturn ID should be 1.");
        assertEquals(2024, result.get(0).getYear(), "The TaxReturn year should be 2024.");
        assertEquals(1, result.get(0).getUserId(), "The TaxReturn user ID should be 1.");
    }

    // Update TaxReturn:
    @Test
    void updateTaxReturn() {

        // Define stubbing:
        when(taxReturnRepository.findById(1)).thenReturn(Optional.of(returnedNewTaxReturn));
        when(taxReturnRepository.saveAndFlush(updatedTaxReturn.mapToEntity())).thenReturn(updatedTaxReturn.mapToEntity());

        // Call the method to be tested:
        TaxReturnDto result = taxReturnService.updateTaxReturn(1, updatedTaxReturn);

        // Verify the result:
        assertEquals(1, result.getId(), "The TaxReturn ID should be 1.");
        assertEquals(2024, result.getYear(), "The TaxReturn year should be 2024.");
        assertEquals(1, result.getUserId(), "The TaxReturn user ID should be 1.");
        assertEquals("TestFirstName", result.getFirstName(), "The TaxReturn first name should be TestFirstName.");
        assertEquals("TestLastName", result.getLastName(), "The TaxReturn last name should be TestLastName.");
        assertEquals("TestAddress", result.getAddress(), "The TaxReturn address should be TestAddress.");
        assertEquals("TestCity", result.getCity(), "The TaxReturn city should be TestCity.");
        assertEquals(State.AL, result.getState(), "The TaxReturn state should be Alabama.");
        assertEquals("TestZipCode", result.getZip(), "The TaxReturn zip code should be TestZipCode.");
        assertEquals(BigDecimal.ZERO.setScale(2), result.getFederalRefund(), "The TaxReturn refund should be 0.00.");
    }

    // Delete TaxReturn:
    @Test
    void deleteTaxReturn() {

        // Define stubbing:
        when(taxReturnRepository.findById(1)).thenReturn(Optional.of(updatedTaxReturn.mapToEntity()));

        //Define ArgumentCaptor:
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);

        // Call the method to be tested:
        taxReturnService.deleteTaxReturn(1);

        // Capture the argument passed to the deleteById method
        verify(taxReturnRepository).deleteById(idCaptor.capture());

        // Verify the result:
        assertEquals(1, idCaptor.getValue(), "The TaxReturn ID should be 1.");
    }
}
