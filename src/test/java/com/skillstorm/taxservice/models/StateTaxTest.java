package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTaxTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        StateTax stateTax = new StateTax();

        // Then
        assertThat(stateTax).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        StateTax stateTax = new StateTax();
        int id = 1;
        State state = new State();
        int incomeRange = 50000;
        BigDecimal rate = BigDecimal.valueOf(0.05);

        // When
        stateTax.setId(id);
        stateTax.setState(state);
        stateTax.setIncomeRange(incomeRange);
        stateTax.setRate(rate);

        // Then
        assertThat(stateTax.getId()).isEqualTo(id);
        assertThat(stateTax.getState()).isEqualTo(state);
        assertThat(stateTax.getIncomeRange()).isEqualTo(incomeRange);
        assertThat(stateTax.getRate()).isEqualTo(rate);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        State state = new State();
        StateTax stateTax1 = new StateTax(1, state, 50000, BigDecimal.valueOf(0.05));
        StateTax stateTax2 = new StateTax(1, state, 50000, BigDecimal.valueOf(0.05));

        // Then
        assertThat(stateTax1).isEqualTo(stateTax2);
        assertThat(stateTax1.hashCode()).isEqualTo(stateTax2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        State state = new State();
        StateTax stateTax = new StateTax(1, state, 50000, BigDecimal.valueOf(0.05));

        // When
        String taxString = stateTax.toString();

        // Then
        assertThat(taxString).contains("StateTax");
        assertThat(taxString).contains("id=1");
        assertThat(taxString).contains("state=" + state);
        assertThat(taxString).contains("incomeRange=50000");
        assertThat(taxString).contains("rate=0.05");
    }
}