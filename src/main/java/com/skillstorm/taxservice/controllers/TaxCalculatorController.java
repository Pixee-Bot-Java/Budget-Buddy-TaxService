package com.skillstorm.taxservice.controllers;

import java.util.List;

import com.skillstorm.taxservice.models.Deduction;
import com.skillstorm.taxservice.services.DeductionService;
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
    private final DeductionService deductionService;
    private final SingleFilerTaxBracketRepository singleFilerTaxBracketRepository;
    MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository;

    @Autowired
    public TaxCalculatorController(TaxBracketService taxBracketService, TaxCalculatorService taxCalculatorService,
                                   DeductionService deductionService, SingleFilerTaxBracketRepository singleFilerTaxBracketRepository, MarriedFilerTaxBracketRepository marriedFilerTaxBracketRepository) {
        this.taxBracketService = taxBracketService;
        this.taxCalculatorService = taxCalculatorService;
        this.deductionService = deductionService;
        this.singleFilerTaxBracketRepository = singleFilerTaxBracketRepository;
        this.marriedFilerTaxBracketRepository = marriedFilerTaxBracketRepository;
    }

    // Find Deduction by Deduction ID:
    @GetMapping("/deductions/{id}")
    public ResponseEntity<Deduction> findDeductionById(@PathVariable("id") int id) {
        return ResponseEntity.ok(deductionService.findById(id));
    }

    // Retrieve list of all Deductions:
    @GetMapping("/deductions")
    public ResponseEntity<List<Deduction>> findAllDeductions() {
        return ResponseEntity.ok(deductionService.findAll());
    }

    // Update a Deduction by Deduction ID:
    @PutMapping("/deductions/{id}")
    public ResponseEntity<Deduction> updateDeductionById(@PathVariable("id") int id, @RequestBody Deduction updatedDeduction) {
        return ResponseEntity.ok(deductionService.updateById(id, updatedDeduction));
    }

    // Delete Deduction by Deduction ID:
    @DeleteMapping("deductions/{id}")
    public ResponseEntity<Deduction> deleteDuductionById(@PathVariable("id") int id) {
        deductionService.deleteById(id);
        return ResponseEntity.noContent().build();
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

