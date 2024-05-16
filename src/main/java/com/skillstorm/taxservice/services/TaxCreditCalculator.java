package com.skillstorm.taxservice.services; //refactor package

public class TaxCreditCalculator {
    
//CHILD CREDIT
        public static double calculateChildTaxCredit(int numChildren, double agi, String filingStatus, double taxLiability, double earnedIncome) {
            double potentialCredit = numChildren * 2000;
            double agiLimit = (filingStatus.equalsIgnoreCase("joint")) ? 400000 : 200000;
            double agiExcess = Math.max(0, agi - agiLimit);
            double agiPhaseout = Math.min(potentialCredit, (agiExcess / 1000) * 50);

            double creditAfterPhaseout = Math.max(0, potentialCredit - agiPhaseout);
            double creditApplied = Math.min(taxLiability, creditAfterPhaseout);

            // Calculate potential refund
            double remainingCredit = creditAfterPhaseout - creditApplied;
            if (remainingCredit > 0 && earnedIncome > 2500) {
                double earnedIncomeAboveThreshold = Math.max(0, earnedIncome - 2500);
                double refundLimitPerChild = 1600;
                double potentialRefund = Math.min(remainingCredit, Math.min(0.15 * earnedIncomeAboveThreshold, refundLimitPerChild * numChildren));
                return creditApplied + potentialRefund;
            }

            return creditApplied;

            /*TEST CASES
             * run in seperate java project's main method *

                double result;

                // Test Case 1: Joint filers, AGI below limit, full credit
                result = calculateChildTaxCredit(3, 350000, "joint", 10000, 60000);
                System.out.println("Test Case 1: " + (result == 6000 ? "Passed" : "Failed") + " (Expected: 6000, Actual: " + result + ")");

                // Test Case 2: Single filer, AGI above limit, NO credit (complete phaseout) *THIS ONE FAILS*
                result = calculateChildTaxCredit(2, 250000, "single", 8000, 40000);
                System.out.println("Test Case 2: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 3: Single filer, no tax liability, refund (capped at $1600/child)
                result = calculateChildTaxCredit(1, 40000, "single", 0, 30000);
                System.out.println("Test Case 3: " + (result == 1600 ? "Passed" : "Failed") + " (Expected: 1600, Actual: " + result + ")");

                // Additional Test Cases:
                // Test Case 4: Joint filers, AGI in phaseout range, partial credit
                result = calculateChildTaxCredit(2, 420000, "joint", 5000, 50000);
                System.out.println("Test Case 4: " + (result == 3000 ? "Passed" : "Failed") + " (Expected: 3000, Actual: " + result + ")");

                // Test Case 5: Single filer, no refund due to low earned income
                result = calculateChildTaxCredit(1, 180000, "single", 2000, 2000); // Earned income below $2500
                System.out.println("Test Case 5: " + (result == 2000 ? "Passed" : "Failed") + " (Expected: 2000, Actual: " + result + ")");
         
             */
        }


 


}
