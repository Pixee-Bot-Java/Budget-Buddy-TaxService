package com.skillstorm.taxservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxservice.models.MarriedFilerTaxBracket;
import com.skillstorm.taxservice.models.SingleFilerTaxBracket;
import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.repositories.MarriedFilerTaxBracketRepository;
import com.skillstorm.taxservice.repositories.SingleFilerTaxBracketRepository;
import com.skillstorm.taxservice.services.TaxBracketService;
import com.skillstorm.taxservice.services.TaxCalculatorService;

@RestController
public class TaxCalculatorController {

    @Autowired
    private SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;

    @Autowired
    private MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

    @Autowired
    private TaxBracketService taxBracketService;

    @GetMapping("/taxBrackets/test/{id}")
    public List<TaxBracket> getTaxBrackets(@PathVariable int id) {
      List<TaxBracket> brackets = taxBracketService.findByFilingStatusID(id);
      return brackets;
    }


    

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

