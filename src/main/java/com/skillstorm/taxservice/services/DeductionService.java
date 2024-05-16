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

    // Find Deduction by ID:
    public Deduction findById(int id) {
        return deductionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("deduction.not.found"));
    }

    // Find list of all Deductions:
    public List<Deduction> findAll() {
        return deductionRepository.findAll();
    }
}
