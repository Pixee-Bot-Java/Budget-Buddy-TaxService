package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.repositories.FilingStatusRepository;
import com.skillstorm.taxservice.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class FilingStatusServiceTest {

    @Mock
    private FilingStatusRepository filingStatusRepository;

    @InjectMocks
    private FilingStatusService filingStatusService;

    @Test
    void findByStatus_ReturnsFilingStatus() {
        String status = "Single";
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setStatus(status);
        when(filingStatusRepository.findByStatus(status)).thenReturn(Optional.of(filingStatus));

        FilingStatus result = filingStatusService.findByStatus(status);

        assertNotNull(result);
        assertEquals(filingStatus, result);
    }

    @Test
    void findByStatus_NoDataFound_ThrowsNotFoundException() {
        String status = "Unknown";
        when(filingStatusRepository.findByStatus(status)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            filingStatusService.findByStatus(status);
        });

        assertEquals("Filing status: " + status + " not found", thrown.getMessage());
    }

    @Test
    void findById_ReturnsFilingStatus() {
        int id = 1;

        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(id);
        filingStatus.setStatus("Single");

        when(filingStatusRepository.findById(id)).thenReturn(Optional.of(filingStatus));

        FilingStatus result = filingStatusService.findById(id);

        assertNotNull(result);
        assertEquals(filingStatus, result);
    }

    @Test
    void findById_NoDataFound_ThrowsNotFoundException() {
        int id = 2;
        when(filingStatusRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            filingStatusService.findById(id);
        });

        assertEquals("No filing status found with ID: " + id, thrown.getMessage());
    }
}
