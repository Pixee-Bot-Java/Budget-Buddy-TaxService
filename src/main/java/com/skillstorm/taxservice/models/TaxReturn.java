package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tax_return")
public class TaxReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;

    @Column(name = "filing_status")
    private int filingStatus;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    private String city;

    // May change to enum:
    private String state;

    private String zip;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String ssn;

    @OneToMany(mappedBy = "taxReturn", cascade = CascadeType.ALL)
    private List<W2> w2s;

    @Column(name = "total_income")
    private BigDecimal totalIncome;

    @Column(name = "adjusted_gross_income")
    private BigDecimal adjustedGrossIncome;

    @Column(name = "taxable_income")
    private BigDecimal taxableIncome;

    @Column(name = "fed_tax_withheld")
    private BigDecimal fedTaxWithheld;

    @Column(name = "state_tax_withheld")
    private BigDecimal stateTaxWithheld;

    @Column(name = "social_security_tax_withheld")
    private BigDecimal socialSecurityTaxWithheld;

    @Column(name = "medicare_tax_withheld")
    private BigDecimal medicareTaxWithheld;

    @Column(name = "total_deductions")
    private BigDecimal totalDeductions;

    @Column(name = "total_credits")
    private BigDecimal totalCredits;

    private BigDecimal refund;

    public TaxReturn() {
        w2s = List.of();
    }

    public TaxReturn(int id) {
        this();
        this.id = id;
    }
}
