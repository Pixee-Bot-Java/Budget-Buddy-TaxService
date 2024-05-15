package com.skillstorm.taxdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxdemo.models.MarriedFilerTaxBracket;
import com.skillstorm.taxdemo.models.SingleFilerTaxBracket;
import com.skillstorm.taxdemo.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxdemo.repositories.SingleFilerTaxBracketRepository;
import com.skillstorm.taxdemo.services.TaxCalculatorService;

@RestController
public class TaxCalculatorController {

    @Autowired
    private SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;

    @Autowired
    private MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

    @GetMapping("/taxBrackets/single")
    public List<SingleFilerTaxBracket> getSingleFilerTaxBrackets() {
        return singleFilerTaxBracketRepository.findAll();
    }

    @GetMapping("/taxBrackets/married")
    public List<MarriedFilerTaxBracket> getMarriedFilerTaxBrackets() {
        return marriedFilerTaxBracketRepository.findAll();
    }

    @Autowired
    private TaxCalculatorService taxCalculatorService;

    @PostMapping("/calculate/single")
    public double calculateSingleFilerTax(@RequestParam int taxableIncome, @RequestParam int creditsApplied) {
        return taxCalculatorService.calculateSingleFilerTax(taxableIncome, creditsApplied);
    }

    @PostMapping("/calculate/married")
    public double calculateMarriedFilerTax(@RequestParam int taxableIncome) {
        return taxCalculatorService.calculateMarriedFilerTax(taxableIncome);
    }
}

