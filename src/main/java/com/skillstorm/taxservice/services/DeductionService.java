package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.Deduction;
import com.skillstorm.taxservice.repositories.DeductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductionService {

    private final DeductionRepository deductionRepository;

    @Autowired
    public DeductionService(DeductionRepository deductionRepository) {
        this.deductionRepository = deductionRepository;
    }

    // Add new Deduction:
    public Deduction addDeduction(Deduction newDeduction) {
        return deductionRepository.saveAndFlush(newDeduction);
    }

    // Find Deduction by ID:
    public Deduction findById(int id) {
        return deductionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("deduction.not.found"));
    }

    // Find list of all Deductions:
    public List<Deduction> findAll() {
        return deductionRepository.findAll();
    }

    // Update Deduction by ID:
    public Deduction updateById(int id, Deduction updatedDeduction) {
        // Verify Deduction exists in the database:
        findById(id);
        updatedDeduction.setId(id);
        return deductionRepository.saveAndFlush(updatedDeduction);
    }

    // Delete Deduction by ID:
    public void deleteById(int id) {
        // Verify Deduction exists in the database:
        findById(id);
        deductionRepository.deleteById(id);
    }
}
