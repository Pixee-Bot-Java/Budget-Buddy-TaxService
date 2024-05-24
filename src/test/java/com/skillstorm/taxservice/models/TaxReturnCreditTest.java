package com.skillstorm.taxservice.models;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class TaxReturnCreditTest {

    @Test
    public void testNoArgsConstructor() {
        // Given
        TaxReturnCredit taxReturnCredit = new TaxReturnCredit();

        // Then
        assertThat(taxReturnCredit).isNotNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        TaxReturnCredit taxReturnCredit = new TaxReturnCredit();
        int id = 1;
        TaxReturn taxReturn = new TaxReturn();
        int numDependents = 2;
        int numDependentsAotc = 1;
        int numChildren = 2;
        BigDecimal childCareExpenses = BigDecimal.valueOf(2500);
        BigDecimal educationExpenses = BigDecimal.valueOf(10000);
        BigDecimal llcEducationExpenses = BigDecimal.valueOf(5000);
        BigDecimal iraContributions = BigDecimal.valueOf(6000);
        boolean claimedAsDependent = false;
        boolean claimLlcCredit = true;

        // When
        taxReturnCredit.setId(id);
        taxReturnCredit.setTaxReturn(taxReturn);
        taxReturnCredit.setNumDependents(numDependents);
        taxReturnCredit.setNumDependentsAotc(numDependentsAotc);
        taxReturnCredit.setNumChildren(numChildren);
        taxReturnCredit.setChildCareExpenses(childCareExpenses);
        taxReturnCredit.setEducationExpenses(educationExpenses);
        taxReturnCredit.setLlcEducationExpenses(llcEducationExpenses);
        taxReturnCredit.setIraContributions(iraContributions);
        taxReturnCredit.setClaimedAsDependent(claimedAsDependent);
        taxReturnCredit.setClaimLlcCredit(claimLlcCredit);

        // Then
        assertThat(taxReturnCredit.getId()).isEqualTo(id);
        assertThat(taxReturnCredit.getTaxReturn()).isEqualTo(taxReturn);
        assertThat(taxReturnCredit.getNumDependents()).isEqualTo(numDependents);
        assertThat(taxReturnCredit.getNumDependentsAotc()).isEqualTo(numDependentsAotc);
        assertThat(taxReturnCredit.getNumChildren()).isEqualTo(numChildren);
        assertThat(taxReturnCredit.getChildCareExpenses()).isEqualTo(childCareExpenses);
        assertThat(taxReturnCredit.getEducationExpenses()).isEqualTo(educationExpenses);
        assertThat(taxReturnCredit.getLlcEducationExpenses()).isEqualTo(llcEducationExpenses);
        assertThat(taxReturnCredit.getIraContributions()).isEqualTo(iraContributions);
        assertThat(taxReturnCredit.isClaimedAsDependent()).isEqualTo(claimedAsDependent);
        assertThat(taxReturnCredit.isClaimLlcCredit()).isEqualTo(claimLlcCredit);
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        TaxReturn taxReturn = new TaxReturn();
        TaxReturnCredit taxReturnCredit1 = new TaxReturnCredit();
        taxReturnCredit1.setId(1);
        taxReturnCredit1.setTaxReturn(taxReturn);
        taxReturnCredit1.setNumDependents(2);
        taxReturnCredit1.setNumDependentsAotc(1);
        taxReturnCredit1.setNumChildren(2);
        taxReturnCredit1.setChildCareExpenses(BigDecimal.valueOf(2500));
        taxReturnCredit1.setEducationExpenses(BigDecimal.valueOf(10000));
        taxReturnCredit1.setLlcEducationExpenses(BigDecimal.valueOf(5000));
        taxReturnCredit1.setIraContributions(BigDecimal.valueOf(6000));
        taxReturnCredit1.setClaimedAsDependent(false);
        taxReturnCredit1.setClaimLlcCredit(true);

        TaxReturnCredit taxReturnCredit2 = new TaxReturnCredit();
        taxReturnCredit2.setId(1);
        taxReturnCredit2.setTaxReturn(taxReturn);
        taxReturnCredit2.setNumDependents(2);
        taxReturnCredit2.setNumDependentsAotc(1);
        taxReturnCredit2.setNumChildren(2);
        taxReturnCredit2.setChildCareExpenses(BigDecimal.valueOf(2500));
        taxReturnCredit2.setEducationExpenses(BigDecimal.valueOf(10000));
        taxReturnCredit2.setLlcEducationExpenses(BigDecimal.valueOf(5000));
        taxReturnCredit2.setIraContributions(BigDecimal.valueOf(6000));
        taxReturnCredit2.setClaimedAsDependent(false);
        taxReturnCredit2.setClaimLlcCredit(true);

        // Then
        assertThat(taxReturnCredit1).isEqualTo(taxReturnCredit2);
        assertThat(taxReturnCredit1.hashCode()).isEqualTo(taxReturnCredit2.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        TaxReturn taxReturn = new TaxReturn();
        TaxReturnCredit taxReturnCredit = new TaxReturnCredit();
        taxReturnCredit.setId(1);
        taxReturnCredit.setTaxReturn(taxReturn);
        taxReturnCredit.setNumDependents(2);
        taxReturnCredit.setNumDependentsAotc(1);
        taxReturnCredit.setNumChildren(2);
        taxReturnCredit.setChildCareExpenses(BigDecimal.valueOf(2500));
        taxReturnCredit.setEducationExpenses(BigDecimal.valueOf(10000));
        taxReturnCredit.setLlcEducationExpenses(BigDecimal.valueOf(5000));
        taxReturnCredit.setIraContributions(BigDecimal.valueOf(6000));
        taxReturnCredit.setClaimedAsDependent(false);
        taxReturnCredit.setClaimLlcCredit(true);

        // When
        String taxReturnCreditString = taxReturnCredit.toString();

        // Then
        assertThat(taxReturnCreditString).contains("TaxReturnCredit");
        assertThat(taxReturnCreditString).contains("id=1");
        assertThat(taxReturnCreditString).contains("taxReturn=" + taxReturn);
        assertThat(taxReturnCreditString).contains("numDependents=2");
        assertThat(taxReturnCreditString).contains("numDependentsAotc=1");
        assertThat(taxReturnCreditString).contains("numChildren=2");
        assertThat(taxReturnCreditString).contains("childCareExpenses=2500");
        assertThat(taxReturnCreditString).contains("educationExpenses=10000");
        assertThat(taxReturnCreditString).contains("llcEducationExpenses=5000");
        assertThat(taxReturnCreditString).contains("iraContributions=6000");
        assertThat(taxReturnCreditString).contains("claimedAsDependent=false");
        assertThat(taxReturnCreditString).contains("claimLlcCredit=true");
    }
}
