package com.skillstorm.taxservice.services;

import org.springframework.stereotype.Service;

import com.skillstorm.taxservice.dtos.OtherIncomeDto;
import com.skillstorm.taxservice.dtos.TaxReturnCreditDto;
import com.skillstorm.taxservice.dtos.TaxReturnDto;
import com.skillstorm.taxservice.dtos.W2Dto;
import com.skillstorm.taxservice.models.CapitalGainsTax;
import com.skillstorm.taxservice.models.FilingStatus;
import com.skillstorm.taxservice.models.StateTax;
import com.skillstorm.taxservice.models.TaxBracket;
import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.DependentCareTaxCreditLimit;
import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;
import com.skillstorm.taxservice.models.taxcredits.SaversTaxCredit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TaxCalculatorService {

    private final TaxCreditService taxCreditService;
    private final FilingStatusService filingStatusService;
    private final OtherIncomeService otherIncomeService;
    private final TaxBracketService taxBracketService;
    private final StateTaxService stateTaxService;
    private final CapitalGainsTaxService capitalGainsTaxService;

    public TaxCalculatorService(TaxCreditService taxCreditService,
                                FilingStatusService filingStatusService,
                                OtherIncomeService otherIncomeService,
                                TaxBracketService taxBracketService,
                                StateTaxService stateTaxService,
                                CapitalGainsTaxService capitalGainsTaxService) {
      this.taxCreditService = taxCreditService;
      this.filingStatusService = filingStatusService;
      this.otherIncomeService = otherIncomeService;
      this.taxBracketService = taxBracketService;
      this.stateTaxService = stateTaxService;
      this.capitalGainsTaxService = capitalGainsTaxService;
    }

    public TaxReturnDto calculateTotalIncome(TaxReturnDto taxReturn) throws IllegalAccessException {
      List<W2Dto> w2s = taxReturn.getW2s();
      BigDecimal w2Income =  w2s.stream().map(W2Dto::getWages)
                              .reduce(BigDecimal.ZERO, BigDecimal::add)
                              .setScale(2, RoundingMode.HALF_UP);
      
      OtherIncomeDto otherIncome = otherIncomeService.findByTaxReturnId(taxReturn.getId());
      BigDecimal totalOtherIncome = otherIncomeService.sumOtherIncome(otherIncome);

      BigDecimal totalIncome = w2Income.add(totalOtherIncome);

      taxReturn.setTotalIncome(totalIncome);

      return taxReturn;
    }


    // Placeholder for now
    public TaxReturnDto calculateAgi(TaxReturnDto taxReturn) throws IllegalAccessException {
      return calculateTotalIncome(taxReturn);
    }


    // Placeholder for now
    public TaxReturnDto calculateTaxableIncome(TaxReturnDto taxReturn) throws IllegalAccessException {
      return calculateTotalIncome(taxReturn);
    }


    // Calculate total federal tax liability
    public TaxReturnDto calculateFederalTaxes(TaxReturnDto taxReturn) {

      // If taxable income is 0, simply return
      if (taxReturn.getTaxableIncome().compareTo(BigDecimal.ZERO) <= 0) {
        return taxReturn;
      }

      // Get taxable income
      BigDecimal taxableIncome = taxReturn.getTaxableIncome();

      // Get tax brackets based on user's filing status
      List<TaxBracket> taxBrackets = taxBracketService.findByFilingStatusID(taxReturn.getFilingStatus().getValue());

      // Initialize tax amount & remaining income
      BigDecimal totalTaxes = BigDecimal.ZERO;
      BigDecimal remainingIncome = taxableIncome;

      // Calculate the tax amount at each bracket user's income falls into
      for (TaxBracket bracket : taxBrackets) {
          double minIncome = bracket.getMinIncome();
          double maxIncome = bracket.getMaxIncome();
          BigDecimal taxRate = bracket.getRate();

          if (remainingIncome.compareTo(BigDecimal.ZERO) <= 0) {
              break; // No more income to tax
          }

          BigDecimal taxableAmountInBracket = remainingIncome.min(BigDecimal.valueOf(maxIncome - minIncome));
          BigDecimal taxInBracket = taxableAmountInBracket.multiply(taxRate);
          totalTaxes = totalTaxes.add(taxInBracket);
          remainingIncome = remainingIncome.subtract(taxableAmountInBracket);
      }

      // Set the federal tax amount in the TaxReturnDto
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().subtract(totalTaxes));

      // Return the total federal tax liability
      return taxReturn;
    }


    // Calculate total state tax liability
    public TaxReturnDto calculateStateTaxes(TaxReturnDto taxReturn) {
      // Get tax return's W2s
      List<W2Dto> w2s = taxReturn.getW2s();
  
      // Initialize tax amount and withheld amounts
      BigDecimal totalTaxAmount = BigDecimal.ZERO;
      BigDecimal totalFedTaxWithheld = BigDecimal.ZERO;
      BigDecimal totalStateTaxWithheld = BigDecimal.ZERO;
      BigDecimal totalSocialSecurityTaxWithheld = BigDecimal.ZERO;
      BigDecimal totalMedicareTaxWithheld = BigDecimal.ZERO;
  
      // Calculate taxes for each W2
      for (W2Dto w2 : w2s) {
          totalFedTaxWithheld = totalFedTaxWithheld.add(w2.getFederalIncomeTaxWithheld());
          totalStateTaxWithheld = totalStateTaxWithheld.add(w2.getStateIncomeTaxWithheld());
          totalSocialSecurityTaxWithheld = totalSocialSecurityTaxWithheld.add(w2.getSocialSecurityTaxWithheld());
          totalMedicareTaxWithheld = totalMedicareTaxWithheld.add(w2.getMedicareTaxWithheld());
  
          totalTaxAmount = totalTaxAmount.add(calculateTaxForIncome(w2.getWages(), w2.getState().getValue()));
      }
  
      // Calculate taxes for other income
      if (taxReturn.getOtherIncome() != null) {
          BigDecimal otherIncome = taxReturn.getOtherIncome().getOtherInvestmentIncome()
              .add(taxReturn.getOtherIncome().getNetBusinessIncome())
              .add(taxReturn.getOtherIncome().getAdditionalIncome())
              .add(taxReturn.getOtherIncome().getShortTermCapitalGains());
  
          totalTaxAmount = totalTaxAmount.add(calculateTaxForIncome(otherIncome, taxReturn.getState().getValue()));
      }
  
      // Set results in the relevant DTO fields
      taxReturn.setStateRefund(taxReturn.getStateRefund().subtract(totalTaxAmount));
      taxReturn.setFedTaxWithheld(totalFedTaxWithheld);
      taxReturn.setStateTaxWithheld(totalStateTaxWithheld);
      taxReturn.setSocialSecurityTaxWithheld(totalSocialSecurityTaxWithheld);
      taxReturn.setMedicareTaxWithheld(totalMedicareTaxWithheld);
  
      // Return updated tax return
      return taxReturn;
    }
  
    private BigDecimal calculateTaxForIncome(BigDecimal income, int stateId) {
      BigDecimal totalTaxAmount = BigDecimal.ZERO;
      List<StateTax> taxBrackets = stateTaxService.getTaxBracketsByStateId(stateId);
  
      BigDecimal remainingIncome = income;
  
      for (StateTax bracket : taxBrackets) {
          BigDecimal bracketIncome = remainingIncome.min(BigDecimal.valueOf(bracket.getIncomeRange()));
  
          if (bracketIncome.compareTo(BigDecimal.ZERO) == 0) {
              bracketIncome = remainingIncome;
          }
  
          totalTaxAmount = totalTaxAmount.add(bracketIncome.multiply(bracket.getRate()));
          remainingIncome = remainingIncome.subtract(bracketIncome);
  
          if (remainingIncome.compareTo(BigDecimal.ZERO) <= 0) {
              break;
          }
      }
  
      return totalTaxAmount;
    }


    public TaxReturnDto calculateCapitalGainsTax(TaxReturnDto taxReturn) {

      // Get total taxable income
      BigDecimal taxableIncome = taxReturn.getTaxableIncome();

      // If user hasn't input any other income, simply return
      if (taxReturn.getOtherIncome() == null) {
        return taxReturn;
      }

      // Get capital gains amount
      BigDecimal longTermCapitalGains = taxReturn.getOtherIncome().getLongTermCapitalGains();

      // Initialize tax amounts
      BigDecimal longTermCapitalGainsTaxAmount = BigDecimal.valueOf(0);

      // Get long term capital gains federal tax rates based on user's filing status
      List<CapitalGainsTax> longTermCapitalGainsTaxBrackets = capitalGainsTaxService.findByFilingStatusID(taxReturn.getFilingStatus().getValue());

      // Initialize remaining income
      BigDecimal ordinaryIncome = taxableIncome.subtract(longTermCapitalGains);
      BigDecimal remainingCapGains = longTermCapitalGains;

      // Calculate long term capital gains tax
      for (CapitalGainsTax bracket : longTermCapitalGainsTaxBrackets) {

        // If at the last bracket (income range is 0) tax remaining gains at that rate
        if (bracket.getIncomeRange() == 0) {
          longTermCapitalGainsTaxAmount = longTermCapitalGains.add(remainingCapGains.multiply(bracket.getRate()));
          break;
        }

        // Start at tax bracket that ordinary income falls into
        if (ordinaryIncome.compareTo(BigDecimal.valueOf((double)bracket.getIncomeRange())) >= 0) {
          ordinaryIncome = BigDecimal.valueOf(Math.max(0, ordinaryIncome.doubleValue() - bracket.getIncomeRange()));
          continue;
        }

        // Any leftover ordinary income is subtracted from the income range
        double bracketRange = (double) bracket.getIncomeRange() - ordinaryIncome.doubleValue();
        ordinaryIncome = BigDecimal.valueOf(0);

        // Calculate the taxable amount of the gains
        double taxableAmount = Math.min(bracketRange, remainingCapGains.doubleValue());

        // Calculate the tax amount
        longTermCapitalGainsTaxAmount = longTermCapitalGainsTaxAmount.add(BigDecimal.valueOf(taxableAmount).multiply(bracket.getRate()));

        // Subtract taxable amount to see if next bracket should apply
        remainingCapGains = remainingCapGains.subtract(BigDecimal.valueOf(taxableAmount));

        // If there are no more gains to tax, break here
        if (remainingCapGains.doubleValue() <= 0) break;
      }

      // Add tax amount to federal refund of tax return
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().subtract(longTermCapitalGainsTaxAmount));

      return taxReturn;
    }


    public TaxReturnDto calculateNonRefundableTaxCredits(TaxReturnDto taxReturn) {
      // If user hasn't input any tax credit info, simply return
      if (taxReturn.getTaxCredit() == null) {
        return taxReturn;
      }

      calculateEducationTaxCreditLlc(taxReturn);
      calculateSaversTaxCredit(taxReturn);
      calculateDependentCareTaxCredit(taxReturn);
      return taxReturn;
    }


    public TaxReturnDto calculateRefundableTaxCredits(TaxReturnDto taxReturn) {
      // If user hasn't input any tax credit info, simply return
      if (taxReturn.getTaxCredit() == null) {
        return taxReturn;
      }

      calculateChildTaxCredits(taxReturn);
      calculateEducationTaxCreditAotc(taxReturn);
      calculateEarnedIncomeTaxCredit(taxReturn);
      return taxReturn;
    }


    public TaxReturnDto calculateChildTaxCredits(TaxReturnDto taxReturn) {
      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());
      ChildTaxCredit childTaxCredit = filingStatus.getChildTaxCredit();

      // Get relevant fields
      int numDependents = taxReturnCredit.getNumDependents();
      BigDecimal agi = taxReturn.getAdjustedGrossIncome();

      BigDecimal potentialCreditAmount = BigDecimal.valueOf(numDependents * childTaxCredit.getPerQualifyingChild());

      // Convert int income threshold to BigDecimal
      BigDecimal incomeThreshold = BigDecimal.valueOf(childTaxCredit.getIncomeThreshold());

      // Calculate agiExcess using BigDecimal methods
      BigDecimal agiExcess = agi.subtract(incomeThreshold).max(BigDecimal.ZERO);

      // Divide agiExcess by 1000
      BigDecimal divisor = new BigDecimal("1000");
      BigDecimal quotient = agiExcess.divide(divisor, RoundingMode.HALF_UP);

      // Multiply the result by 50
      BigDecimal multiplier = new BigDecimal("50");
      BigDecimal phaseoutAmount = quotient.multiply(multiplier);

      // Get final potential credit amount
      BigDecimal creditAfterPhaseout = potentialCreditAmount.subtract(phaseoutAmount);

      // Get current federal tax liability
      BigDecimal taxRefund = taxReturn.getFederalRefund();

      if (taxRefund.compareTo(creditAfterPhaseout) >= 0) {
        taxRefund = taxRefund.add(creditAfterPhaseout);
      } else {
        BigDecimal creditLimit = BigDecimal.valueOf(childTaxCredit.getRefundLimit());
        BigDecimal difference = BigDecimal.valueOf(childTaxCredit.getPerQualifyingChild()).subtract(creditLimit);
        creditAfterPhaseout = creditAfterPhaseout.subtract(difference.multiply(BigDecimal.valueOf(numDependents)));
        taxRefund = taxRefund.add(creditAfterPhaseout);
      }

      taxReturn.setFederalRefund(taxRefund);
      
      return taxReturn;
    }


    public TaxReturnDto calculateEarnedIncomeTaxCredit(TaxReturnDto taxReturn) {

      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());
      EarnedIncomeTaxCredit earnedIncomeTaxCredit = filingStatus.getEarnedIncomeTaxCredit();

      // Get agi value
      BigDecimal agi = taxReturn.getAdjustedGrossIncome();

      // If user's investment income is greater than the investment income limit: not eligible for this credit; return
      if (taxReturn.getOtherIncome().getOtherInvestmentIncome().doubleValue() >= earnedIncomeTaxCredit.getInvestmentIncomeLimit()) {
        return taxReturn;
      }

      // Initialize variables
      int numDependents = taxReturnCredit.getNumDependents();
      int agiThreshold;
      int creditAmount;

      // Determine agi threshold and credit amount based on # of dependents
      switch(numDependents) {
        case 0:
          agiThreshold = earnedIncomeTaxCredit.getAgiThreshold0Children();
          creditAmount = earnedIncomeTaxCredit.getAmount0Children();
          break;
        case 1:
          agiThreshold = earnedIncomeTaxCredit.getAgiThreshold1Children();
          creditAmount = earnedIncomeTaxCredit.getAmount1Children();
          break;
        case 2:
          agiThreshold = earnedIncomeTaxCredit.getAgiThreshold2Children();
          creditAmount = earnedIncomeTaxCredit.getAmount2Children();
          break;
        default:
          agiThreshold = earnedIncomeTaxCredit.getAgiThreshold3Children();
          creditAmount = earnedIncomeTaxCredit.getAmount3Children();
      }

      // if agi is greater than agi threshold: not eligible, return
      if (agi.compareTo(BigDecimal.valueOf(agiThreshold)) > 0) return taxReturn;

      // If tax credit is applicable, add credit to federal refund
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().add(BigDecimal.valueOf(creditAmount)));

      return taxReturn;
    }
    

    // refundable
    public TaxReturnDto calculateEducationTaxCreditAotc(TaxReturnDto taxReturn) {
      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());
      EducationTaxCreditAotc educationTaxCreditAotc = filingStatus.getEducationTaxCreditAotc();

      // Get user's agi
      BigDecimal agi = taxReturn.getAdjustedGrossIncome();

      // Rate at which credit amount is reduced by; default to 1.00
      BigDecimal rate = BigDecimal.valueOf(1);

      // Determine the rate at which credit is reduced by based on user's agi
      if (agi.compareTo(BigDecimal.valueOf(educationTaxCreditAotc.getFullCreditIncomeThreshold())) > 0) {
        agi = agi.subtract(BigDecimal.valueOf(educationTaxCreditAotc.getFullCreditIncomeThreshold()));
        if (agi.compareTo(BigDecimal.valueOf(educationTaxCreditAotc.getPartialCreditIncomeThreshold())) > 0) {
          return taxReturn;
        } else {
          rate = educationTaxCreditAotc.getIncomePartialCreditRate();
        }
      }

      // if married filing separately: not eligible; return
      if (filingStatus.getStatus().equals("Married filing separately")) return taxReturn;

      // Initialize credit amount
      BigDecimal creditAmount = BigDecimal.valueOf(0);

      // Get relevant variables
      int numDependents = taxReturnCredit.getNumDependentsAotc();
      BigDecimal educationExpenses = taxReturnCredit.getEducationExpenses();

      // Calculate education expenses per student
      BigDecimal expensesPerDependent = educationExpenses.divide(BigDecimal.valueOf(numDependents), 2, RoundingMode.HALF_UP);

      // If expenses per student exceeds the threshold where 100% of expenses is applied as credit, reduce the expenses
      if (expensesPerDependent.compareTo(BigDecimal.valueOf(educationTaxCreditAotc.getExpensesThresholdFullCredit())) > 0) {
        // Calculate remaining expenses to reduce as credit
        BigDecimal remainingExpenses = expensesPerDependent.subtract(BigDecimal.valueOf(educationTaxCreditAotc.getExpensesThresholdFullCredit()));

        // Reduce expenses by partial credit rate
        BigDecimal reducedExpenses = remainingExpenses.multiply(educationTaxCreditAotc.getExpensesPartialCreditRate());

        expensesPerDependent = BigDecimal.valueOf(educationTaxCreditAotc.getExpensesThresholdFullCredit()).add(reducedExpenses);
      }

      // Calculate credit amount per dependent
      BigDecimal creditAmountPerDependent = expensesPerDependent.min(BigDecimal.valueOf(educationTaxCreditAotc.getMaxCreditAmountPerStudent()));

      // Calculate total credit amount based on number of dependents
      creditAmount = creditAmountPerDependent.multiply(BigDecimal.valueOf(numDependents));

      // Calculate total credit amount based on income and its associated partial credit rate
      creditAmount = creditAmount.multiply(rate);

      // Add total credit amount to tax return
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().add(creditAmount));

      return taxReturn;
    }

    // non-refundable
    public TaxReturnDto calculateEducationTaxCreditLlc(TaxReturnDto taxReturn) {
      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();

      // if user is not eligible, return
      if (!taxReturnCredit.isClaimLlcCredit()) {
        return taxReturn;
      }

      // Get tax credit static data based on filing status
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());
      EducationTaxCreditLlc educationTaxCreditLlc = filingStatus.getEducationTaxCreditLlc();

      // Get user's AGI
      BigDecimal agi = taxReturn.getAdjustedGrossIncome();

      // Rate at which credit amount is reduced by; default to 1.00
      BigDecimal rate = BigDecimal.valueOf(1);

      // Determine the rate at which credit is reduced by based on user's agi
      if (agi.compareTo(BigDecimal.valueOf(educationTaxCreditLlc.getFullCreditIncomeThreshold())) > 0) {
        agi = agi.subtract(BigDecimal.valueOf(educationTaxCreditLlc.getFullCreditIncomeThreshold()));
        if (agi.compareTo(BigDecimal.valueOf(educationTaxCreditLlc.getPartialCreditIncomeThreshold())) > 0) {
          return taxReturn;
        } else {
          rate = educationTaxCreditLlc.getIncomePartialCreditRate();
        }
      }

      // Get education expenses
      BigDecimal educationExpenses = taxReturnCredit.getLlcEducationExpenses();
      
      // Bound expenses to the maximum allowed consideration for tax credit
      educationExpenses = educationExpenses.min(BigDecimal.valueOf(educationTaxCreditLlc.getExpensesThreshold()));

      // Calculate the credit amount based on the credit rate
      BigDecimal creditAmount = educationExpenses.multiply(educationTaxCreditLlc.getCreditRate());

      // Calculate the final credit amount based on the limiting rate of agi
      creditAmount = creditAmount.multiply(rate);

      // Add total credit amount to tax return
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().add(creditAmount).min(BigDecimal.ZERO));

      return taxReturn;
    }

    // non-refundable
    public TaxReturnDto calculateSaversTaxCredit(TaxReturnDto taxReturn) {
      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();

      // if user can be claimed as a dependent on another's tax return: not eligible; return
      if (taxReturnCredit.isClaimedAsDependent()) {
        return taxReturn;
      }

      // Get tax credit static data based on user's filing status
      FilingStatus filingStatus = filingStatusService.findById(taxReturn.getFilingStatus().getValue());
      SaversTaxCredit saversTaxCredit = filingStatus.getSaversTaxCredit();

      // Get user's AGI
      BigDecimal agi = taxReturn.getAdjustedGrossIncome();

      BigDecimal agiFirstThreshold = BigDecimal.valueOf(saversTaxCredit.getAgiThresholdFirstContributionLimit());
      BigDecimal agiSecondThreshold = BigDecimal.valueOf(saversTaxCredit.getAgiThresholdSecondContributionLimit());
      BigDecimal agiThirdThreshold = BigDecimal.valueOf(saversTaxCredit.getAgiThresholdThirdContributionLimit());

      // Determine rate at which IRA contributions are applied as credit
      BigDecimal rate = saversTaxCredit.getFirstContributionRate();
      if (agi.compareTo(agiFirstThreshold) > 0) {
        BigDecimal remainingAgi = agi.subtract(agiFirstThreshold);
        if (remainingAgi.compareTo(agiSecondThreshold) > 0) {
          remainingAgi = remainingAgi.subtract(agiSecondThreshold);
          // If agi exceeds this threshold, 0% of IRA contributions are applied as credit; simply return
          if (remainingAgi.compareTo(agiThirdThreshold) > 0) {
            return taxReturn;
          } else {
            rate = saversTaxCredit.getThirdontributionRate();
          }
        } else {
          rate = saversTaxCredit.getSecondContributionRate();
        }
      }

      // Get user's IRA contribution amount
      BigDecimal iraContributions = taxReturnCredit.getIraContributions();

      // Restrict amount of contributions that are considered for tax credit
      iraContributions = iraContributions.min(BigDecimal.valueOf(saversTaxCredit.getMaxContributionAmount()));

      // Calculate credit amount based on rate
      BigDecimal creditAmount = iraContributions.multiply(rate);

      // Add total credit amount to tax return
      taxReturn.setFederalRefund(taxReturn.getFederalRefund().add(creditAmount).min(BigDecimal.ZERO));

      return taxReturn;
    }


    // non-refundable
    public TaxReturnDto calculateDependentCareTaxCredit(TaxReturnDto taxReturn) {
      TaxReturnCreditDto taxReturnCredit = taxReturn.getTaxCredit();

      // Get tax credit static data
      List<DependentCareTaxCredit> dependentCareTaxCredit = taxCreditService.getDependentCareTaxCreditBrackets();

      // Get user's number of qualifying children
      int numChildren = taxReturnCredit.getNumChildren();

      DependentCareTaxCreditLimit creditLimit = taxCreditService.getDependentCareTaxCreditLimitByNumDependents(numChildren);

      // Get user's AGI
      double remainingAgi = taxReturn.getAdjustedGrossIncome().doubleValue();

      // Initialize rate at which child care expenses is applied as credit
      BigDecimal rate = dependentCareTaxCredit.get(0).getRate();

      // Based on AGI, determine which credit bracket is applied
      for (DependentCareTaxCredit credit : dependentCareTaxCredit) {
        if (remainingAgi > credit.getIncomeRange()) {
          remainingAgi -= credit.getIncomeRange();
          rate = credit.getRate();
        } else {
          rate = credit.getRate();
          break;
        }
      }

      // Get child care expenses
      BigDecimal childCareExpenses = taxReturnCredit.getChildCareExpenses();

      // Calculate credit amount based on rate
      BigDecimal creditAmount = childCareExpenses.multiply(rate);

      // Restrict creditAmount based on credit limit
      creditAmount = creditAmount.max(BigDecimal.valueOf(creditLimit.getCreditLimit()));

      // Non-refundable credit, so add credit with a max liability of 0
      BigDecimal taxRefund = taxReturn.getFederalRefund();
      taxRefund = taxRefund.add(creditAmount).min(BigDecimal.ZERO);

      // Set refund back into tax return
      taxReturn.setFederalRefund(taxRefund);

      return taxReturn;
    }
}


