package com.skillstorm.taxdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxdemo.models.MarriedFilerTaxBracket;
import com.skillstorm.taxdemo.models.SingleFilerTaxBracket;
import com.skillstorm.taxdemo.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxdemo.repositories.SingleFilerTaxBracketRepository;

import java.util.List;

@Service
public class TaxCalculatorService {

    @Autowired
    private SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;

    @Autowired
    private MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

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


