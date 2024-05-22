package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstorm.taxservice.exceptions.NotFoundException;
import com.skillstorm.taxservice.models.State;
import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.repositories.StateTaxRepository;

@ExtendWith(MockitoExtension.class)
class StateTaxServiceTest {

    @Mock
    private StateTaxRepository stateTaxRepository;

    @InjectMocks
    private StateTaxService stateTaxService;

    @Test
    void getTaxBracketsByStateName_Found_ReturnsTaxBrackets() {
        String stateName = "California";

        State state = new State();
        state.setId(1);

        StateTax stateTax1 = new StateTax(1, state, 0, BigDecimal.valueOf(0.05));
        StateTax stateTax2 = new StateTax(2, state, 50000, BigDecimal.valueOf(0.06));

        List<StateTax> stateTaxList = new ArrayList<>();
        stateTaxList.add(stateTax1);
        stateTaxList.add(stateTax2);

        when(stateTaxRepository.findByStateName(stateName)).thenReturn(stateTaxList);

        List<StateTax> result = stateTaxService.getTaxBracketsByStateName(stateName);

        assertEquals(2, result.size());
        assertTrue(result.contains(stateTax1));
        assertTrue(result.contains(stateTax2));
    }

    @Test
    void getTaxBracketsByStateName_NotFound_ThrowsNotFoundException() {
        String stateName = "California";

        when(stateTaxRepository.findByStateName(stateName)).thenReturn(new ArrayList<>());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            stateTaxService.getTaxBracketsByStateName(stateName);
        });

        assertEquals("State tax info not found for state name: " + stateName, exception.getMessage());
    }

    @Test
    void getTaxBracketsByStateCode_Found_ReturnsTaxBrackets() {
        String stateCode = "CA";

        State state = new State();
        state.setId(1);

        StateTax stateTax1 = new StateTax(1, state, 0, BigDecimal.valueOf(0.05));
        StateTax stateTax2 = new StateTax(2, state, 50000, BigDecimal.valueOf(0.06));

        List<StateTax> stateTaxList = new ArrayList<>();
        stateTaxList.add(stateTax1);
        stateTaxList.add(stateTax2);

        when(stateTaxRepository.findByStateCode(stateCode)).thenReturn(stateTaxList);

        List<StateTax> result = stateTaxService.getTaxBracketsByStateCode(stateCode);

        assertEquals(2, result.size());
        assertTrue(result.contains(stateTax1));
        assertTrue(result.contains(stateTax2));
    }

    @Test
    void getTaxBracketsByStateCode_NotFound_ThrowsNotFoundException() {
        String stateCode = "CA";

        when(stateTaxRepository.findByStateCode(stateCode)).thenReturn(new ArrayList<>());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            stateTaxService.getTaxBracketsByStateCode(stateCode);
        });

        assertEquals("State tax info not found for state code: " + stateCode, exception.getMessage());
    }

    @Test
    void getTaxBracketsByStateId_Found_ReturnsTaxBrackets() {
        int stateId = 1;

        State state = new State();
        state.setId(stateId);

        StateTax stateTax1 = new StateTax(1, state, 0, BigDecimal.valueOf(0.05));
        StateTax stateTax2 = new StateTax(2, state, 50000, BigDecimal.valueOf(0.06));

        List<StateTax> stateTaxList = new ArrayList<>();
        stateTaxList.add(stateTax1);
        stateTaxList.add(stateTax2);

        when(stateTaxRepository.findByStateId(stateId)).thenReturn(stateTaxList);

        List<StateTax> result = stateTaxService.getTaxBracketsByStateId(stateId);

        assertEquals(2, result.size());
        assertTrue(result.contains(stateTax1));
        assertTrue(result.contains(stateTax2));
    }

    @Test
    void getTaxBracketsByStateId_NotFound_ThrowsNotFoundException() {
        int stateId = 1;

        when(stateTaxRepository.findByStateId(stateId)).thenReturn(new ArrayList<>());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            stateTaxService.getTaxBracketsByStateId(stateId);
        });

        assertEquals("State tax info not found for state ID: " + stateId, exception.getMessage());
    }
}
