package com.skillstorm.taxservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillstorm.taxservice.constants.FilingStatus;
import com.skillstorm.taxservice.models.TaxReturn;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class TaxReturnDto {

    private int id;
    private int year;
    private int userId;
    private FilingStatus filingStatus;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String dateOfBirth;
    private String ssn;
    private List<W2Dto> w2s;
    private BigDecimal totalIncome;
    private BigDecimal adjustedGrossIncome;
    private BigDecimal taxableIncome;
    private BigDecimal fedTaxWithheld;
    private BigDecimal stateTaxWithheld;
    private BigDecimal socialSecurityTaxWithheld;
    private BigDecimal medicareTaxWithheld;
    private BigDecimal totalDeductions;
    private BigDecimal totalCredits;
    private BigDecimal refund;

    public TaxReturnDto() {
        // Default values to avoid null pointers:
        this.filingStatus = FilingStatus.SINGLE;
        w2s = List.of();
    }

    @JsonCreator
    public TaxReturnDto(@JsonProperty("filingStatus") String filingStatus) {
        this();
        if(filingStatus != null) {
            this.filingStatus = FilingStatus.fromString(filingStatus.trim().toUpperCase());
        }
    }

    public TaxReturnDto(TaxReturn taxReturn) {
        this();
        this.id = taxReturn.getId();
        this.year = taxReturn.getYear();
        this.userId = taxReturn.getUserId();
        this.filingStatus = FilingStatus.fromValue(taxReturn.getFilingStatus());
        this.firstName = taxReturn.getFirstName();
        this.lastName = taxReturn.getLastName();
        this.email = taxReturn.getEmail();
        this.phoneNumber = taxReturn.getPhoneNumber();
        this.address = taxReturn.getAddress();
        this.city = taxReturn.getCity();
        this.state = taxReturn.getState();
        this.zip = taxReturn.getZip();
        if (taxReturn.getDateOfBirth() != null) {
            this.dateOfBirth = taxReturn.getDateOfBirth().toString();
        }
        this.ssn = taxReturn.getSsn();
        this.w2s = taxReturn.getW2s().stream().map(W2Dto::new).toList();
        this.totalIncome = taxReturn.getTotalIncome();
        this.adjustedGrossIncome = taxReturn.getAdjustedGrossIncome();
        this.taxableIncome = taxReturn.getTaxableIncome();
        this.fedTaxWithheld = taxReturn.getFedTaxWithheld();
        this.stateTaxWithheld = taxReturn.getStateTaxWithheld();
        this.socialSecurityTaxWithheld = taxReturn.getSocialSecurityTaxWithheld();
        this.medicareTaxWithheld = taxReturn.getMedicareTaxWithheld();
        this.totalDeductions = taxReturn.getTotalDeductions();
        this.totalCredits = taxReturn.getTotalCredits();
        this.refund = taxReturn.getRefund();
    }

    public TaxReturn mapToEntity() {
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(id);
        taxReturn.setYear(year);
        taxReturn.setUserId(userId);
        taxReturn.setFilingStatus(filingStatus.getValue());
        taxReturn.setFirstName(firstName);
        taxReturn.setLastName(lastName);
        taxReturn.setEmail(email);
        taxReturn.setPhoneNumber(phoneNumber);
        taxReturn.setAddress(address);
        taxReturn.setCity(city);
        taxReturn.setState(state);
        taxReturn.setZip(zip);
        if (dateOfBirth != null) {
            taxReturn.setDateOfBirth(LocalDate.parse(dateOfBirth));
        }
        taxReturn.setSsn(ssn);
        taxReturn.setW2s(w2s.stream().map(W2Dto::mapToEntity).toList());
        taxReturn.setTotalIncome(totalIncome);
        taxReturn.setAdjustedGrossIncome(adjustedGrossIncome);
        taxReturn.setTaxableIncome(taxableIncome);
        taxReturn.setFedTaxWithheld(fedTaxWithheld);
        taxReturn.setStateTaxWithheld(stateTaxWithheld);
        taxReturn.setSocialSecurityTaxWithheld(socialSecurityTaxWithheld);
        taxReturn.setMedicareTaxWithheld(medicareTaxWithheld);
        taxReturn.setTotalDeductions(totalDeductions);
        taxReturn.setTotalCredits(totalCredits);
        taxReturn.setRefund(refund);

        return taxReturn;
    }
}
