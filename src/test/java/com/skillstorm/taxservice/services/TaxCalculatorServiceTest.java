package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.taxservice.constants.FilingStatus;
import com.skillstorm.taxservice.constants.State;
import com.skillstorm.taxservice.dtos.OtherIncomeDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.models.TaxBracket;

public class TaxCalculatorServiceTest {

  @Mock
  private OtherIncomeService otherIncomeService;

  @Mock
  private StateTaxService stateTaxService;

  @Mock
  private TaxBracketService taxBracketService;

  @InjectMocks
  private TaxCalculatorService taxCalculatorService;

  private TaxReturnDto taxReturn;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    taxReturn = new TaxReturnDto();
    taxReturn.setId(1);
  }

  @Test
  public void testCalculateTotalIncome() throws IllegalAccessException {
    // Arrange
    W2Dto w2Dto1 = new W2Dto();
    w2Dto1.setWages(new BigDecimal("30000.00"));
    
    W2Dto w2Dto2 = new W2Dto();
    w2Dto2.setWages(new BigDecimal("20000.00"));
    
    taxReturn.setW2s(Arrays.asList(w2Dto1, w2Dto2));
    
    OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
    BigDecimal totalOtherIncome = new BigDecimal("10000.00");
    
    when(otherIncomeService.findByTaxReturnId(1)).thenReturn(otherIncomeDto);
    when(otherIncomeService.sumOtherIncome(otherIncomeDto)).thenReturn(totalOtherIncome);
    
    // Act
    TaxReturnDto result = taxCalculatorService.calculateTotalIncome(taxReturn);
    
    // Assert
    BigDecimal expectedTotalIncome = new BigDecimal("60000.00").setScale(2, RoundingMode.HALF_UP);
    assertEquals(expectedTotalIncome, result.getTotalIncome());
    
    verify(otherIncomeService, times(1)).findByTaxReturnId(1);
    verify(otherIncomeService, times(1)).sumOtherIncome(otherIncomeDto);
  }

  @Test
  public void testCalculateFederalTaxes() {
    // Arrange
    BigDecimal taxableIncome = new BigDecimal("50000.00");
    BigDecimal federalRefund = new BigDecimal("1000.00");

    FilingStatus filingStatus = FilingStatus.SINGLE;

    taxReturn.setTaxableIncome(taxableIncome);
    taxReturn.setFederalRefund(federalRefund);
    taxReturn.setFilingStatus(filingStatus);

    TaxBracket bracket1 = new TaxBracket();
    bracket1.setMinIncome(0);
    bracket1.setMaxIncome(20000);
    bracket1.setRate(new BigDecimal("0.10"));

    TaxBracket bracket2 = new TaxBracket();
    bracket2.setMinIncome(20000);
    bracket2.setMaxIncome(50000);
    bracket2.setRate(new BigDecimal("0.20"));

    List<TaxBracket> taxBrackets = Arrays.asList(bracket1, bracket2);

    when(taxBracketService.findByFilingStatusID(1)).thenReturn(taxBrackets);

    // Act
    TaxReturnDto result = taxCalculatorService.calculateFederalTaxes(taxReturn);

    // Assert
    BigDecimal expectedTotalTaxes = new BigDecimal("8000.0000");
    BigDecimal expectedFederalRefund = federalRefund.subtract(expectedTotalTaxes);

    assertEquals(expectedFederalRefund, result.getFederalRefund());
    
    verify(taxBracketService, times(1)).findByFilingStatusID(1);
  }

  @Test
    public void testCalculateStateTaxes() {
        // Arrange
        W2Dto w2Dto1 = new W2Dto();
        w2Dto1.setWages(new BigDecimal("30000.00"));
        w2Dto1.setFederalIncomeTaxWithheld(new BigDecimal("3000.00"));
        w2Dto1.setStateIncomeTaxWithheld(new BigDecimal("1500.00"));
        w2Dto1.setSocialSecurityTaxWithheld(new BigDecimal("1860.00"));
        w2Dto1.setMedicareTaxWithheld(new BigDecimal("435.00"));
        w2Dto1.setState(State.AL);

        W2Dto w2Dto2 = new W2Dto();
        w2Dto2.setWages(new BigDecimal("20000.00"));
        w2Dto2.setFederalIncomeTaxWithheld(new BigDecimal("2000.00"));
        w2Dto2.setStateIncomeTaxWithheld(new BigDecimal("1000.00"));
        w2Dto2.setSocialSecurityTaxWithheld(new BigDecimal("1240.00"));
        w2Dto2.setMedicareTaxWithheld(new BigDecimal("290.00"));
        w2Dto2.setState(State.AL);

        taxReturn.setW2s(Arrays.asList(w2Dto1, w2Dto2));
        taxReturn.setStateRefund(new BigDecimal("5000.00"));

        OtherIncomeDto otherIncomeDto = new OtherIncomeDto();
        otherIncomeDto.setOtherInvestmentIncome(new BigDecimal("5000.00"));
        otherIncomeDto.setNetBusinessIncome(new BigDecimal("10000.00"));
        otherIncomeDto.setAdditionalIncome(new BigDecimal("3000.00"));
        otherIncomeDto.setShortTermCapitalGains(new BigDecimal("2000.00"));

        taxReturn.setOtherIncome(otherIncomeDto);
        taxReturn.setState(State.AL);

        // Mocking the tax brackets
        StateTax bracket1 = new StateTax();
        bracket1.setIncomeRange(20000);
        bracket1.setRate(new BigDecimal("0.05"));

        StateTax bracket2 = new StateTax();
        bracket2.setIncomeRange(30000); // Difference between 50000 and 20000
        bracket2.setRate(new BigDecimal("0.07"));

        StateTax bracket3 = new StateTax();
        bracket3.setIncomeRange(0); // No max income limit for this bracket
        bracket3.setRate(new BigDecimal("0.09"));

        List<StateTax> stateTaxBrackets = Arrays.asList(bracket1, bracket2, bracket3);

        when(stateTaxService.getTaxBracketsByStateId(1)).thenReturn(stateTaxBrackets);

        // Act
        TaxReturnDto result = taxCalculatorService.calculateStateTaxes(taxReturn);

        // Assert
        BigDecimal totalWages = new BigDecimal("50000.00");
        BigDecimal otherIncome = new BigDecimal("20000.00"); // Sum of other income
        BigDecimal totalIncome = totalWages.add(otherIncome);

        // Calculate expected state tax
        BigDecimal expectedStateTax = new BigDecimal("20000.00").multiply(new BigDecimal("0.05")) // First 20000 at 5%
                                     .add(new BigDecimal("30000.00").multiply(new BigDecimal("0.07"))) // Next 30000 at 7%
                                     .add(totalIncome.subtract(totalWages).multiply(new BigDecimal("0.09"))); // Remaining income at 9%

        BigDecimal expectedStateRefund = new BigDecimal("5000.00").subtract(expectedStateTax);

        assertEquals(expectedStateRefund.setScale(2, RoundingMode.HALF_UP), result.getStateRefund());
        assertEquals(new BigDecimal("5000.00").setScale(2, RoundingMode.HALF_UP), result.getFedTaxWithheld());
        assertEquals(new BigDecimal("2500.00").setScale(2, RoundingMode.HALF_UP), result.getStateTaxWithheld());
        assertEquals(new BigDecimal("3100.00").setScale(2, RoundingMode.HALF_UP), result.getSocialSecurityTaxWithheld());
        assertEquals(new BigDecimal("725.00").setScale(2, RoundingMode.HALF_UP), result.getMedicareTaxWithheld());

        verify(stateTaxService, times(1)).getTaxBracketsByStateId(1);
    }
  
}
