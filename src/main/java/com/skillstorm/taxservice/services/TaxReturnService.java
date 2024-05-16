package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.TaxReturn;
import com.skillstorm.taxservice.repositories.TaxReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxReturnService {

    private final TaxReturnRepository taxReturnRepository;

    @Autowired
    public TaxReturnService(TaxReturnRepository taxReturnRepository) {
        this.taxReturnRepository = taxReturnRepository;
    }

    // Add new TaxReturn:
    public TaxReturn addTaxReturn(TaxReturn newTaxReturn) {
        return taxReturnRepository.saveAndFlush(newTaxReturn);
    }

    // Get TaxReturn by id:
    public TaxReturn findById(int id) {
        return taxReturnRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("taxreturn.not.found"));
    }

    // Find all TaxReturns by userId:
    public List<TaxReturn> findAllByUserId(int userId) {
        return taxReturnRepository.findAllByUserId(userId);
    }

    // Update TaxReturn:
    public TaxReturn updateTaxReturn(int id, TaxReturn updatedTaxReturn) {
        // Verify that the TaxReturn exists:
        findById(id);
        updatedTaxReturn.setId(id);
        return taxReturnRepository.saveAndFlush(updatedTaxReturn);
    }

    // Delete TaxReturn:
    public void deleteTaxReturn(int id) {
        // Verify that the TaxReturn exists:
        findById(id);
        taxReturnRepository.deleteById(id);
    }
}
