package com.skillstorm.taxservice.services;

import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.repositories.TaxReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:SystemMessages.properties")
public class TaxReturnService {

    private final TaxReturnRepository taxReturnRepository;
    private final Environment environment;

    @Autowired
    public TaxReturnService(TaxReturnRepository taxReturnRepository, Environment environment) {
        this.taxReturnRepository = taxReturnRepository;
        this.environment = environment;
    }

    // Add new TaxReturn:
    public TaxReturnDto addTaxReturn(TaxReturnDto newTaxReturn) {
        return new TaxReturnDto(taxReturnRepository.saveAndFlush(newTaxReturn.mapToEntity()));
    }

    // Get TaxReturn by id:
    public TaxReturnDto findById(int id) {
        return new TaxReturnDto(taxReturnRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(environment.getProperty("taxreturn.not.found") + " " + id)));
    }

    // Find all TaxReturns by userId:
    public List<TaxReturnDto> findAllByUserId(int userId) {
        return taxReturnRepository.findAllByUserId(userId)
                .stream().map(TaxReturnDto::new).toList();
    }

    public List<TaxReturnDto> findAllByUserIdAndYear(int userId, int year) {
        return taxReturnRepository.findAllByUserIdAndYear(userId, year)
                .stream().map(TaxReturnDto::new).toList();
    }

    // Update TaxReturn:
    public TaxReturnDto updateTaxReturn(int id, TaxReturnDto updatedTaxReturn) {
        // Verify that the TaxReturn exists:
        findById(id);
        updatedTaxReturn.setId(id);
        return new TaxReturnDto(taxReturnRepository.saveAndFlush(updatedTaxReturn.mapToEntity()));
    }

    // Delete TaxReturn:
    public void deleteTaxReturn(int id) {
        // Verify that the TaxReturn exists:
        findById(id);
        taxReturnRepository.deleteById(id);
    }
}
