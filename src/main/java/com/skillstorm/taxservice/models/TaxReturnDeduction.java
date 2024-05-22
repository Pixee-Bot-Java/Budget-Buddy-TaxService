package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "taxreturn_deduction")
public class TaxReturnDeduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taxreturn_id", referencedColumnName = "id")
    private TaxReturn taxReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deduction_id", referencedColumnName = "id")
    private Deduction deduction;

    @Column(name = "amount_spent")
    private BigDecimal amountSpent;

    @Column(name = "net_deduction")
    private BigDecimal netDeduction;
}
