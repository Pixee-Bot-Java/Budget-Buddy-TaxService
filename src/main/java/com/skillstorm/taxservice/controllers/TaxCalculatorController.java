package com.skillstorm.taxservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestBody;

import com.skillstorm.taxservice.models.MarriedFilerTaxBracket;
import com.skillstorm.taxservice.models.SingleFilerTaxBracket;
import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.SingleFilerTaxBracketRepository;
import com.skillstorm.taxservice.services.TaxBracketService;
import com.skillstorm.taxservice.services.TaxCalculatorService;

@RestController
public class TaxCalculatorController {

    private final TaxBracketService taxBracketService;
    private final TaxCalculatorService taxCalculatorService;
    private final SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;
    MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

    @Autowired
    public TaxCalculatorController(TaxBracketService taxBracketService, TaxCalculatorService taxCalculatorService,
                                   SingleFilerTaxBracketRepository singleFilerTaxBracketRepository, MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository) {
        this.taxBracketService = taxBracketService;
        this.taxCalculatorService = taxCalculatorService;
        this.singleFilerTaxBracketRepository = singleFilerTaxBracketRepository;
        this.marriedFilerTaxBracketRepository = marriedFilerTaxBracketRepository;
    }

    @GetMapping("/taxBrackets/test/{id}")
    public List<TaxBracket> getTaxBrackets(@PathVariable int id) {
      return taxBracketService.findByFilingStatusID(id);
    }


    @GetMapping("/taxBrackets/single")
    public List<SingleFilerTaxBracket> getSingleFilerTaxBrackets() {
        return singleFilerTaxBracketRepository.findAll();
    }

    @GetMapping("/taxBrackets/married")
    public List<MarriedFilerTaxBracket> getMarriedFilerTaxBrackets() {
        return marriedFilerTaxBracketRepository.findAll();
    }

    @PostMapping("/calculate/single")
    public double calculateSingleFilerTax(@RequestParam int taxableIncome, @RequestParam int creditsApplied) {
        return taxCalculatorService.calculateSingleFilerTax(taxableIncome, creditsApplied);
    }

    @PostMapping("/calculate/married")
    public double calculateMarriedFilerTax(@RequestParam int taxableIncome) {
        return taxCalculatorService.calculateMarriedFilerTax(taxableIncome);
    }
}

