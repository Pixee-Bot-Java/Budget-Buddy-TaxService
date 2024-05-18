package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "taxreturn_credit")
public class TaxReturnCredit {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "tax_return_id")
  private int taxReturnId;

  @Column(name = "num_dependents_aotc")
  private int numDependentsAotc;

  @Column(name = "num_dependents_under_13")
  private int numChildren;

  @Column(name = "child_care_expenses")
  private BigDecimal childCareExpenses;

  @Column(name = "education_expenses")
  private BigDecimal educationExpenses;

  @Column(name = "ira_contributions")
  private BigDecimal ira_contributions;

  @Column(name = "claimed_as_dependent")
  private boolean claimedAsDependent;

}
