package com.skillstorm.taxservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.repositories.FilingStatusRepository;

@Service
public class FilingStatusService {
  
  @Autowired
  private FilingStatusRepository filingStatusRepository;

  // Get filing status by the status string
  public FilingStatus getByStatus(String status) {
    Optional<FilingStatus> filingStatus = filingStatusRepository.findByStatus(status);

    if (filingStatus.isPresent()) {
      return filingStatus.get();
    } else {
      throw new NotFoundException("Filing status: " + status + " not found");
    }
  }
}
