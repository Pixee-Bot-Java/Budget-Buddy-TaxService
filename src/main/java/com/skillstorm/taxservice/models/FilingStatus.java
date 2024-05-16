package com.skillstorm.taxservice.models;

import com.skillstorm.taxservice.models.taxcredits.ChildTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EarnedIncomeTaxCredit;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditAotc;
import com.skillstorm.taxservice.models.taxcredits.EducationTaxCreditLlc;
import com.skillstorm.taxservice.models.taxcredits.SaversTaxCredit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "filing_status")
@Data                     // Generates getters, setters, toString, hashCode, and equals methods
@NoArgsConstructor        // Generates a no-args constructor
public class FilingStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String status;

  @ManyToOne
  @JoinColumn(name = "child_tax_credit_id")
  private ChildTaxCredit childTaxCredit;

  @ManyToOne
  @JoinColumn(name = "earned_income_tax_credit_id")
  private EarnedIncomeTaxCredit earnedIncomeTaxCredit;

  @ManyToOne
  @JoinColumn(name = "education_tax_credit_aotc_id")
  private EducationTaxCreditAotc educationTaxCreditAotc;

  @ManyToOne
  @JoinColumn(name = "education_tax_credit_llc_id")
  private EducationTaxCreditLlc educationTaxCreditLlc;

  @ManyToOne
  @JoinColumn(name = "savers_tax_credit_id")
  private SaversTaxCredit saversTaxCredit;
}
