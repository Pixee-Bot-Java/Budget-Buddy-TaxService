DROP TABLE IF EXISTS tax_brackets CASCADE;
DROP TABLE IF EXISTS filing_status CASCADE;


CREATE TABLE filing_status (
  id SERIAL PRIMARY KEY,
  status VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE tax_brackets (
  id SERIAL PRIMARY KEY,
  filing_status_id INT NOT NULL,
  rate DECIMAL(5, 2) NOT NULL,
  min_income INT NOT NULL,
  max_income INT NOT NULL,
  FOREIGN KEY (filing_status_id) REFERENCES filing_status(id)
);