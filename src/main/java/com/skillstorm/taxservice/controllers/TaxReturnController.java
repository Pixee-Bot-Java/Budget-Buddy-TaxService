package com.skillstorm.taxservice.controllers;

import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.services.TaxReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/taxreturn")
public class TaxReturnController {

    private final TaxReturnService taxReturnService;

    @Autowired
    public TaxReturnController(TaxReturnService taxReturnService) {
        this.taxReturnService = taxReturnService;
    }

    // Add new TaxReturn. All we really need is the year and userId. We will
    // get the rest of the information as they fill out the form:
    @PostMapping
    public ResponseEntity<TaxReturn> addTaxReturn(TaxReturn newTaxReturn) {
        TaxReturn createdTaxReturn = taxReturnService.addTaxReturn(newTaxReturn);
        return ResponseEntity.created(URI.create("/" + createdTaxReturn.getId())).body(createdTaxReturn);
    }

    // Get TaxReturn by id:
    @GetMapping("/{id}")
    public ResponseEntity<TaxReturn> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(taxReturnService.findById(id));
    }

    // Get all TaxReturns by userId:
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaxReturn>> findAllByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(taxReturnService.findAllByUserId(userId));
    }

    // Update TaxReturn:
    @PutMapping("/{id}")
    public ResponseEntity<TaxReturn> updateTaxReturn(@PathVariable("id") int id, TaxReturn updatedTaxReturn) {
        return ResponseEntity.ok(taxReturnService.updateTaxReturn(id, updatedTaxReturn));
    }

    // Delete TaxReturn:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxReturn(@PathVariable("id") int id) {
        taxReturnService.deleteTaxReturn(id);
        return ResponseEntity.noContent().build();
    }
}
