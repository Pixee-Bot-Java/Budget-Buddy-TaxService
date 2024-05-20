package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.dtos.TaxReturnDeductionDto;
import com.skillstorm.taxservice.repositories.TaxReturnDeductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaxReturnDeductionService {

    private final TaxReturnDeductionRepository taxReturnDeductionRepository;

    @Autowired
    public TaxReturnDeductionService(TaxReturnDeductionRepository taxReturnDeductionRepository) {
        this.taxReturnDeductionRepository = taxReturnDeductionRepository;
    }

    // Add new Deductions for a TaxReturn or update existing ones:
    @Transactional
    public List<TaxReturnDeductionDto> saveAndUpdateByTaxReturnId(int taxReturnId, List<TaxReturnDeductionDto> deductions) {

        // Remove all deductions previously associated with the TaxReturn:
        taxReturnDeductionRepository.deleteAllByTaxReturnId(taxReturnId);

        // Replace them with the new list:
        return taxReturnDeductionRepository.saveAllAndFlush(deductions.stream().map(TaxReturnDeductionDto::mapToEntity).toList())
                .stream().map(TaxReturnDeductionDto::new).toList();
    }
}
