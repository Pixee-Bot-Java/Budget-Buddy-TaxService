package com.skillstorm.taxservice.controllers;

import com.skillstorm.taxservice.dtos.RefundDto;
import com.skillstorm.taxservice.dtos.TaxReturnDeductionDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.services.TaxReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/taxreturns")
public class TaxReturnController {

    private final TaxReturnService taxReturnService;

    @Autowired
    public TaxReturnController(TaxReturnService taxReturnService) {
        this.taxReturnService = taxReturnService;
    }

    // Add new TaxReturn. All we really need is the year and userId. We will
    // get the rest of the information as they fill out the form:
    @PostMapping
    public ResponseEntity<TaxReturnDto> addTaxReturn(@RequestBody TaxReturnDto newTaxReturn) {
        TaxReturnDto createdTaxReturn = taxReturnService.addTaxReturn(newTaxReturn);
        return ResponseEntity.created(URI.create("/" + createdTaxReturn.getId())).body(createdTaxReturn);
    }

    // Get TaxReturn by id:
    @GetMapping("/{id}")
    public ResponseEntity<TaxReturnDto> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(taxReturnService.findById(id));
    }

    // Get current federal tax refund:
    @GetMapping("/{id}/refund")
    public ResponseEntity<RefundDto> getRefund(@PathVariable("id") int id) {
        return ResponseEntity.ok(taxReturnService.getRefund(id));
    }

    // Get all TaxReturns by userId (and optionally by year):
    @GetMapping
    public ResponseEntity<List<TaxReturnDto>> findAllByUserId(@RequestParam("userId") int userId, @RequestParam(value = "year", required = false) Integer year) {
        if(year == null) {
            return ResponseEntity.ok(taxReturnService.findAllByUserId(userId));
        }
        return ResponseEntity.ok(taxReturnService.findAllByUserIdAndYear(userId, year));
    }

    // Update TaxReturn:
    @PutMapping("/{id}")
    public ResponseEntity<TaxReturnDto> updateTaxReturn(@PathVariable("id") int id, @RequestBody TaxReturnDto updatedTaxReturn) {
        return ResponseEntity.ok(taxReturnService.updateTaxReturn(id, updatedTaxReturn));
    }

    // Delete TaxReturn:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxReturn(@PathVariable("id") int id) {
        taxReturnService.deleteTaxReturn(id);
        return ResponseEntity.noContent().build();
    }

    // Claim deductions:
    @PostMapping("/{id}/deductions")
    public ResponseEntity<TaxReturnDeductionDto> claimDeduction(@PathVariable("id") int id, @RequestBody TaxReturnDeductionDto deduction) {
        return ResponseEntity.ok(taxReturnService.claimDeduction(id, deduction));
    }

    // View a TaxReturnDeduction by ID:
    @GetMapping("taxreturn/deductions/{taxReturnDeductionId}")
    public ResponseEntity<TaxReturnDeductionDto> getTaxReturnDeductionById(@PathVariable("taxReturnDeductionId") int taxReturnDeductionId) {
        return ResponseEntity.ok(taxReturnService.getTaxReturnDeductionById(taxReturnDeductionId));
    }

    // Get all deductions for a TaxReturn:
    @GetMapping("/{id}/deductions")
    public ResponseEntity<List<TaxReturnDeductionDto>> getDeductions(@PathVariable("id") int id) {
        return ResponseEntity.ok(taxReturnService.getDeductions(id));
    }

    // Update a TaxReturnDeduction:
    @PutMapping("taxreturn/deductions/{taxReturnDeductionId}")
    public ResponseEntity<TaxReturnDeductionDto> updateTaxReturnDeduction(@PathVariable("taxReturnDeductionId") int taxReturnDeductionId, @RequestBody TaxReturnDeductionDto updatedDeduction) {
        return ResponseEntity.ok(taxReturnService.updateTaxReturnDeduction(taxReturnDeductionId, updatedDeduction));
    }

    // Delete a TaxReturnDeduction:
    @DeleteMapping("taxreturn/deductions/{taxReturnDeductionId}")
    public ResponseEntity<Void> deleteTaxReturnDeduction(@PathVariable("taxReturnDeductionId") int taxReturnDeductionId) {
        taxReturnService.deleteTaxReturnDeduction(taxReturnDeductionId);
        return ResponseEntity.noContent().build();
    }

    // Delete absolutely everything associated with a UserId in the event they delete their account:
    @DeleteMapping("/deleteAll/{userId}")
    public ResponseEntity<Void> deleteAllByUserId(@PathVariable("userId") int userId) {
        taxReturnService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
