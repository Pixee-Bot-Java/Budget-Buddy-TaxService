DROP TABLE IF EXISTS tax_brackets CASCADE;
DROP TABLE IF EXISTS standard_deduction CASCADE;
DROP TABLE IF EXISTS capital_gains_tax;
DROP TABLE IF EXISTS filing_status CASCADE;
DROP TABLE IF EXISTS child_tax_credit CASCADE;
DROP TABLE IF EXISTS dependent_care_tax_credit CASCADE;
DROP TABLE IF EXISTS dependent_care_tax_credit_limit CASCADE;
DROP TABLE IF EXISTS earned_income_tax_credit CASCADE;
DROP TABLE IF EXISTS education_tax_credit_aotc CASCADE;
DROP TABLE IF EXISTS education_tax_credit_llc CASCADE;
DROP TABLE IF EXISTS savers_tax_credit CASCADE;
DROP TABLE IF EXISTS state_tax CASCADE;
DROP TABLE IF EXISTS states CASCADE;
DROP TABLE IF EXISTS deduction CASCADE;


CREATE TABLE IF NOT EXISTS child_tax_credit (
  id SERIAL PRIMARY KEY,
  per_qualifying_child INT NOT NULL,
  per_other_child INT NOT NULL,
  income_threshold INT NOT NULL,
  rate_factor DECIMAL(5, 2) NOT NULL DEFAULT 0.05,
  refundable BOOLEAN NOT NULL,
  refund_limit INT NOT NULL,
  refund_rate DECIMAL(5, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS dependent_care_tax_credit (
  id SERIAL PRIMARY KEY,
  income_range INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS dependent_care_tax_credit_limit (
  id SERIAL PRIMARY KEY,
  num_dependents INT NOT NULL,
  credit_limit INT NOT NULL,
  refundable BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS earned_income_tax_credit (
  id SERIAL PRIMARY KEY,
  agi_threshold_3children INT NOT NULL,
  agi_threshold_2Children INT NOT NULL,
  agi_threshold_1Children INT NOT NULL,
  agi_threshold_0Children INT NOT NULL,
  amount_3children INT NOT NULL,
  amount_2children INT NOT NULL,
  amount_1children INT NOT NULL,
  amount_0children INT NOT NULL,
  investment_income_limit INT NOT NULL,
  refundable BOOLEAN NOT NULL,
  refund_limit INT NOT NULL,
  refund_rate DECIMAL(5, 2)
);

CREATE TABLE IF NOT EXISTS education_tax_credit_aotc (
  id SERIAL PRIMARY KEY,
  full_credit_income_threshold INT NOT NULL,
  partial_credit_income_threshold INT NOT NULL,
  income_partial_credit_rate DECIMAL(5, 2) NOT NULL,
  max_credit_amount INT NOT NULL,
  full_credit_expenses_threshold INT NOT NULL,
  partial_credit_expenses_threshold INT NOT NULL,
  partial_credit_expenses_rate DECIMAL(5,2) NOT NULL,
  refundable BOOLEAN NOT NULL,
  refund_limit INT NOT NULL,
  refund_rate DECIMAL(5, 2)
);

CREATE TABLE IF NOT EXISTS education_tax_credit_llc (
  id SERIAL PRIMARY KEY,
  full_credit_income_threshold INT NOT NULL,
  partial_credit_income_threshold INT NOT NULL,
  income_partial_credit_rate DECIMAL(5, 2) NOT NULL,
  max_credit_amount INT NOT NULL,
  expenses_threshold INT NOT NULL,
  credit_rate DECIMAL(5, 2) NOT NULL,
  refundable BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS savers_tax_credit (
  id SERIAL PRIMARY KEY,
  agi_threshold_first_contribution_limit INT NOT NULL,
  agi_threshold_second_contribution_limit INT NOT NULL,
  agi_threshold_third_contribution_limit INT NOT NULL,
  first_contribution_rate DECIMAL(5, 2) NOT NULL,
  second_contribution_rate DECIMAL(5, 2) NOT NULL,
  third_contribution_rate DECIMAL(5, 2) NOT NULL,
  max_contribution_amount INT NOT NULL,
  refundable BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS filing_status (
  id SERIAL PRIMARY KEY,
  status VARCHAR(50) NOT NULL UNIQUE,
  child_tax_credit_id INT NOT NULL,
  earned_income_tax_credit_id INT NOT NULL,
  education_tax_credit_aotc_id INT NOT NULL,
  education_tax_credit_llc_id INT NOT NULL,
  savers_tax_credit_id INT NOT NULL,
  FOREIGN KEY (child_tax_credit_id) REFERENCES child_tax_credit(id),
  FOREIGN KEY (earned_income_tax_credit_id) REFERENCES earned_income_tax_credit(id),
  FOREIGN KEY (education_tax_credit_aotc_id) REFERENCES education_tax_credit_aotc(id),
  FOREIGN KEY (education_tax_credit_llc_id) REFERENCES education_tax_credit_llc(id),
  FOREIGN KEY (savers_tax_credit_id) REFERENCES savers_tax_credit(id)
);

CREATE TABLE IF NOT EXISTS standard_deduction (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  deduction_amount INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE IF NOT EXISTS capital_gains_tax (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL,
  income_range INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE IF NOT EXISTS tax_brackets (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL,
  min_income INT NOT NULL,
  max_income INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);

CREATE TABLE IF NOT EXISTS states (
  id SERIAL PRIMARY KEY,
  state_name VARCHAR(50),
  state_code VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS state_tax (
  id SERIAL PRIMARY KEY,
  state_id INT NOT NULL,
  income_range INT NOT NULL,
  rate DECIMAL(6, 5) NOT NULL,
  FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE TABLE IF NOT EXISTS deduction (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    agi_limit DECIMAL(4, 2),
    itemized BOOLEAN
);
