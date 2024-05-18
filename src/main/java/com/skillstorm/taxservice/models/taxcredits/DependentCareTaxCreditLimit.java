package com.skillstorm.taxservice.models.taxcredits;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dependent_care_tax_credit_limit")
public class DependentCareTaxCreditLimit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "num_dependents")
  private int numDependents;

  @Column(name = "credit_limit")
  private int creditLimit;
}
