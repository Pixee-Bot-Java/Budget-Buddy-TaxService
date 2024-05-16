package com.skillstorm.taxservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table
public class TaxReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;

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

    @OneToMany(mappedBy = "taxReturn", cascade = CascadeType.ALL)
    private List<W2> w2s;

    private BigDecimal refund;

    public TaxReturn() {
        w2s = List.of();
    }
}
