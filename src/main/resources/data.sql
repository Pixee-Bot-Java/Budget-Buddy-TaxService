INSERT INTO filing_status (status) VALUES ('Single');
INSERT INTO filing_status (status) VALUES ('Married filing jointly');
INSERT INTO filing_status (status) VALUES ('Married filing separately');
INSERT INTO filing_status (status) VALUES ('Head of Household');
INSERT INTO filing_status (status) VALUES ('Qualifying Surviving Spouse');

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
