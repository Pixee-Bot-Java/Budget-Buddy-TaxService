package com.skillstorm.taxservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import com.skillstorm.taxservice.dtos.TaxReturnCreditDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.models.CapitalGainsTax;
import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;

public class TaxCalculatorServiceTest {

  @Mock
  private OtherIncomeService otherIncomeService;

  @Mock
  private StateTaxService stateTaxService;

  @Mock
  private CapitalGainsTaxService capitalGainsTaxService;

  @Mock
  private FilingStatusService filingStatusService;

  @Mock
  private com.skillstorm.taxservice.models.FilingStatus filingStatusModel;

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

    taxReturn.setTaxableIncome(taxableIncome);
    taxReturn.setFederalRefund(federalRefund);
    taxReturn.setFilingStatus(com.skillstorm.taxservice.constants.FilingStatus.SINGLE);

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

    @Test
    void testCalculateCapitalGainsTax() {
      // Setup
      taxReturn.setTaxableIncome(BigDecimal.valueOf(50000));
      taxReturn.setFederalRefund(BigDecimal.valueOf(2000));

      OtherIncomeDto otherIncome = new OtherIncomeDto();
      otherIncome.setLongTermCapitalGains(BigDecimal.valueOf(10000));

      taxReturn.setOtherIncome(otherIncome);
      taxReturn.setFilingStatus(FilingStatus.SINGLE);

      // Mock capital gains tax brackets
      List<CapitalGainsTax> capitalGainsTaxBrackets = new ArrayList<>();

      // Mock capital gains tax brackets for married filing status
      capitalGainsTaxBrackets.add(new CapitalGainsTax(1, new com.skillstorm.taxservice.models.FilingStatus(), BigDecimal.valueOf(0.1), 40000)); // Sample bracket 1
      capitalGainsTaxBrackets.add(new CapitalGainsTax(2, new com.skillstorm.taxservice.models.FilingStatus(), BigDecimal.valueOf(0.15), 0)); // Sample last bracket

      when(capitalGainsTaxService.findByFilingStatusID(taxReturn.getFilingStatus().getValue()))
              .thenReturn(capitalGainsTaxBrackets);
      
      // Calculate expected federal refund after capital gains tax
      BigDecimal expectedFederalRefund = taxReturn.getFederalRefund().subtract(BigDecimal.valueOf(1500)).setScale(2); // Sample expected federal refund after capital gains tax

      // Call the method
      TaxReturnDto result = taxCalculatorService.calculateCapitalGainsTax(taxReturn);

      
      // Assert the result
      assertEquals(expectedFederalRefund, result.getFederalRefund());
    }
  
    @Test
    void testCalculateChildTaxCredits() {

      // Set up test data
      taxReturn.setAdjustedGrossIncome(BigDecimal.valueOf(90000));
      taxReturn.setFederalRefund(BigDecimal.valueOf(2000));
      taxReturn.setTotalCredits(BigDecimal.ZERO);

      TaxReturnCreditDto taxReturnCredit = new TaxReturnCreditDto();
      taxReturnCredit.setNumDependents(2);
      taxReturn.setTaxCredit(taxReturnCredit);

      FilingStatus filingStatusEnum = FilingStatus.SINGLE;
      taxReturn.setFilingStatus(filingStatusEnum);

      com.skillstorm.taxservice.models.FilingStatus filingStatus = 
        mock(com.skillstorm.taxservice.models.FilingStatus.class);

      ChildTaxCredit childTaxCredit = new ChildTaxCredit();
      childTaxCredit.setPerQualifyingChild(2000);
      childTaxCredit.setIncomeThreshold(75000);
      childTaxCredit.setRefundLimit(1400);

      // Mocking FilingStatusService to return our sample filing status and child tax credit
      when(filingStatusService.findById(filingStatusEnum.getValue())).thenReturn(filingStatus);
      when(filingStatus.getChildTaxCredit()).thenReturn(childTaxCredit);

      // Call the method to test
      TaxReturnDto result = taxCalculatorService.calculateChildTaxCredits(taxReturn);

      // Calculate expected values
      BigDecimal potentialCreditAmount = BigDecimal.valueOf(2 * 2000);
      BigDecimal incomeThreshold = BigDecimal.valueOf(75000);
      BigDecimal agiExcess = BigDecimal.valueOf(90000).subtract(incomeThreshold).max(BigDecimal.ZERO);
      BigDecimal quotient = agiExcess.divide(new BigDecimal("1000"), RoundingMode.HALF_UP);
      BigDecimal phaseoutAmount = quotient.multiply(new BigDecimal("50"));
      BigDecimal creditAfterPhaseout = potentialCreditAmount.subtract(phaseoutAmount);

      BigDecimal creditLimit = BigDecimal.valueOf(childTaxCredit.getRefundLimit());
      BigDecimal difference = BigDecimal.valueOf(childTaxCredit.getPerQualifyingChild()).subtract(creditLimit);
      creditAfterPhaseout = creditAfterPhaseout.subtract(difference.multiply(BigDecimal.valueOf(2)));

      BigDecimal expectedFederalRefund = BigDecimal.valueOf(2000).add(creditAfterPhaseout);
      BigDecimal expectedTotalCredits = creditAfterPhaseout;

      // Assert the result
      assertEquals(expectedFederalRefund.setScale(2, RoundingMode.HALF_UP), result.getFederalRefund().setScale(2, RoundingMode.HALF_UP));
      assertEquals(expectedTotalCredits.setScale(2, RoundingMode.HALF_UP), result.getTotalCredits().setScale(2, RoundingMode.HALF_UP));
    }
}
