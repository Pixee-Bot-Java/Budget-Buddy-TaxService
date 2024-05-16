package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "w2")
public class W2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_return_id")
    private TaxReturn taxReturn;

    private int year;

    @Column(name = "user_id")
    private int userId;

    private String employer;

    private BigDecimal wages;

    @Column(name = "federal_income_tax_withheld")
    private BigDecimal federalIncomeTaxWithheld;

    @Column(name = "social_security_tax_withheld")
    private BigDecimal socialSecurityTaxWithheld;

    @Column(name = "medicare_tax_withheld")
    private BigDecimal medicareTaxWithheld;

    @Column(name = "image_key")
    private String imageKey;
}
