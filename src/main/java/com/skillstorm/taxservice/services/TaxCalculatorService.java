package com.skillstorm.taxservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.models.MarriedFilerTaxBracket;
import com.skillstorm.taxservice.models.SingleFilerTaxBracket;
import com.skillstorm.taxservice.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.SingleFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.TaxBracketRepository;

import java.util.List;

@Service
public class TaxCalculatorService {

    @Autowired
    private SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;

    @Autowired
    private MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

    @Autowired
    private TaxBracketRepository taxBracketRepository;

    





    public double calculateSingleFilerTax(int taxableIncome, int creditsApplied) {
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
    }
}


