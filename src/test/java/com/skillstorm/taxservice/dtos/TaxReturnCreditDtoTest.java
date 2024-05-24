package com.skillstorm.taxservice.dtos;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxReturnCreditDtoTest {

    @Test
    void testNoArgsConstructorAndGettersAndSetters() {
        TaxReturnCreditDto taxReturnCreditDto = new TaxReturnCreditDto();
        assertThat(taxReturnCreditDto).isNotNull();

        // Test default values
        assertThat(taxReturnCreditDto.getTaxReturnId()).isEqualTo(0);
        assertThat(taxReturnCreditDto.getNumDependents()).isEqualTo(0);
        assertThat(taxReturnCreditDto.getNumDependentsAotc()).isEqualTo(0);
        assertThat(taxReturnCreditDto.getNumChildren()).isEqualTo(0);
        assertThat(taxReturnCreditDto.getChildCareExpenses()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(taxReturnCreditDto.getEducationExpenses()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(taxReturnCreditDto.getLlcEducationExpenses()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(taxReturnCreditDto.getIraContributions()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(taxReturnCreditDto.isClaimedAsDependent()).isFalse();
        assertThat(taxReturnCreditDto.isClaimLlcCredit()).isFalse();

        // Test setters
        taxReturnCreditDto.setTaxReturnId(1);
        assertThat(taxReturnCreditDto.getTaxReturnId()).isEqualTo(1);

        int numDependents = 2;
        taxReturnCreditDto.setNumDependents(numDependents);
        assertThat(taxReturnCreditDto.getNumDependents()).isEqualTo(numDependents);

        int numDependentsAotc = 3;
        taxReturnCreditDto.setNumDependentsAotc(numDependentsAotc);
        assertThat(taxReturnCreditDto.getNumDependentsAotc()).isEqualTo(numDependentsAotc);

        int numChildren = 4;
        taxReturnCreditDto.setNumChildren(numChildren);
        assertThat(taxReturnCreditDto.getNumChildren()).isEqualTo(numChildren);

        BigDecimal childCareExpenses = BigDecimal.valueOf(1000);
        taxReturnCreditDto.setChildCareExpenses(childCareExpenses);
        assertThat(taxReturnCreditDto.getChildCareExpenses()).isEqualByComparingTo(childCareExpenses);

        BigDecimal educationExpenses = BigDecimal.valueOf(2000);
        taxReturnCreditDto.setEducationExpenses(educationExpenses);
        assertThat(taxReturnCreditDto.getEducationExpenses()).isEqualByComparingTo(educationExpenses);

        BigDecimal llcEducationExpenses = BigDecimal.valueOf(3000);
        taxReturnCreditDto.setLlcEducationExpenses(llcEducationExpenses);
        assertThat(taxReturnCreditDto.getLlcEducationExpenses()).isEqualByComparingTo(llcEducationExpenses);

        BigDecimal iraContributions = BigDecimal.valueOf(4000);
        taxReturnCreditDto.setIraContributions(iraContributions);
        assertThat(taxReturnCreditDto.getIraContributions()).isEqualByComparingTo(iraContributions);

        boolean claimedAsDependent = true;
        taxReturnCreditDto.setClaimedAsDependent(claimedAsDependent);
        assertThat(taxReturnCreditDto.isClaimedAsDependent()).isEqualTo(claimedAsDependent);

        boolean claimLlcCredit = true;
        taxReturnCreditDto.setClaimLlcCredit(claimLlcCredit);
        assertThat(taxReturnCreditDto.isClaimLlcCredit()).isEqualTo(claimLlcCredit);
    }
}
