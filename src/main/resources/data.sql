BEGIN;
-- marries filing jointly
INSERT INTO child_tax_credit (per_qualifying_child, per_other_child, income_threshold, rate_factor) VALUES (2000, 500, 400000, 0.05);
-- other filing statuses
INSERT INTO child_tax_credit (per_qualifying_child, per_other_child, income_threshold, rate_factor) VALUES (2000, 500, 200000, 0.05);

-- married filing jointly
INSERT INTO earned_income_tax_credit (agi_threshold_3children, 
                                      agi_threshold_2Children, 
                                      agi_threshold_1Children, 
                                      agi_threshold_0Children, 
                                      amount_3children, 
                                      amount_2children, 
                                      amount_1children, 
                                      amount_0children,
                                      investment_income_limit)
                                      VALUES (63398, 59478, 53120, 24210, 7430, 6604, 3995, 600, 11000);
-- other filing statuses
INSERT INTO earned_income_tax_credit (agi_threshold_3children, 
                                      agi_threshold_2Children, 
                                      agi_threshold_1Children, 
                                      agi_threshold_0Children, 
                                      amount_3children, 
                                      amount_2children, 
                                      amount_1children, 
                                      amount_0children,
                                      investment_income_limit)
                                      VALUES (56838, 52918, 46560, 17640, 7430, 6604, 3995, 600, 11000);

-- married filing jointly
INSERT INTO education_tax_credit_aotc (full_credit_income_threshold, 
                                      partial_credit_income_threshold,
                                      income_partial_credit_rate,
                                      max_credit_amount,
                                      full_credit_expenses_threshold,
                                      partial_credit_expenses_threshold,
                                      partial_credit_expenses_rate)
                                      VALUES (160000, 20000, 0.75, 2500, 2000, 2000, 0.40);
-- other filing statuses
INSERT INTO education_tax_credit_aotc (full_credit_income_threshold, 
                                      partial_credit_income_threshold,
                                      income_partial_credit_rate,
                                      max_credit_amount,
                                      full_credit_expenses_threshold,
                                      partial_credit_expenses_threshold,
                                      partial_credit_expenses_rate)
                                      VALUES (80000, 10000, 0.75, 2500, 2000, 2000, 0.40);

-- married filing jointly
INSERT INTO education_tax_credit_llc (full_credit_income_threshold, 
                                      partial_credit_income_threshold,
                                      income_partial_credit_rate,
                                      max_credit_amount,
                                      expenses_threshold,
                                      credit_rate)
                                      VALUES (160000,20000, 0.75, 2000, 10000, 0.20);
-- other filing statuses
INSERT INTO education_tax_credit_llc (full_credit_income_threshold, 
                                      partial_credit_income_threshold,
                                      income_partial_credit_rate,
                                      max_credit_amount,
                                      expenses_threshold,
                                      credit_rate)
                                      VALUES (80000, 20000, 0.75, 2000, 10000, 0.20);

-- marries filing jointly
INSERT INTO savers_tax_credit (agi_threshold_first_contribution_limit,
                              agi_threshold_second_contribution_limit,
                              agi_threshold_third_contribution_limit,
                              first_contribution_rate,
                              second_contribution_rate,
                              third_contribution_rate,
                              max_contribution_amount)
                              VALUES (43500, 3999, 25499, 0.5, 0.2, 0.1, 2000);
-- head of household
INSERT INTO savers_tax_credit (agi_threshold_first_contribution_limit,
                              agi_threshold_second_contribution_limit,
                              agi_threshold_third_contribution_limit,
                              first_contribution_rate,
                              second_contribution_rate,
                              third_contribution_rate,
                              max_contribution_amount)
                              VALUES (32625, 2999, 19124, 0.5, 0.2, 0.1, 2000);
-- other filing statuses
INSERT INTO savers_tax_credit (agi_threshold_first_contribution_limit,
                              agi_threshold_second_contribution_limit,
                              agi_threshold_third_contribution_limit,
                              first_contribution_rate,
                              second_contribution_rate,
                              third_contribution_rate,
                              max_contribution_amount)
                              VALUES (21750, 1999, 12749, 0.5, 0.2, 0.1, 2000);
COMMIT;


BEGIN;
INSERT INTO filing_status (status, 
                          child_tax_credit_id, 
                          earned_income_tax_credit_id, 
                          education_tax_credit_aotc_id, 
                          education_tax_credit_llc_id, 
                          savers_tax_credit_id) 
                          VALUES ('Single', 2, 2, 2, 2, 3);
INSERT INTO filing_status (status,
                          child_tax_credit_id,
                          earned_income_tax_credit_id,
                          education_tax_credit_aotc_id,
                          education_tax_credit_llc_id,
                          savers_tax_credit_id) 
                          VALUES ('Married filing jointly', 1, 1, 1, 1, 1);
INSERT INTO filing_status (status,
                          child_tax_credit_id,
                          earned_income_tax_credit_id,
                          education_tax_credit_aotc_id,
                          education_tax_credit_llc_id,
                          savers_tax_credit_id) 
                          VALUES ('Married filing separately', 2, 2, 2, 2, 3);
INSERT INTO filing_status (status,
                          child_tax_credit_id,
                          earned_income_tax_credit_id,
                          education_tax_credit_aotc_id,
                          education_tax_credit_llc_id,
                          savers_tax_credit_id) 
                          VALUES ('Head of Household', 2, 2, 2, 2, 2);
INSERT INTO filing_status (status,
                          child_tax_credit_id,
                          earned_income_tax_credit_id,
                          education_tax_credit_aotc_id,
                          education_tax_credit_llc_id,
                          savers_tax_credit_id) 
                          VALUES ('Qualifying Surviving Spouse', 2, 2, 2, 2, 3);
COMMIT;


BEGIN;
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 11000, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 11001, 44725, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 44726, 95375, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95376, 182100, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 578125, 1);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 578126, 2147483647, 1);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 22000, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 22001, 89450, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 89451, 190750, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 190751, 364200, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 364201, 462500, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 462501, 693750, 2);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 693751, 2147483647, 2);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 11000, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 11001, 44725, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 44726, 95375, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95376, 182100, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 346875, 3);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 346876, 2147483647, 3);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 15700, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 15701, 59850, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 59851, 95350, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 95351, 182100, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 182101, 231250, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 231251, 578100, 4);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 578101, 2147483647, 4);

INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.10, 0, 22000, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.12, 22001, 89450, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.22, 89451, 190750, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.24, 190751, 364200, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.32, 364201, 462500, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.35, 462501, 693750, 5);
INSERT INTO tax_brackets (rate, min_income, max_income, filing_status_id) VALUES (0.37, 693751, 2147483647, 5);
COMMIT;
