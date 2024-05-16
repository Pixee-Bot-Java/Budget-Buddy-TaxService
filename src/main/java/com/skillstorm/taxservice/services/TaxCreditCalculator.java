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

//EDUCATION CREDITS


        public static double calculateAOTC(double qualifiedExpenses, double agi, String filingStatus) {
            if (filingStatus.equalsIgnoreCase("separate")) {
                return 0; // Married filing separately cannot claim AOTC
            }

            double agiLimit = (filingStatus.equalsIgnoreCase("joint")) ? 180000 : 90000;
            
            if (agi > agiLimit) {
                return 0; // No credit if AGI exceeds limit
            }
            
            double maxCredit = 2500;
            double phaseoutStart = (filingStatus.equalsIgnoreCase("joint")) ? 160000 : 80000;

            if (agi >= phaseoutStart) {
                // Handle partial credit (we'll need the actual phaseout formula here)
                // For now, let's assume a linear phaseout:
                double phaseoutAmount = (agi - phaseoutStart) / 20000 * maxCredit; 
                maxCredit = Math.max(0, maxCredit - phaseoutAmount);
            }

            double credit = Math.min(maxCredit, 
                                    Math.min(2000, qualifiedExpenses) + 
                                    0.25 * Math.max(0, qualifiedExpenses - 2000));

            return credit;
        }

        // LLC
        public static double calculateLLC(double qualifiedExpenses) {
            double maxCredit = 2000;
            double credit = Math.min(maxCredit, 0.20 * Math.min(10000, qualifiedExpenses));
            return credit;
        }


        /*TEST CASES
             * run in seperate java project's main method *

                double result;

                // Test Cases for AOTC
                // Test Case 1: Single filer, AGI below limit, qualified expenses within first tier
                result = calculateAOTC(1500, 70000, "single");
                System.out.println("AOTC Test Case 1: " + (result == 1500 ? "Passed" : "Failed") + " (Expected: 1500, Actual: " + result + ")");

                // Test Case 2: Joint filers, AGI below limit, qualified expenses in both tiers
                result = calculateAOTC(3500, 150000, "joint");
                System.out.println("AOTC Test Case 2: " + (result == 2375 ? "Passed" : "Failed") + " (Expected: 2375, Actual: " + result + ")");

                // Test Case 3: Single filer, AGI in phaseout range (Cannot test accurately without formula)
                result = calculateAOTC(2500, 85000, "single"); 
                System.out.println("AOTC Test Case 3: Cannot verify accuracy due to unknown phaseout formula (Actual: " + result + ")");

                // Test Case 4: Joint filers, AGI above limit
                result = calculateAOTC(4000, 190000, "joint");
                System.out.println("AOTC Test Case 4: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 5: Married filing separately
                result = calculateAOTC(2000, 50000, "separate");
                System.out.println("AOTC Test Case 5: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Cases for LLC
                // Test Case 6: Qualified expenses below limit
                result = calculateLLC(5000);
                System.out.println("LLC Test Case 6: " + (result == 1000 ? "Passed" : "Failed") + " (Expected: 1000, Actual: " + result + ")");

                // Test Case 7: Qualified expenses at limit
                result = calculateLLC(10000);
                System.out.println("LLC Test Case 7: " + (result == 2000 ? "Passed" : "Failed") + " (Expected: 2000, Actual: " + result + ")");

                // Test Case 8: Qualified expenses above limit
                result = calculateLLC(15000);
                System.out.println("LLC Test Case 8: " + (result == 2000 ? "Passed" : "Failed") + " (Expected: 2000, Actual: " + result + ")");
         
             */


/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- */


//RETIREMENT CREDITS

        public static double calculateSaversCredit(double contributionAmount, double agi, String filingStatus) {
            // Check initial conditions
            if (contributionAmount <= 0 || contributionAmount > 2000) {
                return 0; // Contribution must be positive and not exceed $2,000
            }

            // Determine credit rate based on AGI and filing status
            double creditRate = 0; 
            if (filingStatus.equalsIgnoreCase("joint")) {
                if (agi <= 43500) {
                    creditRate = 0.50;
                } else if (agi <= 47500) {
                    creditRate = 0.20;
                } else if (agi <= 73000) {
                    creditRate = 0.10;
                }
            } else if (filingStatus.equalsIgnoreCase("head")) {
                if (agi <= 32625) {
                    creditRate = 0.50;
                } else if (agi <= 35625) {
                    creditRate = 0.20;
                } else if (agi <= 54750) {
                    creditRate = 0.10;
                }
            } else if (filingStatus.equalsIgnoreCase("single")) {  // Specific check for "single"
                if (agi <= 21750) {
                    creditRate = 0.50;
                } else if (agi <= 23750) {
                    creditRate = 0.20;
                } else if (agi <= 36500) {
                    creditRate = 0.10;
                }
            } // No else here, if it's not joint, head, or single, creditRate remains 0

            // Calculate credit amount
            double creditAmount = contributionAmount * creditRate;
            return Math.min(creditAmount, 1000); // Cap at $1,000
        }

        /*TEST CASES
             * run in seperate java project's main method *

                double result;

                // Test Cases for Saver's Credit
                // Test Case 1: Single filer, low AGI, max contribution
                result = calculateSaversCredit(2000, 15000, "single");
                System.out.println("Test Case 1: " + (result == 1000 ? "Passed" : "Failed") + " (Expected: 1000, Actual: " + result + ")");

                // Test Case 2: Joint filers, mid-range AGI, partial credit
                result = calculateSaversCredit(1200, 45000, "joint");
                System.out.println("Test Case 2: " + (result == 240 ? "Passed" : "Failed") + " (Expected: 240, Actual: " + result + ")");

                // Test Case 3: Head of household, high AGI, no credit
                result = calculateSaversCredit(1800, 60000, "head");
                System.out.println("Test Case 3: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 4: Single filer, AGI at the threshold for 10% credit *This test Fails*
                result = calculateSaversCredit(800, 23750, "single");
                System.out.println("Test Case 4: " + (result == 80 ? "Passed" : "Failed") + " (Expected: 80, Actual: " + result + ")"); 

                // Test Case 5: Joint filers, AGI at the threshold for 20% credit
                result = calculateSaversCredit(500, 47500, "joint");
                System.out.println("Test Case 5: " + (result == 100 ? "Passed" : "Failed") + " (Expected: 100, Actual: " + result + ")");

                // Test Case 6: Negative contribution (invalid input)
                result = calculateSaversCredit(-500, 20000, "single");
                System.out.println("Test Case 6: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 7: Contribution exceeding $2,000 (invalid input)
                result = calculateSaversCredit(2500, 18000, "single");
                System.out.println("Test Case 7: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");

                // Test Case 8: Invalid filing status
                result = calculateSaversCredit(1000, 35000, "invalid");
                System.out.println("Test Case 8: " + (result == 0 ? "Passed" : "Failed") + " (Expected: 0, Actual: " + result + ")");
         
             */


}
