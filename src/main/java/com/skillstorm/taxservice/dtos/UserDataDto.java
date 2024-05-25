package com.skillstorm.taxservice.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skillstorm.taxservice.constants.FilingStatus;
import com.skillstorm.taxservice.constants.State;
import com.skillstorm.taxservice.models.TaxReturn;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDataDto {

    private int id;
    private int year;
    private int userId;
    private String filingStatus;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private State state;
    private String zip;
    private String dateOfBirth;
    private String ssn;

    public UserDataDto() {
        // Default values to avoid null pointers:
        this.filingStatus = FilingStatus.SINGLE.toString();
        this.state = State.AL;
    }

    @JsonCreator
    public UserDataDto(@JsonProperty("filingStatus") String filingStatus) {
        this();
        if(filingStatus != null) {
            this.filingStatus = FilingStatus.fromString(filingStatus.trim().toUpperCase())
                    .toString();
        }
    }

    public UserDataDto(TaxReturn taxReturn) {
        this();
        this.id = taxReturn.getId();
        this.year = taxReturn.getYear();
        this.userId = taxReturn.getUserId();
        this.filingStatus = FilingStatus.fromValue(taxReturn.getFilingStatus()).toString();
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
    }

    @JsonIgnore
    public TaxReturn mapToEntity() {
        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setId(this.id);
        taxReturn.setYear(this.year);
        taxReturn.setUserId(this.userId);
        taxReturn.setFilingStatus(FilingStatus.fromString(this.filingStatus).getValue());
        taxReturn.setFirstName(this.firstName);
        taxReturn.setLastName(this.lastName);
        taxReturn.setEmail(this.email);
        taxReturn.setPhoneNumber(this.phoneNumber);
        taxReturn.setAddress(this.address);
        taxReturn.setCity(this.city);
        taxReturn.setState(this.state);
        taxReturn.setZip(this.zip);
        if (dateOfBirth != null) {
            taxReturn.setDateOfBirth(LocalDate.parse(dateOfBirth));
        }
        taxReturn.setSsn(this.ssn);

        return taxReturn;
    }
}
