package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.dtos.RefundDto;
import com.skillstorm.taxservice.dtos.TaxReturnDeductionDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.repositories.TaxReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:SystemMessages.properties")
public class TaxReturnService {

    private final TaxReturnRepository taxReturnRepository;
    private final TaxReturnCreditService taxReturnCreditService;
    private final TaxReturnDeductionService taxReturnDeductionService;
    private final Environment environment;

    @Autowired
    public TaxReturnService(TaxReturnRepository taxReturnRepository, TaxReturnCreditService taxReturnCreditService,
                            TaxReturnDeductionService taxReturnDeductionService, Environment environment) {
        this.taxReturnRepository = taxReturnRepository;
        this.taxReturnCreditService = taxReturnCreditService;
        this.taxReturnDeductionService = taxReturnDeductionService;
        this.environment = environment;
    }

    // Add new TaxReturn:
    public TaxReturnDto addTaxReturn(TaxReturnDto newTaxReturn) {
        calculateRefundAmount(newTaxReturn);
        return new TaxReturnDto(taxReturnRepository.saveAndFlush(newTaxReturn.mapToEntity()));
    }

    // Get TaxReturn by id:
    public TaxReturnDto findById(int id) {
        TaxReturnDto taxReturnDto = new TaxReturnDto(taxReturnRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(environment.getProperty("taxreturn.not.found") + " " + id)));
        calculateRefundAmount(taxReturnDto);
        return taxReturnDto;
    }

    // Find all TaxReturns by userId:
    public List<TaxReturnDto> findAllByUserId(int userId) {
        return taxReturnRepository.findAllByUserId(userId)
                .stream().map(TaxReturnDto::new).toList();
    }

    // Find all TaxReturns by userId and year:
    public List<TaxReturnDto> findAllByUserIdAndYear(int userId, int year) {
        return taxReturnRepository.findAllByUserIdAndYear(userId, year)
                .stream().map(TaxReturnDto::new).toList();
    }

    // Update TaxReturn:
    public TaxReturnDto updateTaxReturn(int id, TaxReturnDto updatedTaxReturn) {
        // Verify that the TaxReturn exists:
        findById(id);

        // Set the ID of the updated TaxReturn in case it was not set in the request body:
        updatedTaxReturn.setId(id);

        // Recalculate the refund amount to ensure it is up-to-date:\
        calculateRefundAmount(updatedTaxReturn);

        // Save the updated TaxReturn to the database:
        return new TaxReturnDto(taxReturnRepository.saveAndFlush(updatedTaxReturn.mapToEntity()));
    }

    // Delete TaxReturn:
    public void deleteTaxReturn(int id) {
        // Verify that the TaxReturn exists:
        findById(id);
        taxReturnRepository.deleteById(id);
    }

    // Calculate the Tax Refund for a TaxReturn:
    private void calculateRefundAmount(TaxReturnDto updatedTaxReturn) {

        // Set financial values for the TaxReturn:
        setFinancialValues(updatedTaxReturn);

        // With all of our fields set, let's run the TaxCalculator. Placeholder for now:
        updatedTaxReturn.setFederalRefund(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));

        // Also calculate the state refund. Placeholder for now:
        updatedTaxReturn.setStateRefund(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }

    // Populate the monetary values for a TaxReturn:
    private void setFinancialValues(TaxReturnDto updatedTaxReturn) {
        // Calculate the total income for the TaxReturn:
        updatedTaxReturn.setTotalIncome(getTotalIncome(updatedTaxReturn));

        // Calculate all taxes withheld for the TaxReturn:
        calculateTaxesWithheld(updatedTaxReturn);

        // Calculate total credits and deductions for the TaxReturn:
        calculateCreditsAndDeductions(updatedTaxReturn);

        // Calculate the adjusted gross income for the TaxReturn:
        calculateAgi(updatedTaxReturn);

        // Calculate the taxable income for the TaxReturn:
        calculateTaxableIncome(updatedTaxReturn);
    }

    // Calculate the taxable income for a TaxReturn:
    private void calculateTaxableIncome(TaxReturnDto updatedTaxReturn) {
        // Placeholder for now:
        updatedTaxReturn.setTaxableIncome(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }

    // Calculate the adjusted gross income for a TaxReturn:
    private void calculateAgi(TaxReturnDto updatedTaxReturn) {
        // Placeholder for now:
        updatedTaxReturn.setAdjustedGrossIncome(updatedTaxReturn
                .getTotalIncome()
                .subtract(updatedTaxReturn.getTotalDeductions())
                .setScale(2, RoundingMode.HALF_UP));
    }

    // Calculate the total credits and deductions for a TaxReturn:
    private void calculateCreditsAndDeductions(TaxReturnDto updatedTaxReturn) {
        // Will need to sum up all of the TaxReturnCredits associated with the TaxReturn. Placeholder for now:
        updatedTaxReturn.setTotalCredits(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));

        // Will need to sum up all of the TaxReturnDeductions associated with the TaxReturn. Placeholder for now:
        updatedTaxReturn.setTotalDeductions(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }

    // Calculate the total of all taxes already withheld for a TaxReturn:
    private void calculateTaxesWithheld(TaxReturnDto updatedTaxReturn) {
        updatedTaxReturn.setFedTaxWithheld(getFederalTaxesWithheld(updatedTaxReturn));
        updatedTaxReturn.setStateTaxWithheld(getStateTaxesWithheld(updatedTaxReturn));
        updatedTaxReturn.setSocialSecurityTaxWithheld(getSocialSecurityTaxesWithheld(updatedTaxReturn));
        updatedTaxReturn.setMedicareTaxWithheld(getMedicareTaxesWithheld(updatedTaxReturn));
    }

    // Calculate total medicare taxes already withheld for a TaxReturn:
    private BigDecimal getMedicareTaxesWithheld(TaxReturnDto updatedTaxReturn) {
        return updatedTaxReturn.getW2s().stream().map(W2Dto::getMedicareTaxWithheld)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Calculate total social security taxes already withheld for a TaxReturn:
    private BigDecimal getSocialSecurityTaxesWithheld(TaxReturnDto updatedTaxReturn) {
        return updatedTaxReturn.getW2s().stream().map(W2Dto::getSocialSecurityTaxWithheld)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Calculate total state income taxes already withheld for a TaxReturn:
    private BigDecimal getStateTaxesWithheld(TaxReturnDto updatedTaxReturn) {
        return updatedTaxReturn.getW2s().stream().map(W2Dto::getStateIncomeTaxWithheld)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Calculate total federal income taxes already withheld for a TaxReturn:
    private BigDecimal getFederalTaxesWithheld(TaxReturnDto updatedTaxReturn) {
        return updatedTaxReturn.getW2s().stream().map(W2Dto::getFederalIncomeTaxWithheld)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Iterate through the W2s and sum the wages to get the total income:
    private BigDecimal getTotalIncome(TaxReturnDto updatedTaxReturn) {
        return updatedTaxReturn.getW2s().stream().map(W2Dto::getWages)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    // Claim deductions for a TaxReturn or update its existing ones:
    public List<TaxReturnDeductionDto> claimDeductions(int id, List<TaxReturnDeductionDto> deductions) {
        return taxReturnDeductionService.saveAndUpdateByTaxReturnId(id, deductions);
    }

    // Get the current tax refund for a TaxReturn:
    public RefundDto getRefund(int id) {
        TaxReturnDto taxReturnDto = findById(id);
        return new RefundDto(taxReturnDto.getFederalRefund(), taxReturnDto.getStateRefund());
    }

    // Clean up all entities associated with a User when they delete their account:
    @Transactional
    public void deleteAllByUserId(int userId) {
        taxReturnRepository.deleteAllByUserId(userId);
    }
}
