package com.skillstorm.taxdemo.services; //refactor package

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


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */
        
//EARNED INCOME CREDIT
        public static double calculateEITC(int numQualifyingChildren, double agi, String filingStatus, double investmentIncome, double earnedIncome) {
            if (investmentIncome > 11000 || earnedIncome <= 0) {  // Check initial conditions (removed earnedIncome > 1)
                return 0;
            }
    
    
            // Determine AGI limit based on filing status and number of children
            double agiLimit;
            if (filingStatus.equalsIgnoreCase("joint")) {
                agiLimit = (numQualifyingChildren >= 3) ? 63398 :
                          (numQualifyingChildren == 2) ? 59478 :
                          (numQualifyingChildren == 1) ? 53120 :
                                                      24210;
            } else {
                agiLimit = (numQualifyingChildren >= 3) ? 56838 :
                          (numQualifyingChildren == 2) ? 52918 :
                          (numQualifyingChildren == 1) ? 46560 :
                                                      17640;
            }
    
            if (agi > agiLimit) {  // Check AGI limit
                return 0;
            }
    
            // Determine credit amount based on number of children
            double creditAmount;
            switch (numQualifyingChildren) {
                case 0:
                    creditAmount = 600;
                    break;
                case 1:
                    creditAmount = 3995;
                    break;
                case 2:
                    creditAmount = 6604;
                    break;
                default:  // 3 or more children
                    creditAmount = 7430;
                    break;
            }
    
            return creditAmount; 
        }


        /*TEST CASES
             * run in seperate java project's main method *

                double result;

                // Test Case 1: Single filer, 1 child, AGI below limit
                result = calculateEITC(1, 45000, "single", 10000, 15000);
                System.out.println("Test Case 1: " + (result == 3995 ? "Passed" : "Failed") + " (Expected: 3995, Actual: " + result + ")");

                // Test Case 2: Joint filers, 3 children, AGI below limit
                result = calculateEITC(3, 60000, "joint", 8000, 25000);
                System.out.println("Test Case 2: " + (result == 7430 ? "Passed" : "Failed") + " (Expected: 7430, Actual: " + result + ")");

                // Test Case 3: Single filer, no children, AGI below limit
                result = calculateEITC(0, 15000, "single", 5000, 10000);
                System.out.println("Test Case 3: " + (result == 600 ? "Passed" : "Failed") + " (Expected: 600, Actual: " + result + ")");

                // Test Case 4: Joint filers, 2 children, AGI at limit (should get credit)
                result = calculateEITC(2, 59478, "joint", 10000, 20000);
                System.out.println("Test Case 4: " + (result == 6604 ? "Passed" : "Failed") + " (Expected: 6604, Actual: " + result + ")");

                // Test Case 5: Single filer, 1 child, AGI above limit
                result = calculateEITC(1, 48000, "single", 10000, 15000);
                System.out.println("Test Case 5: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 6: Investment income too high
                result = calculateEITC(2, 50000, "joint", 12000, 20000);
                System.out.println("Test Case 6: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 7: No earned income
                result = calculateEITC(1, 40000, "single", 8000, 0);
                System.out.println("Test Case 7: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");
         
             */

 
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */




}
