package com.skillstorm.taxservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.dtos.OtherIncomeDto;
import com.skillstorm.taxservice.dtos.TaxReturnCreditDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.models.MarriedFilerTaxBracket;
import com.skillstorm.taxservice.models.SingleFilerTaxBracket;
import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.models.W2;
import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;
import com.skillstorm.taxservice.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.SingleFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.TaxBracketRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TaxCalculatorService {

    private final TaxReturnService taxReturnService;
    private final TaxReturnCreditService taxReturnCreditService;
    private final TaxCreditService taxCreditService;
    private final TaxReturnDeductionService taxReturnDeductionService;
    private final FilingStatusService filingStatusService;
    private final W2Service w2Service;
    private final OtherIncomeService otherIncomeService;
    private final StandardDeductionService standardDeductionService;
    private final TaxBracketService taxBracketService;
    private final StateTaxService stateTaxService;
    private final CapitalGainsTaxService capitalGainsTaxService;

    public TaxCalculatorService(TaxReturnService taxReturnService,
                                TaxReturnCreditService taxReturnCreditService,
                                TaxCreditService taxCreditService,
                                TaxReturnDeductionService taxReturnDeductionService,
                                FilingStatusService filingStatusService,
                                W2Service w2Service,
                                OtherIncomeService otherIncomeService,
                                StandardDeductionService standardDeductionService,
                                TaxBracketService taxBracketService,
                                StateTaxService stateTaxService,
                                CapitalGainsTaxService capitalGainsTaxService) {
      this.taxReturnService = taxReturnService;
      this.taxReturnCreditService = taxReturnCreditService;
      this.taxCreditService = taxCreditService;
      this.taxReturnDeductionService = taxReturnDeductionService;
      this.filingStatusService = filingStatusService;
      this.w2Service = w2Service;
      this.otherIncomeService = otherIncomeService;
      this.standardDeductionService = standardDeductionService;
      this.taxBracketService = taxBracketService;
      this.stateTaxService = stateTaxService;
      this.capitalGainsTaxService = capitalGainsTaxService;
    }

    public BigDecimal calculateTotalIncome(int taxReturnId) {
      List<W2Dto> w2s = w2Service.findAllByTaxReturnId(taxReturnId);
      BigDecimal w2Income =  w2s.stream().map(W2Dto::getWages)
                              .reduce(BigDecimal.ZERO, BigDecimal::add)
                              .setScale(2, RoundingMode.HALF_UP);
      
      OtherIncomeDto otherIncome = otherIncomeService.findByTaxReturnId(taxReturnId);
      BigDecimal totalOtherIncome = otherIncomeService.sumOtherIncome(otherIncome);

      return w2Income.add(totalOtherIncome);
    }

    // Placeholder for now
    public BigDecimal calculateAgi(int taxReturnId) {
      return calculateTotalIncome(taxReturnId);
    }

    // Placeholder for now
    public BigDecimal calculateTaxableIncome(int taxReturnId) {
      return calculateTotalIncome(taxReturnId);
    }

    public BigDecimal calculateChildTaxCredits(int taxReturnId) {
      TaxReturnDto taxReturn = taxReturnService.findById(taxReturnId);
      TaxReturnCreditDto taxReturnCredit = taxReturnCreditService.findByTaxReturnId(taxReturnId);
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());

      ChildTaxCredit childTaxCredit = filingStatus.getChildTaxCredit();

      BigDecimal creditAmount = BigDecimal.valueOf(0);

      
      return creditAmount;
    }
    





    /* public double calculateSingleFilerTax(int taxableIncome, int creditsApplied) {
        if (taxableIncome <= 0) {
            throw new IllegalArgumentException("Taxable income must be greater than 0");
        }

        double tax = 0.0;
        int remainingIncome = taxableIncome;

        // Fetch tax brackets for single filers
        List<SingleFilerTaxBracket> brackets = singleFilerTaxBracketRepository.findAll();

        // Calculate MARGINAL tax using tax brackets
        for (SingleFilerTaxBracket bracket : brackets) {
            int bracketIncome = Math.min(bracket.getIncomeBracket(), remainingIncome);
            tax += bracketIncome * (bracket.getTaxRate() / 100.0);
            remainingIncome -= bracketIncome;
            if (remainingIncome <= 0) {
                break;
            }
        }

        return tax - creditsApplied;
    }

    public double calculateMarriedFilerTax(int taxableIncome) {
        if (taxableIncome <= 0) {
            throw new IllegalArgumentException("Taxable income must be greater than 0");
        }

        double tax = 0.0;
        int remainingIncome = taxableIncome;

        // Fetch tax brackets for married filers
        List<MarriedFilerTaxBracket> brackets = marriedFilerTaxBracketRepository.findAll();

        // Calculate tax using tax brackets
        for (MarriedFilerTaxBracket bracket : brackets) {
            int bracketIncome = Math.min(bracket.getIncomeBracket(), remainingIncome);
            tax += bracketIncome * (bracket.getTaxRate() / 100.0);
            remainingIncome -= bracketIncome;
            if (remainingIncome <= 0) {
                break;
            }
        }

        return tax;
    } */
}


