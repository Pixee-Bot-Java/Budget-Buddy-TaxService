package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilingStatusTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        FilingStatus filingStatus = new FilingStatus();

        // Then
        assertThat(filingStatus).isNotNull();
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        int id = 1;
        String status = "Single";

        // When
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(id);
        filingStatus.setStatus(status);

        // Then
        assertThat(filingStatus.getId()).isEqualTo(id);
        assertThat(filingStatus.getStatus()).isEqualTo(status);
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        int id = 1;
        String status = "Married";

        // When
        filingStatus.setId(id);
        filingStatus.setStatus(status);

        // Then
        assertThat(filingStatus.getId()).isEqualTo(id);
        assertThat(filingStatus.getStatus()).isEqualTo(status);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        FilingStatus filingStatus1 = new FilingStatus();
        filingStatus1.setId(1);
        filingStatus1.setStatus("Single");
        FilingStatus filingStatus2 = new FilingStatus();
        filingStatus2.setId(1);
        filingStatus2.setStatus("Single");

        // Then
        assertThat(filingStatus1).isEqualTo(filingStatus2);
        assertThat(filingStatus1.hashCode()).isEqualTo(filingStatus2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setId(1);
        filingStatus.setStatus("Married");

        // When
        String statusString = filingStatus.toString();

        // Then
        assertThat(statusString).contains("FilingStatus");
        assertThat(statusString).contains("id=1");
        assertThat(statusString).contains("status=Married");
    }
}
